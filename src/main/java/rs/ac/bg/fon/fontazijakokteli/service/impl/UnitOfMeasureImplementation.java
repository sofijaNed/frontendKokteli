package rs.ac.bg.fon.fontazijakokteli.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.fontazijakokteli.dao.UnitOfMeasureRepository;
import rs.ac.bg.fon.fontazijakokteli.dto.GlassDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.IngredientDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.UnitOfMeasureDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.Glass;
import rs.ac.bg.fon.fontazijakokteli.entity.Ingredient;
import rs.ac.bg.fon.fontazijakokteli.entity.UnitOfMeasure;
import rs.ac.bg.fon.fontazijakokteli.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnitOfMeasureImplementation implements ServiceInterface<UnitOfMeasureDTO> {

    private UnitOfMeasureRepository unitOfMeasureRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UnitOfMeasureImplementation(UnitOfMeasureRepository unitOfMeasureRepository, ModelMapper modelMapper) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UnitOfMeasureDTO> findAll() {
        List<UnitOfMeasure> unitOfMeasures= unitOfMeasureRepository.findAll();
        List<UnitOfMeasureDTO> unitOfMeasureDTOS=new ArrayList<>();
        for(UnitOfMeasure unitOfMeasure:unitOfMeasures){
            UnitOfMeasureDTO unitOfMeasureDTO=modelMapper.map(unitOfMeasure, UnitOfMeasureDTO.class);
            unitOfMeasureDTOS.add(unitOfMeasureDTO);
        }
        return unitOfMeasureDTOS;
    }

    @Override
    public UnitOfMeasureDTO findById(Object id) throws Exception {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findById((Integer) id);
        UnitOfMeasureDTO unitOfMeasureDTO = modelMapper.map(unitOfMeasure, UnitOfMeasureDTO.class);

        return unitOfMeasureDTO;
    }

    @Override
    @Transactional
    public UnitOfMeasureDTO save(UnitOfMeasureDTO unitOfMeasureDTO) throws Exception {
        UnitOfMeasure unitOfMeasure = modelMapper.map(unitOfMeasureDTO, UnitOfMeasure.class);
        UnitOfMeasure savedUnitOfMeasure = unitOfMeasureRepository.save(unitOfMeasure);

        return modelMapper.map(savedUnitOfMeasure, UnitOfMeasureDTO.class);
    }
}
