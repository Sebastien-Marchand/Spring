package dev.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.dao.PlatDaoMemoire;

class PlatDaoMemoireTest {

	private PlatDaoMemoire platDaoMemoire;
	
	@BeforeEach
	void setUp() {
		this.platDaoMemoire = new PlatDaoMemoire();
	}

	@Test
	void LissterPlatsVideInitialisation() {
		assertThat(platDaoMemoire.listerPlats()).isEmpty();
	}
	
	@Test
	void ajouterPlatCasPassants() {
		
		platDaoMemoire.ajouterPlat("Kebab", 500);
		assertThat(platDaoMemoire.listerPlats()).isNotEmpty();
	}

}
