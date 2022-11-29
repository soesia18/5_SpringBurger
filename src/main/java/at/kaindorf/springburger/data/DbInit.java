package at.kaindorf.springburger.data;

import at.kaindorf.springburger.Beans.Ingredient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringBurger<br>
 * <b>User:</b> simon<br>
 * <b>Date:</b> 28. November 2022<br>
 * <b>Time:</b> 12:54<br>
 */

@Component
@Slf4j
public class DbInit {
    @Autowired
    private IngredientRepository ingredientRepository;

    @PostConstruct
    private void initializeIngredients() {
        log.debug("Add ingredients to DB");
        ingredientRepository.save( new Ingredient("120b", "120 g Ground Beef", Ingredient.Type.PATTY));
        ingredientRepository.save(new Ingredient("160b", "160 g Ground Beef", Ingredient.Type.PATTY));
        ingredientRepository.save(new Ingredient("140T", "140 g Turkey", Ingredient.Type.PATTY));
        ingredientRepository.save(new Ingredient("VEGG", "Veggie", Ingredient.Type.PATTY));
        ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        ingredientRepository.save(new Ingredient("SWIS", "Swiss Cheese", Ingredient.Type.CHEESE));
        ingredientRepository.save(new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIE));
        ingredientRepository.save(new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE));
        ingredientRepository.save(new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE));
    }
}
