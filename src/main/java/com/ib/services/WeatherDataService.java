/**
 * 
 */
package com.ib.services;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ib.businessservices.WeatherDataBusinessService;
import com.ib.dtos.WeatherDataDTO;
import com.ib.exception.ApplicationException;

/**
 * @author ishmael
 *
 */
@Path("/weather")
public class WeatherDataService {

	@Autowired
	private WeatherDataBusinessService weatherDataBusinessService;

	private Logger logger = LoggerFactory.getLogger(WeatherDataService.class);

	@GET
	@Path("/current")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrentWeather(@QueryParam("q") String location) {
		ResponseBuilder response = Response.status(Status.BAD_REQUEST);
		if (isNotBlank(location)) {
			try {
				WeatherDataDTO weatherDataDto = weatherDataBusinessService.getCurrentWeather(location);
				response.status(Status.ACCEPTED);
				response.entity(weatherDataDto);
			} catch (ApplicationException e) {
				response.status(Status.NOT_FOUND);
				response.entity(getError(e.getMessage()));
			} catch (Exception e) {
				response.status(Status.INTERNAL_SERVER_ERROR);
				response.entity(getError("Internal Error"));
			}
		}
		return response.build();
	}

	private Object getError(String message) {
		try {
			JSONObject error = new JSONObject();
			error.append("message", message);
			return error;
		} catch (JSONException e) {
			logger.error("Exception occurred creating errorMessage", e);
		}
		return message;
	}

}
