package apap.tutorial.gopud.service;

import java.util.List;
import java.util.Optional;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;

public interface MenuService {
	void addMenu(MenuModel menu);
	
	List<MenuModel> findAllMenuByIdRestoran(long idRestoran);
	
	//CHECK
	MenuModel changeMenu(MenuModel MenuModel);
	
	Optional<MenuModel> getMenuByIdMenu(Long idMenu);
	
	
}
