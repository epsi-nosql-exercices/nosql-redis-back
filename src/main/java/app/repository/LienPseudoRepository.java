package app.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import app.bean.LienPseudo;

@Repository
public interface LienPseudoRepository extends CrudRepository<LienPseudo,String> {

}
