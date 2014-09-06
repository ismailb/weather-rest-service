package com.ib.tests;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ib.businessservices.WeatherDataBusinessService;
import com.ib.dao.WeatherDataDao;
import com.ib.dtos.RemoteWeatherData;
import com.ib.exception.ApplicationException;
import com.ib.models.Location;
import com.ib.services.RemoteWeatherDataService;

/**
 * WeatherDataBusinessServiceTest tests {@link WeatherDataBusinessService}
 * 
 * @author ishmael
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherDataBusinessServiceTest {
	
	@Mock
	private RemoteWeatherDataService mockRemoteWeatherDataService;

	@Mock
	private WeatherDataDao mockWeatherDataDao;

	@InjectMocks
	private WeatherDataBusinessService weatherDataBusinessService = new WeatherDataBusinessService();

	private static final String TEST_LOCATION = "test location";
	
	/**
	 * Tests {@link WeatherDataBusinessService#getCurrentWeather(String)} with invalid location name
	 * 
	 */
	@Test(expected = ApplicationException.class)
	public void testCurretWeatherFetchWithInvalidLocation() {
		weatherDataBusinessService.getCurrentWeather(null);
	}
	
	/**
	 * Tests the remote weather data fetch if todays weather is not available 
	 */
	@Test
	public void testCurrentWeatherWithValidLocationAndWithoutTodaysWeather()
	{
		Location location = LocationBuilder.aBasicLocation().build(); 
		RemoteWeatherData remoteData = RemoteWeatherDataBuilder.aBasicRemoteWeatherData().build();
		when(mockWeatherDataDao.findLocationByName(anyString())).thenReturn(location);
		when(mockWeatherDataDao.save(location)).thenReturn(location);
		when(mockRemoteWeatherDataService.getCurrentWeatherData(TEST_LOCATION)).thenReturn(remoteData);

		weatherDataBusinessService.getCurrentWeather(TEST_LOCATION);
		
		verify(mockRemoteWeatherDataService, Mockito.times(1)).getCurrentWeatherData(TEST_LOCATION);
	}
	
}
