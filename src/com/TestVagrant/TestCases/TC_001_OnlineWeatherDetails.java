package com.TestVagrant.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.BaseClass.OnlineBaseClass;

public class TC_001_OnlineWeatherDetails extends OnlineBaseClass {

	@Test(priority = 1)
	public void openWeatherDetailsPage() throws InterruptedException {

		Thread.sleep(2000);

		page.enterCityName(cityName);// Entering city name in the search

		boolean weatherDetailsDisplay = page.selectCityName(cityName);

		Assert.assertTrue(weatherDetailsDisplay);

		System.out.println("Weather details Page is displayed successfully\n");
	}

	@Test(priority = 2)
	public void validateCityNameOnPage() throws InterruptedException {

		boolean cityNameVerification = page.validateCityNameOnPage(cityName);

		Assert.assertTrue(cityNameVerification);

		System.out.println("City Name on Page is displayed correctly\n");
	}

	@Test(priority = 3)
	public void getWeatherTempInCelciusOnPage() throws InterruptedException {

		boolean tempInCelsius = page.weatherTempInCelciusDetails();

		Assert.assertTrue(tempInCelsius);

		System.out.println("Temperature in celsius on Page is displayed correctly\n");
	}
}
