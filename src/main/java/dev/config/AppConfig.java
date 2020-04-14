package dev.config;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import dev.dao.PlatDaoFichier;
import dev.dao.PlatDaoMemoire;
import dev.ihm.Menu;
import dev.service.PlatServiceVersion1;
import dev.service.PlatServiceVersion2;

@Configuration

@ComponentScan("dev.dao")
@ComponentScan("dev.service")
@ComponentScan("dev.ihm")
@PropertySource("app.properties")
@Import({DataSourceConfig.class,JpaConfig.class })
public class AppConfig {
	
	@Bean
	public Scanner beanScanner() {
		return new Scanner(System.in);
	}

}
