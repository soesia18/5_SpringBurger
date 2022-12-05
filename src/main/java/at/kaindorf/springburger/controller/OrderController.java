package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.beans.Burger;
import at.kaindorf.springburger.beans.Order;
import at.kaindorf.springburger.data.BurgerRepository;
import at.kaindorf.springburger.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.HashMap;

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
@SessionAttributes({"designBurger", "order"})
public class OrderController {
    // ToDo: implement POST-Mapping to process order
    // ToDo: Mapping of order input values to order-object

    private final OrderRepository orderRepository;
    private final BurgerRepository burgerRepository;

    public OrderController(OrderRepository orderRepository, BurgerRepository burgerRepository) {
        this.orderRepository = orderRepository;
        this.burgerRepository = burgerRepository;
    }

    @ModelAttribute
    public void setAttribute(Model model) {
        if (!model.containsAttribute("validationErrors")) {
            model.addAttribute("validationErrors", new HashMap<>());
        }
    }

    @GetMapping
    public String showOrderForm (Model model,
                                 @SessionAttribute("designBurger") Burger burger) {
        log.debug("GET request to /order: ");
        log.debug("Burger: " + burger);

        // Workaround for validation, should not be necessary when order is a sessionAttribute
        Object errors = model.getAttribute("org.springframework.validation.BindingResult.order");

        model.addAttribute("order", new Order());
        model.addAttribute("designBurger", burger);

        // Workaround for validation, should not be necessary when order is a sessionAttribute
        model.addAttribute("org.springframework.validation.BindingResult.order", errors);

        return "orderForm";
    }

    @PostMapping
    public ModelAndView performOrder(@Valid @ModelAttribute(name = "order") Order order,
                                     @SessionAttribute("designBurger") Burger burger,
                                     Errors errors) {
        log.debug("POST request to /order: " + order);

        if (errors.hasErrors()) {
            log.debug("Order error: " + errors);
            return new ModelAndView("orderForm");
        }

        order.addBurger(burger);
        orderRepository.save(order);
        burgerRepository.save(burger);

        return new ModelAndView("redirect:/design");
    }
}
