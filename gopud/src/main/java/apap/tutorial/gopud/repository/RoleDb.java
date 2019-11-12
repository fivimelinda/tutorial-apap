package apap.tutorial.gopud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.gopud.model.RoleModel;


@Repository
public interface RoleDb extends JpaRepository<RoleModel, Long>{

}
