package apap.tutorial.gopud.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.model.RestoranModel;
import apap.tutorial.gopud.repository.MenuDb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
	@InjectMocks
	MenuService menuService = new MenuServiceImpl();
	@Mock
	MenuDb menuDb;
	@Test
	public void whenAddValidMenuItShouldCallMenuRepositorySave() {
		MenuModel newMenu = new MenuModel();
		newMenu.setNama("ayam goreng");
		newMenu.setHarga(BigInteger.valueOf(1000));
		newMenu.setDurasiMasak(5);
		newMenu.setDeskripsi("enak");
		menuService.addMenu(newMenu);
		verify(menuDb, times(1)).save(newMenu);
	}
	
	@Test
	public void whenFindAllMenuByIdRestoranCalledItShouldReturnAllMenu() {
		List<MenuModel> allMenuFromRestoranInDatabase = new ArrayList<>();
		for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
			allMenuFromRestoranInDatabase.add(new MenuModel());
		}
		when (menuService.findAllMenuByIdRestoran(1L)).thenReturn(allMenuFromRestoranInDatabase);
		
		List<MenuModel> dataFromServiceCall = menuService.findAllMenuByIdRestoran(1L);
		assertEquals(3, dataFromServiceCall.size());
		
		verify(menuDb, times(1)).findByRestoranIdRestoran(1L);
	}
	
	@Test
	public void whenGetMenuByIdMenuCalledByExistingDataItShouldReturnCorrectData() {
		MenuModel returnedData = new MenuModel();
		returnedData.setNama("bebek");
		returnedData.setHarga(BigInteger.valueOf(17000));
		returnedData.setDurasiMasak(6);
		returnedData.setDeskripsi("panggang");
		returnedData.setId((long)1);
		
		when(menuService.getMenuByIdMenu(1L)).thenReturn(Optional.of(returnedData));
		
		Optional<MenuModel> dataFromServiceCall = menuService.getMenuByIdMenu(1L);
		
		verify(menuDb, times(1)).findById(1L);
		assertTrue(dataFromServiceCall.isPresent());
		
		MenuModel dataFromOptional = dataFromServiceCall.get();
		
		assertEquals("bebek", dataFromOptional.getNama());
		assertEquals(BigInteger.valueOf(17000), dataFromOptional.getHarga());
		assertEquals(Long.valueOf(1), dataFromOptional.getId());
		assertEquals(Integer.valueOf(6), dataFromOptional.getDurasiMasak());
		assertEquals("panggang", dataFromOptional.getDeskripsi());
	}
	
	@Test
	public void whenChangeMenuCalledItShouldChangeMenuData() {
		MenuModel updatedData = new MenuModel();
		updatedData.setNama("bebek");
		updatedData.setHarga(BigInteger.valueOf(17000));
		updatedData.setDurasiMasak(6);
		updatedData.setDeskripsi("goreng");
		updatedData.setId((long)1);
		
		when(menuDb.findById(1L)).thenReturn(Optional.of(updatedData));
		
		when(menuService.changeMenu(updatedData)).thenReturn(updatedData);
		
		MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
		
		assertEquals("bebek", dataFromServiceCall.getNama());
		assertEquals(BigInteger.valueOf(17000), dataFromServiceCall.getHarga());
		assertEquals(Long.valueOf(1), dataFromServiceCall.getId());
		assertEquals(Integer.valueOf(6), dataFromServiceCall.getDurasiMasak());
		assertEquals("goreng", dataFromServiceCall.getDeskripsi());
	}
	
	@Test
	public void whenChangeMenuCalledItShouldThrowNullPointerException() {
		MenuModel updatedData = new MenuModel();
		updatedData.setNama("bebek");
		updatedData.setHarga(BigInteger.valueOf(17000));
		updatedData.setDurasiMasak(6);
		updatedData.setDeskripsi("goreng");
		updatedData.setId((long)1);
		
		when(menuDb.findById(1L)).thenReturn(Optional.of(updatedData));
		
		when(menuService.changeMenu(updatedData)).thenThrow(NullPointerException.class);
		
		MenuModel dataFromServiceCall = menuService.changeMenu(updatedData);
		
		assertEquals(null, dataFromServiceCall);
	}
	
	@Test
	public void whenDeleteMenuItShouldCallMenuRepositorySave() {
		MenuModel newMenu = new MenuModel();
		newMenu.setNama("ayam goreng");
		newMenu.setHarga(BigInteger.valueOf(1000));
		newMenu.setDurasiMasak(5);
		newMenu.setDeskripsi("enak");
		
		menuService.addMenu(newMenu);
		menuService.deleteMenu(newMenu);
		
		verify(menuDb, times(1)).delete(newMenu);
	}
	
	@Test
	public void whenGetListMenuOrderByHargaAsc() {
		List<MenuModel> allMenuFromRestoranInDatabase = new ArrayList<>();
		for (int loopTimes = 3; loopTimes > 0; loopTimes--) {
			allMenuFromRestoranInDatabase.add(new MenuModel());
		}
		when (menuService.getListMenuOrderByHargaAsc(1L)).thenReturn(allMenuFromRestoranInDatabase);
		
		List<MenuModel> dataFromServiceCall = menuService.getListMenuOrderByHargaAsc(1L);
		assertEquals(3, dataFromServiceCall.size());
		
		verify(menuDb, times(1)).findByRestoranIdRestoranOrderByHarga(1L);
	}
	
	
	
}
