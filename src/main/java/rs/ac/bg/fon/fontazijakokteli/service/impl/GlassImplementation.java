package rs.ac.bg.fon.fontazijakokteli.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.fontazijakokteli.dao.GlassRepository;
import rs.ac.bg.fon.fontazijakokteli.dto.GlassDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.Glass;
import rs.ac.bg.fon.fontazijakokteli.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GlassImplementation implements ServiceInterface<GlassDTO> {
    private GlassRepository glassRepository;
    private ModelMapper modelMapper;

    @Autowired
    public GlassImplementation(GlassRepository glassRepository, ModelMapper modelMapper) {
        this.glassRepository = glassRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<GlassDTO> findAll() {
        List<Glass> glasses= glassRepository.findAll();
        List<GlassDTO> glassDTOS=new ArrayList<>();
        for(Glass glass:glasses){
            GlassDTO glassDTO=modelMapper.map(glass, GlassDTO.class);
            //glassDTO.set(modelMapper.map(coctail.getGlass(), GlassDTO.class));
            glassDTOS.add(glassDTO);
        }
        return glassDTOS;
    }

    @Override
    public GlassDTO findById(Object id) throws Exception {
        Optional<Glass> glass=glassRepository.findById((Integer) id);
        GlassDTO glassDTO;
        if(glass.isPresent()) {
            glassDTO = modelMapper.map(glass.get(), GlassDTO.class);
            return glassDTO;
        }
        else{
            throw new Exception("Ne postoji čaša");
        }
    }


    @Override
    @Transactional
    public GlassDTO save(GlassDTO glassDTO) throws Exception {
        Glass glass = modelMapper.map(glassDTO, Glass.class);
        Glass savedGlass = glassRepository.save(glass);

        return modelMapper.map(savedGlass, GlassDTO.class);
    }

}
