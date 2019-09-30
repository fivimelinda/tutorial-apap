package apap.tutorial.gopud.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="restoran")
public class RestoranModel implements Serializable, Comparable<RestoranModel> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRestoran;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 30)
	@Column(name="alamat", nullable = false)
	private String alamat;
	
	@NotNull
	@Column(name= "nomorTelepon", nullable = false)
	private Integer nomorTelepon;
	
	@NotNull
	@Column(name = "rating", nullable = false)
	private Integer rating = 0;
	
	@OneToMany(mappedBy = "restoran", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MenuModel> listMenu;
	
	public Long getIdRestoran() {
		return idRestoran;
	}

	public void setIdRestoran(Long idRestoran) {
		this.idRestoran = idRestoran;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Integer getNomorTelepon() {
		return nomorTelepon;
	}

	public void setNomorTelepon(Integer nomorTelepon) {
		this.nomorTelepon = nomorTelepon;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<MenuModel> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<MenuModel> listMenu) {
		this.listMenu = listMenu;
	}
	
	@Override
	public int compareTo(RestoranModel other) {
		return this.getNama().toLowerCase().compareTo(other.getNama().toLowerCase());
	}

	
}
