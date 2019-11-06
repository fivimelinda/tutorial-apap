package apap.tutorial.gopud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apap.tutorial.gopud.model.RestoranModel;

@Repository
public interface RestoranDb extends JpaRepository<RestoranModel, Long>{
	Optional<RestoranModel> findByIdRestoran(Long idRestoran);

	List<RestoranModel> findAllByOrderByNamaAsc();
	
}
