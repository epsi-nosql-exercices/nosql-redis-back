package app.adaptateur;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.bean.Message;
import app.bean.Utilisateur;
import app.gestion.GestionnaireUtilisateur;

/**
 * Point d'entrée (et déclaration) des services REST pour les utilisateurs,
 * appelle les fonctions métier sous-jacentes. Chemin du service REST est
 * définit par /users
 *
 * @author alexm
 *
 */
@Component
@Path("/noSQL")
@Produces(MediaType.APPLICATION_JSON)
public class Adaptateur {

	@Autowired
	private GestionnaireUtilisateur gestionnaireUtilisateur;

	@POST
	@Path("utilisateur")
	public Response addUtilisateur(@FormParam("email") String email, @FormParam("motDePasse") String motDePasse,
			@FormParam("pseudo") String pseudo) {
                System.out.println("addUtilisateur");
                System.out.println(gestionnaireUtilisateur.findByPseudo(pseudo));
                if (gestionnaireUtilisateur.findByPseudo(pseudo) == null) {
                        Utilisateur utilisateur = new Utilisateur(email, motDePasse, pseudo);
                        System.out.println(utilisateur.toString());
                        gestionnaireUtilisateur.addUtilisateur(utilisateur);
                        return Response.ok(utilisateur).build();
                }
		return Response.status(Status.CONFLICT).build();
	}

	@POST
	@Path("utilisateur/auth")
	public Response authentification(@FormParam("pseudo") String pseudo, @FormParam("motDePasse") String motDePasse) {
		Utilisateur uti = gestionnaireUtilisateur.findByPseudo(pseudo);
		if (uti != null) {
			if (uti.getMotDePasse().equals(motDePasse)) {
				return Response.ok(uti).build();
			} else {
				return Response.status(Status.FORBIDDEN).build();
			}
		}else{
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Path("utilisateur")
	public Utilisateur getUtilisateur(@QueryParam("uuid") String uuid) {
		return gestionnaireUtilisateur.findById(uuid);
	}

        @GET
	@Path("utilisateurs")
	public List<Utilisateur> getUtilisateurs() {
                List<Utilisateur> users = gestionnaireUtilisateur.findAll();
                users.forEach(user -> user.setListeMessage(new ArrayList<>()));
                return users;
	}

	@GET
	@Path("message/hashtag")
	public List<Message> getHashtag(@QueryParam("hashtag") String hashtag) {
		List<Utilisateur> listeUtilisateur = gestionnaireUtilisateur.findAll();
		List<Message> listeMessage = new ArrayList<>();
		for (Utilisateur uti : listeUtilisateur) {
			for (Message m : uti.getListeMessage()) {
				if (m.getListeHashtag().contains(hashtag)) {
					listeMessage.add(m);
				}
			}
		}

		Collections.sort(listeMessage, new Comparator<Message>() {
			@Override
			public int compare(Message m, Message m1) {
				return m.compare(m1);
			}
		});
		return listeMessage;
	}

	@PUT
	@Path("message")
	public Response addMessage(@FormParam("uuid") String uuid, @FormParam("contenu") String contenu) {
		Message message = new Message(contenu);
		Utilisateur utilisateur = gestionnaireUtilisateur.findById(uuid);
		message.setUuidUtilisateur(uuid);
		message.setPseudoUtilisateur(utilisateur.getPseudo());
		utilisateur.getListeMessage().add(message);
		gestionnaireUtilisateur.update(utilisateur, utilisateur.getPseudo());
		return Response.ok(utilisateur).build();
	}

	/* Follower subscribe au following */
	@PUT
	@Path("follow")
	public Response addFollow(@FormParam("uuidFollower") String uuidFollower,
			@FormParam("uuidFollowing") String uuidFollowing) {
		Utilisateur utilisateurFollower = gestionnaireUtilisateur.findById(uuidFollower);
		Utilisateur utilisateurFollowing = gestionnaireUtilisateur.findById(uuidFollowing);
		utilisateurFollower.getListeFollowing().add(utilisateurFollowing.getUuid());
		utilisateurFollowing.getListeFollower().add(utilisateurFollower.getUuid());

		gestionnaireUtilisateur.update(utilisateurFollower, utilisateurFollower.getPseudo());
		gestionnaireUtilisateur.update(utilisateurFollowing, utilisateurFollowing.getPseudo());
		return Response.ok().build();
	}

}
