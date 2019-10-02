package apap.tutorial.gopud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;

@Repository
public interface MenuDb extends JpaRepository<MenuModel, Long> {
	List<MenuModel> findByRestoranIdRestoran(Long restoranId);
	
	Optional<MenuModel> findById(Long idMenu);
	
	List<MenuModel> findByRestoranIdRestoranOrderByHarga(Long restoranId);

}
