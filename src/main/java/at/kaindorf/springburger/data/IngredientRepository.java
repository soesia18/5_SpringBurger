package at.kaindorf.springburger.data;

import at.kaindorf.springburger.beans.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringBurger<br>
 * <b>User:</b> simon<br>
 * <b>Date:</b> 28. November 2022<br>
 * <b>Time:</b> 12:44<br>
 */

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
