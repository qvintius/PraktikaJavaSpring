
package classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Vopros {
    private UUID id = UUID.randomUUID();
    @NotBlank
    private String theme;

    private String formulirovka;

    @Min(2)
    private int count;

    private List<Otvet> otvety;

    public Vopros(String theme, String formulirovka, int count, List<Otvet> otvety) {
        this.theme = theme;
        this.formulirovka = formulirovka;
        this.count = count;
        this.otvety = otvety;
    }
}