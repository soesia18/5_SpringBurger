package at.kaindorf.springburger.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Size(min = 1, message = "There is a name needed")
    private String name;
    @Size(min = 1, message = "There is a street needed")
    private String street;
    @Size(min = 1, message = "There is a city needed")
    private String city;
}
