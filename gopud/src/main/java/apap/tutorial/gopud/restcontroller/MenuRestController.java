package apap.tutorial.gopud.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuRestService;
import apap.tutorial.gopud.service.RestoranRestService;
import apap.tutorial.gopud.service.RestoranService;

@RestController
@RequestMapping("api/v1")
public class MenuRestController {
	@Autowired
	private MenuRestService menuRestService;
		
	@Autowired
	RestoranRestService restoranRestService;
	
	@PostMapping(value = "/menu")
    private MenuModel createMenu(
            @Valid @RequestBody MenuModel menu,
            BindingResult bindingResult
    ){
        if(bindingResult.hasFieldErrors()){
            throw new ResponseStatusException(
              HttpStatus.BAD_REQUEST,"Request body has invalid type or missing field");
        }else{
            RestoranModel restoran= restoranRestService.getRestoranByIdRestoran(2L);
            restoran.getListMenu().add(menu);
            menu.setRestoran(restoran);
            return menuRestService.createMenu(menu);
        }
    }
	
	@PutMapping(value="/menu/{menuId}")
	private MenuModel updateMenu(
			@PathVariable(value="menuId") Long menuId,
			@RequestBody MenuModel menu) {
		try {
			return menuRestService.changeMenu(menuId, menu);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "ID Menu " + String.valueOf(menuId) + " Not Found");
		}
	}
	
	@GetMapping(value="/menu/{menuId}")
	private MenuModel retrieveMenu(@PathVariable("menuId") Long menuId) {
		try {
			return menuRestService.getMenuByIdMenu(menuId);
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND, "ID Menu " +String.valueOf(menuId)+" Not Found");
		}
	}
	
	@GetMapping(value="/menus")
	private List<MenuModel> retrieveListMenu(){
		return
				menuRestService.retrieveListMenu();
	}
	
	@DeleteMapping(value="/menu/{menuId}")
	private ResponseEntity<String> deleteMenu(@PathVariable("menuId") Long menuId){
		try {
			menuRestService.deleteMenu(menuId);
			return ResponseEntity.ok("Menu with ID "+String.valueOf(menuId)+" Deleted!");
		} catch (NoSuchElementException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Menu with ID "+String.valueOf(menuId)+" Not Found!");
		}
	}
	
	
}
