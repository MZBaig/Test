package com.ge.fsmp.connector.domain.mashaery;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Created by Gagandeep Singh on 9/14/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerDetails {
	
	
	private String name;
	
	private String siteName;
	
	private String siteCity;
	
	private String country;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteCity() {
		return siteCity;
	}
	public void setSiteCity(String siteCity) {
		this.siteCity = siteCity;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public CustomerDetails(String name, String siteName, String siteCity, String country) {
		
		this.name = name;
		this.siteName = siteName;
		this.siteCity = siteCity;
		this.country = country;
	}
	
	
	public CustomerDetails(){
		
	}
	
	@Override
	public boolean equals(Object o){
		
		if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerDetails customer = (CustomerDetails) o ;
        
        return  Objects.equals(name, customer.name)&&
        		Objects.equals(siteName, customer.siteName)&&
        		Objects.equals(siteCity, customer.siteCity)&&
        		Objects.equals(country, customer.country);
        
	}
	
	
	@Override
	public int hashCode(){
		
		return Objects.hash(name, siteName, siteCity, country);
	}
}
