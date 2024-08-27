package rs.ac.bg.fon.fontazijakokteli.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.fontazijakokteli.dao.*;
import rs.ac.bg.fon.fontazijakokteli.dao.CocktailRepository;
import rs.ac.bg.fon.fontazijakokteli.dto.*;
import rs.ac.bg.fon.fontazijakokteli.entity.Cocktail;
import rs.ac.bg.fon.fontazijakokteli.entity.Ingredient;
import rs.ac.bg.fon.fontazijakokteli.entity.RecipeItem;
import rs.ac.bg.fon.fontazijakokteli.entity.primaryKey.RecipeItemPK;
import rs.ac.bg.fon.fontazijakokteli.service.ServiceInterface;
import org.modelmapper.ModelMapper;

import java.util.*;

@Service
public class CocktailImplementation implements ServiceInterface<CocktailDTO> {

    private CocktailRepository cocktailRepository;
    private RecipeItemRepository recipeItemRepository;
    private ModelMapper modelMapper;
    private IngredientRepository ingredientRepository;

    @Autowired
    public CocktailImplementation(CocktailRepository cocktailRepository, RecipeItemRepository recipeItemRepository, ModelMapper modelMapper, IngredientRepository ingredientRepository) {
        this.cocktailRepository = cocktailRepository;
        this.recipeItemRepository = recipeItemRepository;
        this.modelMapper = modelMapper;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<CocktailDTO> findAll() {
        List<Cocktail> cocktails = cocktailRepository.findAll();
        List<CocktailDTO> cocktailDTOS = new ArrayList<>();

        for(Cocktail cocktail: cocktails){

            CocktailDTO cocktailDTO = modelMapper.map(cocktail, CocktailDTO.class);
            cocktailDTO.setGlassDTO(modelMapper.map(cocktail.getGlass(), GlassDTO.class));
            cocktailDTO.setRecipeItems(getRecipes(cocktail.getCocktailName()));
            cocktailDTOS.add(cocktailDTO);
        }
        return cocktailDTOS;
    }

    @Override
    public CocktailDTO findById(Object id) throws Exception {
        Optional<Cocktail> cocktail=cocktailRepository.findById((String) id);
        CocktailDTO cocktailDTO;
        if(cocktail.isPresent()) {
            cocktailDTO = modelMapper.map(cocktail.get(), CocktailDTO.class);
            cocktailDTO.setGlassDTO(modelMapper.map(cocktail.get().getGlass(), GlassDTO.class));
            cocktailDTO.setRecipeItems(getRecipes(cocktail.get().getCocktailName()));
            return cocktailDTO;
        }
        else{
            throw new Exception("Ne postoji čaša");
        }
    }

    @Override
    @Transactional
    public CocktailDTO save(CocktailDTO cocktailDTO) throws Exception {

        Cocktail cocktail = modelMapper.map(cocktailDTO, Cocktail.class);
        //Collection<RecipeItem> recipeItemCollection = modelMapper.map(cocktailDTO.getRecipeItems(), Cocktail.class).getRecipeItemCollection();
        Cocktail savedCocktail = cocktailRepository.save(cocktail);
        Ingredient ingredient = new Ingredient();

        //System.out.println(recipeItemCollection);
        //if(recipeItemCollection != null) {
            for (RecipeItemDTO ri : cocktailDTO.getRecipeItems()) {

                RecipeItem recipeItem = modelMapper.map(ri, RecipeItem.class);
                if(ri.getIngredientDTO().getIngredientID() == -1){

                    Ingredient ingredient1 = new Ingredient();
                    ingredient1.setName(recipeItem.getIngredient().getName());
                    ingredient = ingredientRepository.save(ingredient1);
                }else {
                    ingredient = ingredientRepository.save(modelMapper.map(ri.getIngredientDTO(), Ingredient.class));

                }
                RecipeItemPK recipeItemPK = new RecipeItemPK(savedCocktail.getCocktailName(), ingredient.getIngredientID());
                recipeItem.setItemID(recipeItemPK);
                recipeItem.getIngredient().setIngredientID(ingredient.getIngredientID());

                if(recipeItem.getIngredient().getIngredientID() != -1) {
                    recipeItemRepository.save(recipeItem);
                }

            }
        //}
        return modelMapper.map(savedCocktail, CocktailDTO.class);
    }

//    public CocktailDTO save(CocktailDTO cocktailDTO) throws Exception {
//        Cocktail cocktail = modelMapper.map(cocktailDTO, Cocktail.class);
//        Cocktail savedCocktail = cocktailRepository.save(cocktail);
//
//        for (RecipeItemDTO ri : cocktailDTO.getRecipeItems()) {
//            RecipeItem recipeItem = modelMapper.map(ri, RecipeItem.class);
//
//            // Provera da li je ID sastojka -1
//            if (ri.getIngredientDTO().getIngredientID() == -1) {
//                // Kreiranje novog sastojka
//                Ingredient newIngredient = new Ingredient();
//                newIngredient.setName(ri.getIngredientDTO().getName()); // Postavljanje imena sastojka
//
//                // Čuvanje novog sastojka u bazi podataka
//                Ingredient savedIngredient = ingredientRepository.save(newIngredient);
//
//                // Postavljanje primarnog ključa za stavku recepta
//                RecipeItemPK recipeItemPK = new RecipeItemPK(savedCocktail.getCocktailName(), savedIngredient.getIngredientID());
//                recipeItem.setItemID(recipeItemPK);
//            } else {
//                // Ako sastojak već postoji u bazi, koristimo postojeći ID
//                RecipeItemPK recipeItemPK = new RecipeItemPK(savedCocktail.getCocktailName(), ri.getIngredientDTO().getIngredientID());
//                recipeItem.setItemID(recipeItemPK);
//            }
//
//            // Čuvanje stavke recepta
//            recipeItemRepository.save(recipeItem);
//        }
//
//        return modelMapper.map(savedCocktail, CocktailDTO.class);
//    }

//    public CocktailDTO save(CocktailDTO cocktailDTO) throws Exception {
//        Cocktail cocktail = modelMapper.map(cocktailDTO, Cocktail.class);
//        Cocktail savedCocktail = cocktailRepository.save(cocktail);
//
//        for (RecipeItemDTO ri : cocktailDTO.getRecipeItems()) {
//            RecipeItem recipeItem = modelMapper.map(ri, RecipeItem.class);
//
//            // Provera da li je ID sastojka -1
//            if (ri.getIngredientDTO().getIngredientID() == -1) {
//                // Kreiranje novog sastojka
//                Ingredient newIngredient = new Ingredient();
//                newIngredient.setName(ri.getIngredientDTO().getName()); // Postavljanje imena sastojka
//
//                newIngredient.setIngredientID(null);
//
//                // Čuvanje novog sastojka u bazi podataka
//                Ingredient savedIngredient = ingredientRepository.save(newIngredient);
//
//                // Postavljanje primarnog ključa za stavku recepta
//                RecipeItemPK recipeItemPK = new RecipeItemPK(savedCocktail.getCocktailName(), savedIngredient.getIngredientID());
//                recipeItem.setItemID(recipeItemPK);
//            } else {
//                // Pokušaj pronalaženja postojećeg sastojka
//                Optional<Ingredient> existingIngredientOptional = ingredientRepository.findById(ri.getIngredientDTO().getIngredientID());
//
//                if (existingIngredientOptional.isPresent()) {
//                    // Ako sastojak postoji u bazi, koristi postojeći sastojak
//                    Ingredient existingIngredient = existingIngredientOptional.get();
//
//                    // Postavljanje primarnog ključa za stavku recepta
//                    RecipeItemPK recipeItemPK = new RecipeItemPK(savedCocktail.getCocktailName(), existingIngredient.getIngredientID());
//                    recipeItem.setItemID(recipeItemPK);
//                } else {
//                    // Ako sastojak ne postoji u bazi, izbaci izuzetak ili obradi grešku kako je potrebno
//                    throw new EntityNotFoundException("Ingredient with ID " + ri.getIngredientDTO().getIngredientID() + " not found.");
//                }
//            }
//
//            // Čuvanje stavke recepta
//            recipeItemRepository.save(recipeItem);
//        }
//
//        return modelMapper.map(savedCocktail, CocktailDTO.class);
//    }



    public List<RecipeItemDTO> getRecipes(String cocktailName){

        List<RecipeItem> recipeItems = recipeItemRepository.findByCocktailCocktailName(cocktailName);
        List<RecipeItemDTO> recipeItemDTOS = new ArrayList<>();

        for(RecipeItem ri: recipeItems){
            RecipeItemDTO recipeItemDTO = modelMapper.map(ri, RecipeItemDTO.class);
            recipeItemDTO.setMeasureDTO(modelMapper.map(ri.getMeasure(), UnitOfMeasureDTO.class));
            recipeItemDTO.setCocktailDTO(modelMapper.map(ri.getCocktail(), CocktailDTO.class));
            recipeItemDTO.setIngredientDTO(modelMapper.map(ri.getIngredient(), IngredientDTO.class));
            recipeItemDTOS.add(recipeItemDTO);
        }
        return recipeItemDTOS;
    }
}
