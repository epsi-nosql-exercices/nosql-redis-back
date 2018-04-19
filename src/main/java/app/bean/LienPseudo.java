package app.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("LienPseudo")
public class LienPseudo implements Serializable {
	
	@Id
	private String pseudo;
	private String uuidUti;
	
	public LienPseudo(String pseudo, String uuidUti) {
		super();
		this.pseudo = pseudo;
		this.uuidUti = uuidUti;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getUuidUti() {
		return uuidUti;
	}
	public void setUuidUti(String uuidUti) {
		this.uuidUti = uuidUti;
	} 
	
	

}
