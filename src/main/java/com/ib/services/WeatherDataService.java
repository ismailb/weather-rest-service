/**
 * 
 */
package com.ib.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author ishmael
 *
 */
@Path("weather")
public class WeatherDataService {
	
	@GET
	@Path("current")
	public Response getCurrentWeather()
	{
		return Response.ok("Testing").build();
	}

}
