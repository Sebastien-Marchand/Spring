package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.config.JdbcTestConfig;
import dev.dao.PlatDaoJdbc;
import dev.entite.Plat;
import dev.config.ArticleRowMapper;

@SpringJUnitConfig({ JdbcTestConfig.class, PlatDaoJdbc.class })
@ActiveProfiles("jdbc")
public class PlatDaoJdbcIntegrationTest {

	
	@Autowired
	PlatDaoJdbc platDaoJdbc;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void listePlatsNonVide() {
		List<Plat> listePlats = platDaoJdbc.listerPlats();
		assertFalse(listePlats.isEmpty());
	}
/*
	@Test
	void testAjouterPlat() {
		Plat platAjoute = new Plat("plat 1", 1200);
		platDaoJdbc.ajouterPlat(platAjoute.getNom(), platAjoute.getPrixEnCentimesEuros());

		String sql = "SELECT * FROM plats where nom=?";
		Plat platRecupere = jdbcTemplate.queryForObject(sql, new Object[] { "plat 1" }, new ArticleRowMapper());

		assertThat(platAjoute).isEqualTo(platRecupere);
	}*/

}
