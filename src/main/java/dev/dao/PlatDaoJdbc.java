package dev.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import dev.config.ArticleRowMapper;
import dev.entite.Plat;

@Repository
@Profile("jdbc")
public class PlatDaoJdbc implements IPlatDao{

	private JdbcTemplate jdbcTemplate;
	
	public PlatDaoJdbc(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	@Override
	public List<Plat> listerPlats() {

		return this.jdbcTemplate.query("select * from plat",new ArticleRowMapper());
	}

	@Override
	public void ajouterPlat(String nomPlat, Integer prixPlat) {
		String sql ="INSERT INTO plat ( nom, prix ) values (? , ? )";
		jdbcTemplate.update(sql, nomPlat, prixPlat);
	}

}
