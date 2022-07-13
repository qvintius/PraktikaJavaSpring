package classes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/voprosy")
@Validated
public class VoprosController {

    private final InterfaceVopros interfaceVopros;

    @Autowired
    public VoprosController(InterfaceVopros interfaceVopros) {
        this.interfaceVopros = interfaceVopros;
    }

    @GetMapping("")//все вопросы
    public ResponseEntity<List<Vopros>> vseVoprosy() {
        return ResponseEntity.ok(interfaceVopros.outputAll());
    }

    @GetMapping("/{id}")//вопрос по айди
    public ResponseEntity<Vopros> readById(@PathVariable("id") String id){
        final Vopros vopros = interfaceVopros.readId(UUID.fromString(id));
        return vopros !=null
                ? new ResponseEntity<>(vopros, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return ResponseEntity.ok(interfaceVopros.outputById(UUID.fromString(id)));
    }

    @PostMapping("/new")//создание нового вопроса //yaml запись
    public ResponseEntity<?> create(@Valid @RequestBody Vopros vopros){
        //interfaceVopros.objToYaml( voprosy.getVoprosy());
        interfaceVopros.create(vopros);
        return ResponseEntity.ok().header("Vopros", "new Vopros").body("");
        //return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> edit(@PathVariable(name = "id") UUID id, @RequestBody Vopros vopros){
        interfaceVopros.update(vopros, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") String id){
        interfaceVopros.deleteId(UUID.fromString(id));
        return ResponseEntity.ok().header("Vopros", "Vopros is deleted").body("");
    }

    @GetMapping("/inf")
    public String inf(){
        return interfaceVopros.inf();
    }

}
