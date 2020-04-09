package dev.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import dev.dao.IPlatDao;
import dev.dao.PlatDaoMemoire;
import dev.exception.PlatException;

class PlatServiceVersion1Test {

	
	private PlatDaoMemoire platDaoMemoire;
	
	@Mock
    IPlatDao platDao; // mock(IPlatDao.class)
	@InjectMocks
    PlatServiceVersion1 platServiceVersion1; // new PlatServiceVersion(platDao)
	/*
	@BeforeEach
	void setUp() throws Exception {
		platDaoMemoire = mock(PlatDaoMemoire.class);
		this.PlatServiceVersion = new PlatServiceVersion1(platDaoMemoire);
	}
*/
	@Test
	void ajouterPlat3carac() {
		
		 assertThatThrownBy(() -> {
			 platDaoMemoire.ajouterPlat("a", 2500);
	        })
	                .isInstanceOf(PlatException.class)
	                .hasMessage("un plat doit avoir un nom de plus de 3 caractères");
	}
	
	@Test
    void AjouterPlatPrixInvalide() {

        assertThatThrownBy(() -> {
        	platDaoMemoire.ajouterPlat("moulesfrites", 10);
        }).isInstanceOf(PlatException.class)
				.hasMessage("le prix d'un plat doit être supérieur à 5 €");

    }

    @Test
    void AjouterPlat() {

    	platDaoMemoire.ajouterPlat("moulesfrites", 1200);
        
        verify(platDao).ajouterPlat("moulesfrites", 1200);
    }

}
