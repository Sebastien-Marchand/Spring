package dev;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.ihm.Menu;

public class AppSpringXML {

	public static void main(String[] args) {

		//Création du contexte Spring
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-memoire.xml");
		//Récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		
		menu.afficher();
		
		//Fermeture du Scanner
		context.getBean(Scanner.class).close();
		
		//Fermeture du contexte Spring
		context.close();

	}
}