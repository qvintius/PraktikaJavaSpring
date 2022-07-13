package classes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(UUID id){
        super ("Vopros with same id " + id.toString() + " wasn't found");
    }

}
