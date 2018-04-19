package app.repository;

import org.springframework.stereotype.Repository;
import app.bean.Utilisateur;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur,String> {

}	
	