package apap.tutorial.gopud.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class MenuController {
	@Autowired
	MenuService menuService;
	
	@Qualifier("restoranServiceImpl")
	@Autowired
	RestoranService restoranService;
	
//	@RequestMapping(value="/menu/add/{idRestoran}", method = RequestMethod.GET)
//	private String addProductFormPage(@PathVariable(value="idRestoran") Long idRestoran, Model model) {
//		MenuModel menu = new MenuModel();
//		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
//		menu.setRestoran(restoran);
//		model.addAttribute("menu", menu);
//		return "form-add-menu";
//	}
	
	@RequestMapping(value = "menu/add", method = RequestMethod.POST)
	private String addProductSubmit(@ModelAttribute MenuModel menu, Model model) {
		menuService.addMenu(menu);
		model.addAttribute("nama", menu.getNama());
		return "add-menu";
	}
	
	//API yang digunakan untuk menuju halaman form change menu
	@RequestMapping(value ="menu/change/{idMenu}", method = RequestMethod.GET)
	public String changeMenuFormPage(@PathVariable Long idMenu, Model model) {
		//mengambil existing data restoran
		MenuModel existingMenu = menuService.getMenuByIdMenu(idMenu).get(); //GIMANA CARANYA DPT MENUNYA
		model.addAttribute("menu", existingMenu);
		return "form-change-menu";
	}
	
	//API yang digunakan untuk submit form change menu
	@RequestMapping(value ="menu/change/{idMenu}", method = RequestMethod.POST)
	public String changeMenuFormSubmit(@PathVariable Long idMenu, @ModelAttribute MenuModel menu, Model model) {
		MenuModel newMenuData = menuService.changeMenu(menu);
		model.addAttribute("menu", newMenuData);
		return "change-menu";
	}
	

	// @RequestMapping(path="/menu/delete/id/{idMenu}")
	// public String deleteRestoran(@PathVariable("idMenu") Long idMenu, Model model) {
	// 	try{
	// 		//Mengambil objek RestoranModel yang dituju
	// 		MenuModel deleteMenu = menuService.getMenuByIdMenu(idMenu).get();
			
	// 		//Add model restoran ke "resto" untuk dirender
	// 		model.addAttribute("menu", deleteMenu);
	// 		//Hapus restoran
	// 		menuService.deleteMenu(deleteMenu);
			
	// 		//Return update telepon template
	// 		return "delete-menu";
	// 	}
	// 	catch (Exception e) {
	// 		return "resto-not-found";
	// 	}	
	// }

	@RequestMapping(value="/menu/delete", method= RequestMethod.POST)
	private String delete(@ModelAttribute RestoranModel restoran, Model model){
		for (MenuModel menu : restoran.getListMenu()){
			menuService.deleteMenu(menu);

		}
		return "delete-menu";
	}
	
	@RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.GET)
    private String addMenuFormPage(@PathVariable(value = "idRestoran") Long idRestoran, Model model) {
        RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
        ArrayList<MenuModel> listMenu = new ArrayList<MenuModel>();
        listMenu.add(new MenuModel());
        restoran.setListMenu(listMenu);

        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }
	
	@RequestMapping(value="/menu/add/{idRestoran}", method = RequestMethod.POST, params= {"addRow"})
    public String addRow(@ModelAttribute RestoranModel restoran, BindingResult bindingResult, Model model) {
        if (restoran.getListMenu() == null) {
            restoran.setListMenu(new ArrayList<MenuModel>());
        }
        restoran.getListMenu().add(new MenuModel());

        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

	@RequestMapping(value="/menu/add/{idRestoran}", method = RequestMethod.POST, params={"removeRow"})
    public String removeRow(@ModelAttribute RestoranModel restoran, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        restoran.getListMenu().remove(rowId.intValue());
        model.addAttribute("restoran", restoran);
        return "form-add-menu";
    }

	@RequestMapping(value = "/menu/add/{idRestoran}", method = RequestMethod.POST, params={"save"})
    private String addMenuSubmit(@ModelAttribute RestoranModel restoran) {
        RestoranModel restoranNow  = restoranService.getRestoranByIdRestoran(restoran.getIdRestoran()).get();
        for(MenuModel menu: restoran.getListMenu()) {
            menu.setRestoran(restoranNow);
            menuService.addMenu(menu);
        }
        return "add-menu";
    }
	
}
