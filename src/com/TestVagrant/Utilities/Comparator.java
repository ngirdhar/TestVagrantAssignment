package com.TestVagrant.Utilities;

import com.TestVagrant.BaseClass.GetRequestWeather;
import com.TestVagrant.Pages.OnlineWeatherPage;

public class Comparator {
	ReadWriteConfig instance = ReadWriteConfig.getInstance();
	public float tempCelVariance = instance.getUserTempCelVariance();

	public boolean compareCityName() {
		String uiCityName, apiCityName;
		uiCityName = OnlineWeatherPage.weatherCityName.toLowerCase();
		System.out.println("City name in NDTV UI is: " + uiCityName);
		apiCityName = GetRequestWeather.responseCityName.toLowerCase();
		System.out.println("City name in API is: " + apiCityName);

		if (uiCityName.equalsIgnoreCase(apiCityName)) {
			System.out.println("City name matches");
			return true;
		} else {
			System.out.println("City name does not match");
			return false;
		}
	}

	public boolean compareTempBetweenUIAndAPI() {

		float uiTemp, apiTemp;

		uiTemp = Float.parseFloat(OnlineWeatherPage.weatherTempInCelValue);
		System.out.println("This is UI temperature in celcius value: " + uiTemp + "C\n");

		apiTemp = GetRequestWeather.responseTemp;
		System.out.println("This is API temperature in celcius value: " + apiTemp + "C\n");

		float tempDifference;
		tempDifference = Math.abs(uiTemp - apiTemp);
		if (tempDifference <= tempCelVariance) {
			System.out.println("Temperature difference is " + tempDifference + "C, WITHIN variance range of "
					+ tempCelVariance + "C\n");
			return true;
		} else {
			System.out.println("Temperature difference is " + tempDifference + "C, OUT OF variance range "
					+ tempCelVariance + "C\n");
			return false;
		}
	}
}
