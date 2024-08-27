package rs.ac.bg.fon.fontazijakokteli.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.fontazijakokteli.dto.UnitOfMeasureDTO;
import rs.ac.bg.fon.fontazijakokteli.entity.UnitOfMeasure;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Integer> {
}
