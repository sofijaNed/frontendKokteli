package rs.ac.bg.fon.fontazijakokteli.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.fontazijakokteli.dao.IngredientRepository;
import rs.ac.bg.fon.fontazijakokteli.dao.RecipeItemRepository;
import rs.ac.bg.fon.fontazijakokteli.dto.IngredientDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.RecipeItemDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.Ingredient;
import rs.ac.bg.fon.fontazijakokteli.entity.RecipeItem;
import rs.ac.bg.fon.fontazijakokteli.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientImplementation implements ServiceInterface<IngredientDTO> {

    private IngredientRepository ingredientRepository;

    private RecipeItemRepository recipeItemRepository;

    private ModelMapper modelMapper;

    @Autowired
    public IngredientImplementation(IngredientRepository ingredientRepository, ModelMapper modelMapper, RecipeItemRepository recipeItemRepository) {
        this.ingredientRepository = ingredientRepository;
        this.modelMapper = modelMapper;
        this.recipeItemRepository = recipeItemRepository;
    }

    @Override
    public List<IngredientDTO> findAll() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();

        for(Ingredient ingredient: ingredients){

            IngredientDTO ingredientDTO = modelMapper.map(ingredient, IngredientDTO.class);
            ingredientDTOS.add(ingredientDTO);
        }
        return ingredientDTOS;
    }

    @Override
    public IngredientDTO findById(Object id) throws Exception {
        Optional<Ingredient> ingredient = ingredientRepository.findById((Integer) id);
        IngredientDTO ingredientDTO;
        if(ingredient.isPresent()) {
            ingredientDTO = modelMapper.map(ingredient.get(), IngredientDTO.class);
            return ingredientDTO;
        }
        else{
            throw new Exception("Ne postoji čaša");
        }
    }

    @Override
    @Transactional
    public IngredientDTO save(IngredientDTO ingredientDTO) throws Exception {
        Ingredient ingredient = modelMapper.map(ingredientDTO, Ingredient.class);
        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return modelMapper.map(savedIngredient, IngredientDTO.class);
    }

    public List<RecipeItemDTO> getRecipes(Integer ingredientID){

        List<RecipeItem> recipeItems = recipeItemRepository.findByIngredientIngredientID(ingredientID);
        List<RecipeItemDTO> recipeItemDTOS = new ArrayList<>();

        for(RecipeItem ri: recipeItems){
            RecipeItemDTO recipeItemDTO = modelMapper.map(ri, RecipeItemDTO.class);
            recipeItemDTOS.add(recipeItemDTO);
        }
        return recipeItemDTOS;
    }
}
