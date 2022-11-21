package at.kaindorf.springburger.Beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Burger {
    private String name;
    private ArrayList<String> ingredients;
}
