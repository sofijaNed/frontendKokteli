package rs.ac.bg.fon.fontazijakokteli.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.fontazijakokteli.dto.IngredientDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.RecipeItemDTO;
import rs.ac.bg.fon.fontazijakokteli.service.impl.IngredientImplementation;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private IngredientImplementation ingredientImplementation;

    @Autowired
    public IngredientController(IngredientImplementation ingredientImplementation) {
        this.ingredientImplementation = ingredientImplementation;
    }

    @GetMapping
    public List<IngredientDTO> findAll(){
        return ingredientImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<IngredientDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(ingredientImplementation.findById((Integer)id));
    }

    @PostMapping
    public ResponseEntity<IngredientDTO> save(@Valid @RequestBody IngredientDTO ingredientDTO) throws Exception {
        return new ResponseEntity<>(ingredientImplementation.save(ingredientDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/recipeItems")
    public List<RecipeItemDTO> getRecipeItems(@PathVariable Integer id) throws Exception {
        return ingredientImplementation.getRecipes(id);
    }

}
