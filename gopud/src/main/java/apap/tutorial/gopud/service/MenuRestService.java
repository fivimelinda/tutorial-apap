package apap.tutorial.gopud.service;

import java.util.List;

import apap.tutorial.gopud.model.MenuModel;

public interface MenuRestService {
	MenuModel createMenu(MenuModel menu);
	List<MenuModel> retrieveListMenu();
	MenuModel getMenuByIdMenu(Long menuId);
	MenuModel changeMenu(Long menuId, MenuModel menu);
	void deleteMenu(Long menuId);
}
