package at.kaindorf.springburger.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public static enum Type{
        PATTY, CHEESE, VEGGIE;
    }

}
