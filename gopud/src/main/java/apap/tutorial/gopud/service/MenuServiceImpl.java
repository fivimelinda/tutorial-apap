package apap.tutorial.gopud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDb;

@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
	private MenuDb menuDb;
	
	@Override
	public void addMenu(MenuModel menu) {
		menuDb.save(menu);
	}
	
	@Override
	public List<MenuModel> findAllMenuByIdRestoran(long idRestoran) {
		return menuDb.findByRestoranIdRestoran(idRestoran);
	}
	
	// CHECKKK
	
	@Override
	public Optional<MenuModel> getMenuByIdMenu(Long idMenu){
		return menuDb.findById(idMenu);
	}
	
	@Override
	public MenuModel changeMenu(MenuModel menuModel) {
		//mengambil object menu yang ingin diubah
		MenuModel targetMenu = menuDb.findById(menuModel.getId()).get();
		
		try {
			targetMenu.setNama(menuModel.getNama());
			targetMenu.setHarga(menuModel.getHarga());
			targetMenu.setDurasiMasak(menuModel.getDurasiMasak());
			targetMenu.setDeskripsi(menuModel.getDeskripsi());
			menuDb.save(targetMenu);
			return targetMenu;
		} catch (NullPointerException nullException) {
			return null;
		}
	}
	//
}
