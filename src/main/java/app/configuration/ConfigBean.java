package app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import app.gestion.GestionnaireUtilisateur;

/** Config des beans */
@Configuration
public class ConfigBean {

	@Bean
	public GestionnaireUtilisateur gestionnaireUtilisateur() {
		return new GestionnaireUtilisateur();
	}

}
