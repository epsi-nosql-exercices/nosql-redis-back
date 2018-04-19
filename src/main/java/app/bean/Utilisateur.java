package app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Utilisateur")
public class Utilisateur implements Serializable {

	@Id
	private String uuid;
	private String email;
	private String motDePasse;
	private String pseudo;
	private List<Message> listeMessage;
	private List<String> listeFollower;
	private List<String> listeFollowing;
	
	

	public Utilisateur(String email, String motDePasse, String pseudo) {
		this.uuid = UUID.randomUUID().toString();
		this.email = email;
		this.motDePasse = motDePasse;
		this.pseudo = pseudo;
		this.listeMessage = new ArrayList<>();
		this.listeFollower = new ArrayList<>();
		this.listeFollowing = new ArrayList<>();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public List<Message> getListeMessage() {
		return listeMessage;
	}

	public void setListeMessage(List<Message> listeMessage) {
		this.listeMessage = listeMessage;
	}

	public List<String> getListeFollower() {
		return listeFollower;
	}

	public void setListeFollower(List<String> listeFollower) {
		this.listeFollower = listeFollower;
	}

	public List<String> getListeFollowing() {
		return listeFollowing;
	}

	public void setListeFollowing(List<String> listeFollowing) {
		this.listeFollowing = listeFollowing;
	}

	@Override
	public String toString() {
		return "Utilisateur [uuid=" + uuid + ", email=" + email + ", motDePasse=" + motDePasse + ", pseudo=" + pseudo;
	}
	
	

}
