package apap.tutorial.gopud.rest;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RestoranDetail {
	private String status;
	
	@JsonProperty("restoran-license")
	private Integer restaurantLicense;
	
	@JsonProperty("valid-until")
	private Date validUntil;
	
	public Date getValidUntil() {return validUntil;}
	public void setValidUntil(Date validUntil) {this.validUntil=validUntil;}
	public Integer getRestaurantLicense() {return restaurantLicense;}
	public void setRestaurantLicense(Integer restaurantLicense) {
		this.restaurantLicense = restaurantLicense;
		
	}
	
	public String getStatus() { return status;}
	public void setStatus(String status) { this.status = status;}
}
