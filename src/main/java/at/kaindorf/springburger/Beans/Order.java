package at.kaindorf.springburger.Beans;

import at.kaindorf.springburger.data.BurgerRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "There is a name needed")
    private String name;
    @NotBlank(message = "There is a street needed")
    private String street;
    @NotBlank(message = "There is a city needed")
    private String city;

    @OneToMany(mappedBy = "order")
    private List<Burger> burgers = new ArrayList<>();

    public void addBurger (Burger burger) {
        burgers.add(burger);
        burger.setOrder(this);
    }
}
