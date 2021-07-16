package com.TestVagrant.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.BaseClass.GetRequestWeather;

public class TC_002_GetRequestWeather extends GetRequestWeather {
	@Test
	public void getAPIWeatherDetails() {

		checkResponseStatusCode();// checking the status code of the response
		System.out.println("---------------------------------------------------------------");
		checkResponseStatusLine();// checking the status line of the response
		System.out.println("---------------------------------------------------------------");
		checkResponseTime();// checking the response time
		System.out.println("---------------------------------------------------------------");
		checkResponseBody();// checking the response body
		System.out.println("---------------------------------------------------------------");
		fetchResponseCityName();// validate the name of the city
		boolean fetchValue = fetchResponseTemp();// fetching temperature
		Assert.assertTrue(fetchValue);
		System.out.println("Temperature from API is fetched correctly");
	}

}
