package dev.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import dev.dao.PlatDaoFichier;
import dev.entite.Plat;

@SpringJUnitConfig(PlatDaoFichier.class)
@ActiveProfiles({"fichier"})
@TestPropertySource("classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PlatDaoFichierTest {
	
	@Autowired
	private PlatDaoFichier platDao;
	
	@Test
	void testAjouterPlat()
	{
		Plat monPlat = new Plat("kebabs" ,1500);
		List<Plat> laListePlat = new ArrayList<>();
		laListePlat.add(monPlat);
		platDao.ajouterPlat("kebabs", 1500);
		assertThat(platDao.listerPlats()).isEqualTo(laListePlat);
	}
	/**
	 * Permet de tester indépendant des test réalisé grace a
	 * l'annotation @DirtiesContext
	 * 
	 */
	@Test
	void TestAjouterPlatIndépendant() {
		Plat plat1 = new Plat("falafel", 1800);
		List<Plat> listPlat = new ArrayList<>();
		listPlat.add(plat1);
		platDao.ajouterPlat("falafel", 1800);
		assertThat(platDao.listerPlats()).isEqualTo(listPlat);
	}
}
