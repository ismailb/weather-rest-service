package com.ib.tests;

import com.ib.models.Location;

public class LocationBuilder {

	private Location location;
	
	public static LocationBuilder aBasicLocation()
	{
		LocationBuilder builder = new LocationBuilder();
		
		Location location = new Location();
		location.setName("name");
		location.setCountry("country");
		location.setLatitude("11");
		location.setLongitude("11");
		
		builder.location = location;
		return builder;
	}
	
	public Location build()
	{
		return location;
	}
}
