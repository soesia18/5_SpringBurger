package at.kaindorf.springburger.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Burger {
    @NotNull
    @Size(min = 7, message = "Name must be at least 7 characters long")
    private String name;
    @NotNull(message = "Ingredients must have at least 3 Items")
    @Size(min = 3, message = "Ingredients must have at least 3 Items")
    private ArrayList<String> ingredients;
}
