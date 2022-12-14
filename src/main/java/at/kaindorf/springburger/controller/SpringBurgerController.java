package at.kaindorf.springburger.controller;

import at.kaindorf.springburger.beans.Burger;
import at.kaindorf.springburger.beans.Ingredient;
import at.kaindorf.springburger.data.BurgerRepository;
import at.kaindorf.springburger.data.IngredientRepository;
import at.kaindorf.springburger.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/design")
@DependsOn("dbInit")
@SessionAttributes({"designBurger"})
public class SpringBurgerController {
    private final IngredientRepository ingredientRepository;
    private final BurgerRepository burgerRepository;

    private final OrderRepository orderRepository;
    private static List<Ingredient> ingredients;
    private static final Map<String, List<Ingredient>> ingredientMap = new HashMap<>();

    /*static {
        ingredients = Arrays.asList(
                new Ingredient("120b", "120 g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("160b", "160 g Ground Beef", Ingredient.Type.PATTY),
                new Ingredient("140T", "140 g Turkey", Ingredient.Type.PATTY),
                new Ingredient("VEGG", "Veggie", Ingredient.Type.PATTY),
                new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient("SWIS", "Swiss Cheese", Ingredient.Type.CHEESE),
                new Ingredient("TOMA", "Tomato", Ingredient.Type.VEGGIE),
                new Ingredient("ONIO", "Onions", Ingredient.Type.VEGGIE),
                new Ingredient("SALA", "Salad", Ingredient.Type.VEGGIE)
        );
        for (Ingredient.Type type : Ingredient.Type.values()) {
            ingredientMap.put(type.toString().toLowerCase(), filterByType(type));
        }
    }*/

    public SpringBurgerController (IngredientRepository ingredientRepository,
                                   BurgerRepository burgerRepository,
                                   OrderRepository orderRepository) {
        log.debug("Create Spring-burger Controller");
        this.ingredientRepository = ingredientRepository;
        this.burgerRepository = burgerRepository;
        this.orderRepository = orderRepository;

    }

    @PostConstruct
    private void loadIngredients () {
        log.debug("load Ingredients in Spring-burger Controller");
        ingredients = ingredientRepository.findAll();

        for (Ingredient.Type type : Ingredient.Type.values()) {
            ingredientMap.put(type.toString().toLowerCase(), filterByType(type));
        }
    }

    //every time if any request Mapping is done [get, post, ...]
    @ModelAttribute
    public void setAttribute(Model model) {
        model.addAttribute("ingredientMap", ingredientMap);
    }

    @ModelAttribute("designBurger")
    public Burger burger () {
        return new Burger();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        //model.addAttribute("ingredients",ingredientMap);

        log.info("GET request to /design");
        //model.addAttribute("designBurger", new Burger());

        //Name of the html file
        return "designForm";
    }


    @PostMapping
    public ModelAndView processDesignBurger(@Valid @ModelAttribute("designBurger") Burger burger,
                                            Errors errors) {
        log.debug("POST request to /design: " + burger);


        if (errors.hasErrors()) {
            log.debug("Burger error: " + errors);

            /*attributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.designBurger", errors);*/

            // Don't use this way, use the build in way
            // attributes.addFlashAttribute("validationErrors", getErrorMessages(errors));

            // ToDo: "name" -> "Name must be at least 7 characters long"
            // ToDo: "ingredients" -> "Ingredients must have at least 3 Items"

            return new ModelAndView("designForm");
        }

        burgerRepository.save(burger);

        //return "redirect:/order";
        return new ModelAndView("redirect:/order");
    }

    private static List<Ingredient> filterByType(Ingredient.Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(Collectors.toList());
    }

    public static Map<String, String> getErrorMessages(BindingResult errors) {
        Map<String, String> errorMap = new HashMap<>();
        errors.getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }
}
