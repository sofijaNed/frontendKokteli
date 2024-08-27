package rs.ac.bg.fon.fontazijakokteli.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.fontazijakokteli.dao.RecipeItemRepository;
import rs.ac.bg.fon.fontazijakokteli.dto.CocktailDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.IngredientDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.RecipeItemDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.UnitOfMeasureDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.RecipeItem;
import rs.ac.bg.fon.fontazijakokteli.entity.UnitOfMeasure;
import rs.ac.bg.fon.fontazijakokteli.entity.primaryKey.RecipeItemPK;
import rs.ac.bg.fon.fontazijakokteli.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeItemImplementation implements ServiceInterface<RecipeItemDTO> {
   private RecipeItemRepository recipeItemRepository;
   private ModelMapper modelMapper;

   @Autowired
    public RecipeItemImplementation(RecipeItemRepository recipeItemRepository, ModelMapper modelMapper) {
        this.recipeItemRepository = recipeItemRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RecipeItemDTO> findAll() {
        List<RecipeItem> recipeItems= recipeItemRepository.findAll();
        List<RecipeItemDTO> recipeItemDTOS=new ArrayList<>();
        for(RecipeItem recipeItem:recipeItems){
            RecipeItemDTO recipeItemDTO=modelMapper.map(recipeItem, RecipeItemDTO.class);
            recipeItemDTO.setMeasureDTO(modelMapper.map(recipeItem.getMeasure(), UnitOfMeasureDTO.class));
            recipeItemDTO.setCocktailDTO(modelMapper.map(recipeItem.getCocktail(), CocktailDTO.class));
            recipeItemDTO.setIngredientDTO(modelMapper.map(recipeItem.getIngredient(), IngredientDTO.class));
            recipeItemDTOS.add(recipeItemDTO);
        }
        return recipeItemDTOS;
    }

    @Override
    public RecipeItemDTO findById(Object id) throws Exception {
        Optional<RecipeItem> recipeItem = recipeItemRepository.findById((RecipeItemPK) id);
        RecipeItemDTO recipeItemDTO = modelMapper.map(recipeItem, RecipeItemDTO.class);

        return recipeItemDTO;

    }

    @Override
    @Transactional
    public RecipeItemDTO save(RecipeItemDTO recipeItemDTO) throws Exception {
        RecipeItem recipeItem = modelMapper.map(recipeItemDTO, RecipeItem.class);
        RecipeItem savedRecipeItem = recipeItemRepository.save(recipeItem);

        return modelMapper.map(savedRecipeItem, RecipeItemDTO.class);
    }


}
