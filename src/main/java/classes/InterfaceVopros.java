package classes;

import java.util.List;
import java.util.UUID;

public interface InterfaceVopros {
    public List<Vopros> outputAll();//вернуть список всех вопросов
    //public Vopros outputById(UUID id);//вернуть клиента с заданным id
    void create(Vopros vopros);//создание нового вопроса
    void update(Vopros vopros, UUID id);//обновить вопрос
   // void delete(UUID id);//удалить вопрос
    String inf();


    public Vopros readId(UUID id);//вернуть клиента с заданным id
    public void deleteId(UUID id);//удалить вопрос
    //void objToYaml(List<Vopros> vopros);
    //List<Vopros> yamlToObj();
}
