package rs.ac.bg.fon.fontazijakokteli.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.fontazijakokteli.dto.CocktailDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.RecipeItemDTO;
import rs.ac.bg.fon.fontazijakokteli.service.impl.CocktailImplementation;
import rs.ac.bg.fon.fontazijakokteli.service.impl.CocktailImplementation;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailController {

    private CocktailImplementation cocktailImplementation;

    @Autowired
    public CocktailController(CocktailImplementation cocktailImplementation) {
        this.cocktailImplementation = cocktailImplementation;
    }

    @GetMapping
    public List<CocktailDTO> findAll(){
        return cocktailImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<CocktailDTO> findById(@PathVariable String id) throws Exception {
        return ResponseEntity.ok().body(cocktailImplementation.findById((String)id));
    }

    @PostMapping
    public ResponseEntity<CocktailDTO> save(@Valid @RequestBody CocktailDTO cocktailDTO) throws Exception {
        return new ResponseEntity<>(cocktailImplementation.save(cocktailDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/recipeItems")
    public List<RecipeItemDTO> getRecipeItems(@PathVariable String id) throws Exception {
        return cocktailImplementation.getRecipes(id);
    }


}
