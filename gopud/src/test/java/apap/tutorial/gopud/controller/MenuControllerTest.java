package apap.tutorial.gopud.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.service.MenuService;
import apap.tutorial.gopud.service.RestoranService;

@RunWith(SpringRunner.class)
@WebMvcTest(RestoranController.class)
public class MenuControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	@Qualifier("restoranServiceImpl")
	private RestoranService restoranService;
	
	@MockBean
	@Qualifier("restoranServiceImpl")
	private MenuService menuService;
	
	@Test
	public void whenViewAddMenu() throws Exception{
		RestoranModel myRestoran = new RestoranModel();
		ArrayList<MenuModel> listMenu = new ArrayList<MenuModel>();
		when(restoranService.getRestoranByIdRestoran(1L).get()).thenReturn(myRestoran);
		listMenu.add(new MenuModel());
        myRestoran.setListMenu(listMenu);
		mockMvc.perform(get("/menu/add/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(content().string(Matchers.containsString("Tambah Produk")))
		.andExpect(model().attribute("myRestoran", listMenu));
	}
}
