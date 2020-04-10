package dev.config;

import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArticleRowMapper implements RowMapper<Plat> {

    @Override
    public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
    	
    	Plat article = new Plat();
        article.setNom(rs.getString("nom"));;
        article.setPrixEnCentimesEuros(rs.getInt("prix"));
        return article;
        
    }
}
