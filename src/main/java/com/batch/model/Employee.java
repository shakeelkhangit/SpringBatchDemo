package com.batch.model;

public class Employee {
	
	private String region;
	private String country;
	private String itemType;
	private String salesChannel;
	public Employee() {
		super();
	}
	public Employee(String region, String country, String itemType, String salesChannel) {
		super();
		this.region = region;
		this.country = country;
		this.itemType = itemType;
		this.salesChannel = salesChannel;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	@Override
	public String toString() {
		return "Employee [region=" + region + ", country=" + country + ", itemType=" + itemType + ", salesChannel="
				+ salesChannel + "]";
	}
	
	

}
