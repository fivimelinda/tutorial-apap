package apap.tutorial.gopud.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import apap.tutorial.gopud.rest.Setting;

@Service
@Transactional
public class MenuRestServiceImpl implements MenuRestService{
	
	@Autowired
	private MenuDb menuDb;
	
	@Override
	public MenuModel createMenu(MenuModel menu) {
		return menuDb.save(menu);
	}
	
	@Override
	public List<MenuModel> retrieveListMenu(){
		return menuDb.findAll();
	}
	
	@Override
	public MenuModel getMenuByIdMenu(Long menuId) {
		Optional<MenuModel> menu = menuDb.findById(menuId);
		if(menu.isPresent()) {
			return menu.get();
		} else {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public MenuModel changeMenu(Long menuId, MenuModel menuUpdate) {
		MenuModel menu = getMenuByIdMenu(menuId);
		menu.setNama(menuUpdate.getNama());
		menu.setHarga(menuUpdate.getHarga());
		menu.setDurasiMasak(menuUpdate.getDurasiMasak());
		menu.setDeskripsi(menuUpdate.getDeskripsi());
		return menuDb.save(menu);
	}
	
	@Override
	public void deleteMenu(Long menuId) {
		MenuModel menu = getMenuByIdMenu(menuId);
		menuDb.delete(menu);
	}
	
}
