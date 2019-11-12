package apap.tutorial.gopud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.service.RoleService;
import apap.tutorial.gopud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserModel user, @ModelAttribute(value="konfirmasi") String konfirmasi, Model model) {
		if (userService.getUserByUsername(user.getUsername())== null) {
			if(user.getPassword().equals(konfirmasi)) {
				if(userService.validPass(user.getPassword())) {
					userService.addUser(user);
					model.addAttribute("message", "User baru berhasil ditambahkan!");
				} else {
					model.addAttribute("message", "Password tidak valid! Harus mengandung angka, huruf, minimal 10 karakter");
				}
			} else {
				model.addAttribute("message", "Konfirmasi password tidak sesuai");
			}
		} else {
			model.addAttribute("message", "Username yang dimasukkan sudah digunakan");
		}
		
		model.addAttribute("listRole", roleService.findAll());
		return "home";

	}
	
	@RequestMapping(value="/updatePassword", method = RequestMethod.GET)
	private String changePass(Model model) {
		model.addAttribute("message", "");
		return "form-update-password";
	}
	
	@RequestMapping(value="/updatePassword/{username}", method= RequestMethod.POST)
	private String changePassSubmit(
			@PathVariable(value="username") String username, 
			@ModelAttribute(value="oldPass") String oldPass,
			@ModelAttribute(value="newPass") String newPass,
			@ModelAttribute(value="passKonfirmasi") String passKonfirmasi,
			Model model){
		UserModel user = userService.getUserByUsername(username);
		if(userService.checkPassword(user, oldPass)) { //pass lama sesuai
			if(newPass.equals(passKonfirmasi)) { //pass baru sama dengan konfirmasi
				if(userService.validPass(newPass)) { //pass valid
					userService.updatePassword(user, newPass);
					System.out.println("2");
					return "update-password";
				} else {
					model.addAttribute("message", "Password tidak valid! Harus mengandung angka, huruf, minimal 8 karakter");
					System.out.println("3");
					return "form-update-password";
				}
			} else {
				model.addAttribute("message", "Konfirmasi password tidak sesuai");
				System.out.println("1");
				return "form-update-password";
			}
		} else {
			model.addAttribute("message", "Password lama tidak sesuai");
			System.out.println("4");
			return "form-update-password";
		}
	}
}
