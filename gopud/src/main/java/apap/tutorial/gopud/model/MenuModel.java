package apap.tutorial.gopud.model;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "menu")
public class MenuModel implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 20)
	@Column(name = "name", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "harga", nullable = false)
	private BigInteger harga;
	
	@NotNull
	@Column(name = "durasiMasak", nullable = false)
	private Integer durasiMasak;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "restoranId", referencedColumnName= "idRestoran", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private RestoranModel restoran;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	public BigInteger getHarga() {
		return harga;
	}

	public void setHarga(BigInteger harga) {
		this.harga = harga;
	}

	public Integer getDurasiMasak() {
		return durasiMasak;
	}

	public void setDurasiMasak(Integer durasiMasak) {
		this.durasiMasak = durasiMasak;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public RestoranModel getRestoran() {
		return restoran;
	}

	public void setRestoran(RestoranModel restoran) {
		this.restoran = restoran;
	}

	
}
