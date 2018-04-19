package app;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import app.adaptateur.Adaptateur;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig(){
		register(Adaptateur.class);
	}
}
