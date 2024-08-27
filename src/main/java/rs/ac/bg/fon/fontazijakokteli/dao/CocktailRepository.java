package rs.ac.bg.fon.fontazijakokteli.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.fontazijakokteli.entity.Cocktail;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, String> {
}
