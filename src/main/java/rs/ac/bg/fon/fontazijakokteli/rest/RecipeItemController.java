package rs.ac.bg.fon.fontazijakokteli.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.fontazijakokteli.dto.RecipeItemDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.UnitOfMeasureDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.primaryKey.RecipeItemPK;
import rs.ac.bg.fon.fontazijakokteli.service.impl.RecipeItemImplementation;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeItemController {

    private RecipeItemImplementation recipeItemImplementation;

    @Autowired
    public RecipeItemController(RecipeItemImplementation recipeItemImplementation) {
        this.recipeItemImplementation = recipeItemImplementation;
    }

    @GetMapping
    public List<RecipeItemDTO> findAll(){
        return recipeItemImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<RecipeItemDTO> findById(@PathVariable RecipeItemPK id) throws Exception {
        return ResponseEntity.ok().body(recipeItemImplementation.findById((RecipeItemPK)id));
    }

    @PostMapping
    public ResponseEntity<RecipeItemDTO> save(@Valid @RequestBody RecipeItemDTO recipeItemDTO) throws Exception {
        return new ResponseEntity<>(recipeItemImplementation.save(recipeItemDTO), HttpStatus.CREATED);
    }
}
