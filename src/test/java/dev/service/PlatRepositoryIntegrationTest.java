package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.entite.Ingredient;
import dev.entite.Plat;
import dev.repository.PlatRepository;

@Transactional 
@SpringJUnitConfig({ JpaConfig.class, DataSourceH2TestConfig.class})
@ActiveProfiles("jpa")
class PlatRepositoryIntegrationTest {

	private static final Logger LOGGER = Logger.getLogger(PlatRepositoryIntegrationTest.class.getName());

	@Autowired
	PlatRepository platRp;
	
	@Test
	void testFindAll() {
		List<Plat> listePlats = platRp.findAll();
		assertFalse(listePlats.isEmpty());
	}
	

	@Test
	void testFindAllSortAsc()
	{
		List<Plat> listePlats = platRp.findAll(Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).ascending());
		LOGGER.info("Sort Asc:");
		for(Plat leplat: listePlats)
		{
			LOGGER.info("Prix :" + leplat.getPrixEnCentimesEuros());

		}
		assertFalse(listePlats.isEmpty());
	}
	
	@Test
	void testFindAllSortDesc()
	{
		List<Plat> listePlats = platRp.findAll(Sort.sort(Plat.class).by(Plat::getPrixEnCentimesEuros).descending());
		LOGGER.info("Sort Desc:");
		for(Plat leplat: listePlats)
		{
			LOGGER.info("Prix :" + leplat.getPrixEnCentimesEuros());

		}
		assertFalse(listePlats.isEmpty());
	}
	
	@Test
	void testFindAllPageable()
	{
		Pageable p1 = PageRequest.of(0, 2, Sort.sort(Plat.class).by(Plat::getNom).ascending());
		Page<Plat> pagePlats =  platRp.findAll(p1);
		List<Plat> listePlats = pagePlats.toList();
		LOGGER.info("Sort pageable Asc:");
		for(Plat leplat: listePlats)
		{
			LOGGER.info("Nom :" + leplat.getNom());

		}
		assertFalse(listePlats.isEmpty());
	}
	
	@Test
	void testFindById()
	{
		List<Plat> listePlats = platRp.findById(2).stream().collect(Collectors.toList());
		LOGGER.info("FindById:");
		for(Plat leplat: listePlats)
		{
			LOGGER.info("Nom :" + leplat.getNom());

		}
		assertFalse(listePlats.isEmpty());
	}
	
	@Test
	@Transactional
	void testGetOne()
	{
		Plat lePlat = platRp.getOne(2);
		LOGGER.info("GetOne:");
		LOGGER.info("Nom :" + lePlat.getNom());
		assertThat(lePlat.getNom()).isEqualTo("Moules-frites");
	}
	
	@Test
	void testCount()
	{
		long nbPlat = platRp.count();
		LOGGER.info("Count:");
		LOGGER.info("Nb plat :" + nbPlat);
		assertThat(nbPlat).isNotZero();
	}
	
	@Test
	void testFindByPrix()
	{
		List<Plat> listePlats = platRp.findByPrixEnCentimesEuros(1500);
		LOGGER.info("FindByPrix:");

		for(Plat leplat: listePlats)
		{
			LOGGER.info("Nom :" + leplat.getNom());

		}
		assertThat(listePlats.isEmpty());
	}
	
	@Test
	void testAvgPrix()
	{
		long moyenne = platRp.AvgPrix(1500);
		LOGGER.info("AvgPrix:"+moyenne);
		assertThat(moyenne).isNotNull();
	}
	
	@Test
	void testFindByNomWithIngredients()
	{
		List<Ingredient> ingredients = platRp.findByNom("Moules-frites");
		for(Ingredient lingredient: ingredients)
		{
			LOGGER.info("Nom ingrédient :" + lingredient.getNom());
		}
		assertThat(ingredients.size()).isEqualTo(6);
	}
	
	@Test
    void testSave() {
        Plat p1 = new Plat("Kebab-frites", 1500);
        platRp.save(p1);
        LOGGER.info("Save plat :" + platRp.findById(7).get().getNom());
        assertThat(platRp.findById(7).get().getNom()).isEqualTo("Kebab-frites");
    }

	@Test
    void testChangerNom() {
		platRp.ChangerNom("père_dodu", "Côte de boeuf");
        LOGGER.info("Nouveau nom :" + platRp.findAll().get(4).getNom());
        assertThat(platRp.findAll().get(4).getNom()).isEqualTo("père_dodu");
    }
	
}
