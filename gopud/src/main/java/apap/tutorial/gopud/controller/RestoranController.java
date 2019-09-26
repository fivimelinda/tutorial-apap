package apap.tutorial.gopud.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;

@Controller
public class RestoranController {
	@Qualifier("restoranServiceImpl")
	@Autowired
	private RestoranService restoranService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/")
	public String home() { return "home";}
	
	//URL mapping yang digunakan untuk mengakses halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.GET)
	public String addRestoranFormPage(Model model) {
		RestoranModel newRestoran = new RestoranModel();
		model.addAttribute("restoran", newRestoran);
		return "form-add-restoran";
	}
	
	//URL mapping yang digunakan untuk submit form yang telah anda masukkan pada halaman add restoran
	@RequestMapping(value = "/restoran/add", method = RequestMethod.POST)
	public String addRestoranSubmit(@ModelAttribute RestoranModel restoran, Model model) {
		restoranService.addRestoran(restoran);
		model.addAttribute("namaResto", restoran.getNama());
		return "add-restoran";
	}
	
	//URL mapping view
	@RequestMapping(path = "/restoran/view", method = RequestMethod.GET)
	public String view(
			//Request parameter untuk dipass
			@RequestParam(value = "idRestoran") String idRestoran, Model model
			) {
		try {
			//Mengambil objek RestoranModel yang dituju
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(Long.parseLong(idRestoran)).get();
			//Add model restoran ke "resto" untuk dirender
			model.addAttribute("resto", restoran);
			
			List<MenuModel> menuList = menuService.findAllMenuByIdRestoran(restoran.getIdRestoran());
			model.addAttribute("menuList", menuList);
			model.addAttribute("isMenuListEmpty", menuList.isEmpty());
			return "view-restoran";
		} catch (Exception e) {
			return "resto-not-found";
		}
		
	}
	
	//API yang digunakan untuk menuju halaman form change restoran
	@RequestMapping(value ="restoran/change/{idRestoran}", method = RequestMethod.GET)
	public String changeRestoranFormPage(@PathVariable Long idRestoran, Model model) {
		//mengambil existing data restoran
		RestoranModel existingRestoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
		if (existingRestoran == null) {
			return "resto-not-found";
		} else {
			model.addAttribute("restoran", existingRestoran);
			return "form-change-restoran";
		}
	}
	
	//API yang digunakan untuk submit form change restoran
	@RequestMapping(value ="restoran/change/{idRestoran}", method = RequestMethod.POST)
	public String changeRestoranFormSubmit(@PathVariable Long idRestoran, @ModelAttribute RestoranModel restoran, Model model) {
		RestoranModel newRestoranData = restoranService.changeRestoran(restoran);
		model.addAttribute("restoran", newRestoranData);
		return "change-restoran";
	}
	
	//URL mapping view
	@RequestMapping("/restoran/view-all")
	public String viewall(Model model) {
		
		//Mengambil semua objek RestoranModel yang ada
		List<RestoranModel> listRestoran = restoranService.getRestoranList();
		//Sorting
		Collections.sort(listRestoran);
		//Add model restoran ke "resto" untuk dirender
		model.addAttribute("restoList", listRestoran);
		
		//return view template
		return "viewall-restoran";
	}
	
	@RequestMapping(path="/restoran/delete/id/{idRestoran}")
	public String deleteRestoran(@PathVariable("idRestoran") Long idRestoran, Model model) {
	
		try{
			//Mengambil objek RestoranModel yang dituju
			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran).get();
			
			//Add model restoran ke "resto" untuk dirender
			model.addAttribute("resto", restoran);
			//Hapus restoran
			restoranService.deleteRestoran(restoran);
			
			//Return update telepon template
			return "delete-restoran";
		}
		catch (Exception e) {
			return "resto-not-found";
		}
			
	}
	
//	//URL mapping add
//	@RequestMapping("/restoran/add")
//	public String add(
//			// Request Parameter untuk dipass
//			@RequestParam(value = "idRestoran", required = true) String idRestoran,
//			@RequestParam(value = "nama", required = true) String nama,
//			@RequestParam(value = "alamat", required = true) String alamat,
//			@RequestParam(value = "nomorTelepon", required = true) Integer nomorTelepon,
//			Model model
//			) {
//		//Membuat objek RestoranModel
//		RestoranModel restoran = new RestoranModel(idRestoran, nama, alamat, nomorTelepon);
//		
//		//Memanggil service addRestoran
//		restoranService.addRestoran(restoran);
//		
//		//Add variabel nama restoran ke "namaResto" untuk dirender
//		model.addAttribute("namaResto", nama);
//		
//		//Return view template
//		return "add-restoran";
//	}
	//URL mapping view
//	@RequestMapping("/restoran/view")
//	public String view(
//			//Request Parameter untuk dipass
//			@RequestParam(value= "idRestoran") String idRestoran, Model model
//			) {
//		
//		//Mengambil objek RestoranModel yang dituju
//		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//		
//		//Add model restoran ke "resto" untuk dirender
//		model.addAttribute("resto", restoran);
//		
//		//Return view template
//		return "view-restoran";
//	}
	
	
//	//URL Mapping view with PathVariable
//	@RequestMapping(path="/restoran/view/id-restoran/{idRestoran}")
//	public String getId(@PathVariable("idRestoran") String idRestoran, Model model) {
//		
//		//Mengambil objek RestoranModel yang dituju
//		RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//				
//		//Add model restoran ke "resto" untuk dirender
//		model.addAttribute("resto", restoran);
//				
//		//Return view template
//		return "view-restoran";
//	}
	
	//URL Mapping update nomor telepon
//	@RequestMapping(path="/restoran/update/id-restoran/{idRestoran}/nomor-telepon/{nomorTelepon}")
//	public String updateTelepon(@PathVariable("idRestoran") String idRestoran,
//			@PathVariable("nomorTelepon") Integer nomorTelepon, Model model) {
//		
//			//Mengambil objek RestoranModel yang dituju
//			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//			
//			//Ubah nomor telepon
//			restoran.setNomorTelepon(nomorTelepon);
//			
//			//Add model restoran ke "resto" untuk dirender
//			model.addAttribute("resto", restoran);
//			
//			//Return update telepon template
//			return "update-telepon";
//			
//	}
	
//	@RequestMapping(path="/restoran/delete/id/{idRestoran}")
//	public String deleteRestoran(@PathVariable("idRestoran") String idRestoran, Model model) {
//		
//			//Mengambil objek RestoranModel yang dituju
//			RestoranModel restoran = restoranService.getRestoranByIdRestoran(idRestoran);
//			
//			if (restoran==null) {
//				return "resto-not-found";
//			}
//			else {
//				//Add model restoran ke "resto" untuk dirender
//				model.addAttribute("resto", restoran);
//				//Hapus restoran
//				restoranService.deleteRestoran(restoran);
//				
//				//Return update telepon template
//				return "delete-restoran";
//			}
//			
//	}
	
	
}
