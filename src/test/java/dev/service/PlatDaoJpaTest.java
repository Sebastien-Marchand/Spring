package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.DataSourceH2TestConfig;
import dev.config.JpaConfig;
import dev.dao.PlatDaoJpa;
import dev.entite.Plat;

//Creation du context
@SpringJUnitConfig({ JpaConfig.class, PlatDaoJpa.class, DataSourceH2TestConfig.class })
@ActiveProfiles({ "jpa", "Service1" })
@TestPropertySource("classpath:test.properties")
class PlatDaoJpaTest {

	@Autowired
	PlatDaoJpa platDaoJpa;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void listePlatsNonVide() {
		List<Plat> listePlats = platDaoJpa.listerPlats();
		assertFalse(listePlats.isEmpty());
	}
	
	@Test
	void testAjouterPlat() {
		Plat ajout = new Plat("bruschetta", 1500);
		platDaoJpa.ajouterPlat(ajout.getNom(), ajout.getPrixEnCentimesEuros());
		assertThat(platDaoJpa.listerPlats()).contains(ajout);
	}
}
