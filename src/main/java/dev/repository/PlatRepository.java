package dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.entite.Ingredient;
import dev.entite.Plat;

public interface PlatRepository extends JpaRepository<Plat,Integer>{

	List<Plat> findByPrixEnCentimesEuros(Integer prixEnCentimesEuros);
	
	@Query("SELECT AVG(p.prixEnCentimesEuros) FROM Plat p where prix >:choix")
	long AvgPrix(@Param("choix") int choix);
	
	@Query("SELECT p.ingredients FROM Plat p where p.nom = :choixnom")
	List<Ingredient> findByNom(@Param("choixnom") String choixnom);
	
	@Modifying
	@Query("UPDATE Plat p " + "SET p.nom = :choixNewNom " + "WHERE p.nom = :choixNom")
	void ChangerNom(@Param("choixNewNom") String choixNewNom, @Param("choixNom") String choixNom);
}
