package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.Beans.Burger;
import at.kaindorf.springburger.Beans.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <h3>Created by IntelliJ IDEA.</h3><br>
 * <b>Project:</b> SpringBurger<br>
 * <b>User:</b> simon<br>
 * <b>Date:</b> 21. November 2022<br>
 * <b>Time:</b> 12:28<br>
 */

@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    // ToDo: implement POST-Mapping to process order
    // ToDo: Mapping of order input values to order-object

    @GetMapping
    public String showOrderForm (Model model, @ModelAttribute("designBurger") Burger burger) {
        log.debug("GET request to /order: ");
        log.debug("Burger: " + burger);
        model.addAttribute("order", new Order());
        model.addAttribute("designBurger", burger);
        return "orderForm";
    }

    @PostMapping
    public String processOrder (@ModelAttribute(name = "order") Order order,
                                @ModelAttribute(name = "designBurger") Burger burger) {
        log.debug("POST request to /order: " + order);
        log.debug("Burger: " + burger);
        return "order";
    }
}
