package apap.tutorial.gopud.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import apap.tutorial.gopud.model.RestoranModel;

@Service
public class RestoranInMemoryService implements RestoranService{
	private List<RestoranModel> listRestoran;
	
	//Constructor
	
	public RestoranInMemoryService() {
		listRestoran = new ArrayList<>();
	}
	
	@Override
	public void addRestoran(RestoranModel restoran) {
		listRestoran.add(restoran);
		
	}
	
	@Override
	public List<RestoranModel> getRestoranList(){
		return listRestoran;
	}
	
	@Override
	public RestoranModel getRestoranByIdRestoran(String idRestoran) {
		for(RestoranModel myRestoran : listRestoran) {
			if (myRestoran.getIdRestoran().equals(idRestoran)) {
				return myRestoran;
			}
		}
		return null;
	}
	
	@Override
	public void deleteRestoran(RestoranModel restoran) {
		listRestoran.remove(restoran);
	}

}
