package rs.ac.bg.fon.fontazijakokteli.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.fontazijakokteli.dto.GlassDTO;
import rs.ac.bg.fon.fontazijakokteli.dto.UnitOfMeasureDTO;
import rs.ac.bg.fon.fontazijakokteli.service.impl.UnitOfMeasureImplementation;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class UnitOfMeasureController {

    private UnitOfMeasureImplementation unitOfMeasureImplementation;

    @Autowired
    public UnitOfMeasureController(UnitOfMeasureImplementation unitOfMeasureImplementation) {
        this.unitOfMeasureImplementation = unitOfMeasureImplementation;
    }

    @GetMapping
    public List<UnitOfMeasureDTO> findAll(){
        return unitOfMeasureImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<UnitOfMeasureDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(unitOfMeasureImplementation.findById((Integer)id));
    }

    @PostMapping
    public ResponseEntity<UnitOfMeasureDTO> save(@Valid @RequestBody UnitOfMeasureDTO unitOfMeasureDTO) throws Exception {
        return new ResponseEntity<>(unitOfMeasureImplementation.save(unitOfMeasureDTO), HttpStatus.CREATED);
    }

}
