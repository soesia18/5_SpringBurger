package at.kaindorf.springburger.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 7, message = "Name must be at least 7 characters long")
    private String name;
    @NotNull(message = "Ingredients must have at least 3 Items")
    @Size(min = 3, message = "Ingredients must have at least 3 Items")
    @ManyToMany
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne
    private Order order;

    public String printIngredientsPretty () {
        return ingredients.stream().map(Ingredient::getName).collect(Collectors.joining(", "));
    }

    public void addIngredient (Ingredient ingredient) {
        ingredients.add(ingredient);
    }
}
