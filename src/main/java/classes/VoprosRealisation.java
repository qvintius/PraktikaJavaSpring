package classes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class VoprosRealisation implements InterfaceVopros{


    public List<Vopros> outputAll(){
        return yamlToObj().getVoprosy();
    }

    public Vopros outputById(UUID id){
        //return voprosy.stream().filter(vopros -> vopros.getId().equals(id)).findAny().orElse(null);
        return yamlToObj().getVoprosy().stream().filter(vopros -> vopros.getId().equals(id)).findAny().orElse(null);

    }
    public void update(Vopros updatedVopros, UUID id) throws IdNotFoundException{
        Vopros voprosToBeUpdated = readId(id);
        List<Vopros> voprosList = (new ArrayList<>(outputAll()));
        voprosList.remove(voprosToBeUpdated);
        voprosToBeUpdated.setTheme(updatedVopros.getTheme());
        voprosToBeUpdated.setFormulirovka(updatedVopros.getFormulirovka());
        voprosToBeUpdated.setCount(updatedVopros.getCount());
        voprosToBeUpdated.setOtvety(updatedVopros.getOtvety());
        voprosList.add(voprosToBeUpdated);
        objToYaml(new Voprosy(voprosList));
    }
    public void create(Vopros vopros){
        //vopros.setId(UUID.randomUUID());
        //voprosy.add(vopros);
        List<Vopros> voprosList = (new ArrayList<>(outputAll()));
        voprosList.add(vopros);
        objToYaml(new Voprosy(voprosList));
    }
    public String inf(){
        List<Vopros> voprosList = yamlToObj().getVoprosy();
        return "Alex Medvedev, variant 4.4, electronnoe testirovanie, count of object: " +  voprosList.size(); // количество хранимых объектов*/;
    }

    //работа с файлом
    //запись
    private void objToYaml(Voprosy vopros){
        try {
            YAMLMapper yamlMapper = new YAMLMapper();
            yamlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            yamlMapper.writeValue(new File("file.yaml"), vopros);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //чтение
    private Voprosy yamlToObj(){
        YAMLMapper yamlMapper = new YAMLMapper();
        try {
            //FileInputStream fileInputStream = new FileInputStream("file.yaml");
            //System.out.printf(String.valueOf(fileInputStream.readAllBytes()));

            return yamlMapper.readValue(new File("file.yaml"), new TypeReference<Voprosy>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public Vopros readId(UUID id) throws IdNotFoundException {
        return yamlToObj().getVoprosy().stream().filter(vopros -> vopros.getId().equals(id)).findFirst().orElseThrow(() -> new IdNotFoundException(id));
    }
    public void deleteId(UUID id) throws IdNotFoundException{
        //voprosy.removeIf(v -> v.getId().equals(id));
        List<Vopros> voprosList = (new ArrayList<>(outputAll()));
        Optional<Vopros> voprosOptional = voprosList.stream().filter(vopros -> vopros.getId().equals(id)).findAny();
        voprosList.remove(readId(id));
        objToYaml(new Voprosy(voprosList));
    }

}