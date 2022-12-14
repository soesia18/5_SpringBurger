package at.kaindorf.springburger.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ingredient {
    @Id
    private String id;
    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;




    public static enum Type{
        PATTY, CHEESE, VEGGIE;
    }

}
