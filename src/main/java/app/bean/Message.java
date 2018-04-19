package app.bean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.redis.core.RedisHash;

public class Message implements Serializable {

	private String uuid;
	private String contenu;
	private LocalDateTime dateTime;
	private List<String> listeHashtag;
	private String uuidUtilisateur;
	private String pseudoUtilisateur;

	public Message(String contenu) {
		this.uuid = UUID.randomUUID().toString();
		this.contenu = contenu;
		this.dateTime = LocalDateTime.now();
		Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
		Matcher mat = MY_PATTERN.matcher(contenu);
		listeHashtag = new ArrayList<String>();
		while (mat.find()) {
			listeHashtag.add(mat.group(1));
		}
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public List<String> getListeHashtag() {
		return listeHashtag;
	}

	public void setListeHashtag(List<String> listeHashtag) {
		this.listeHashtag = listeHashtag;
	}

	public String getUuidUtilisateur() {
		return uuidUtilisateur;
	}

	public void setUuidUtilisateur(String uuidUtilisateur) {
		this.uuidUtilisateur = uuidUtilisateur;
	}

	public String getPseudoUtilisateur() {
		return pseudoUtilisateur;
	}

	public void setPseudoUtilisateur(String pseudoUtilisateur) {
		this.pseudoUtilisateur = pseudoUtilisateur;
	}

	public int compare(Message m2) {
		LocalDateTime t2 = m2.getDateTime();
		if (dateTime.isBefore(t2)) {
			return -1;
		} else if (dateTime.isAfter(t2)) {
			return 1;
		} else {
			return 0;
		}
	}

}
