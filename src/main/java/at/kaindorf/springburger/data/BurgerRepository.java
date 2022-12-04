package at.kaindorf.springburger.data;

import at.kaindorf.springburger.beans.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringBurger<br>
 * <b>User:</b> Simon Schoeggler<br>
 * <b>Date:</b> 30. November 2022<br>
 * <b>Time:</b> 08:44<br>
 */

public interface BurgerRepository extends JpaRepository<Burger, Long> {
    Burger findByName(String name);
}
