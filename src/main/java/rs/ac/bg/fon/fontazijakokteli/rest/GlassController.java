package rs.ac.bg.fon.fontazijakokteli.rest;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.fontazijakokteli.dto.GlassDTO;
import rs.ac.bg.fon.fontazijakokteli.service.impl.GlassImplementation;

import java.util.List;

@RestController
@RequestMapping("/glasses")
public class GlassController {

    private GlassImplementation glassImplementation;

    @Autowired
    public GlassController(GlassImplementation glassImplementation) {
        this.glassImplementation = glassImplementation;
    }

    @GetMapping
    public List<GlassDTO> findAll(){
        return glassImplementation.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<GlassDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(glassImplementation.findById((Integer)id));
    }

    @PostMapping
    public ResponseEntity<GlassDTO> save(@Valid @RequestBody GlassDTO glassDTO) throws Exception {
        return new ResponseEntity<>(glassImplementation.save(glassDTO), HttpStatus.CREATED);
    }



}
