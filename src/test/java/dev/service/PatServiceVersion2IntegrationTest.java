package dev.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import dev.dao.PlatDaoMemoire;
import dev.exception.PlatException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {PlatDaoMemoire.class, PlatServiceVersion2.class}) // construction du contexte
@ActiveProfiles({"memoire","service2"})

class PatServiceVersion2IntegrationTest {
	
	@Autowired
	private PlatServiceVersion2 PlatService;

	@Test
	void testAjouterPlat()
	{
		PlatService.ajouterPlat("Kebabs", 1500);
		assertThat(PlatService.listerPlats()).isNotEmpty();
	}
	@Test
	void testAjouterPlatinvalid()
	{
		assertThatThrownBy(() -> {
			PlatService.ajouterPlat("Kebabs", 10);
        }).isInstanceOf(PlatException.class)
				.hasMessage("le prix d'un plat doit être supérieur à 10 €");
	}

}
