package com.TestVagrant.BaseClass;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.TestVagrant.Utilities.ReadWriteConfig;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWeather {
	public static RequestSpecification httpRequest;
	public static Response response;
	public static JsonPath jsonPath;
	ReadWriteConfig instance = ReadWriteConfig.getInstance();
	public String baseURI = instance.getBaseURI();// Fetching from config file
	public String apiKey = instance.getApiToken();// Fetching from config file
	public String cityName = instance.getCityName();// Fetching from config file
	public String units = instance.getApiUnits().trim();// Fetching from config

	public static String responseCityName, responseWeather;
	public static float responseWindSpeed, responseTemp, responseHumidity;

	@BeforeClass
	public void getWeatherDetails() {

		RestAssured.baseURI = baseURI;
		httpRequest = RestAssured.given();

		response = httpRequest.queryParam("q", cityName).queryParam("appid", apiKey).queryParam("units", units)
				.get("/weather");

		jsonPath = response.jsonPath();
	}

	public void checkResponseStatusCode() {
		int statusCode = response.getStatusCode();
		System.out.println("The response status code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	public void checkResponseStatusLine() {

		String statusLine = response.getStatusLine();
		System.out.println("The response status line is: " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

	public void checkResponseTime() {
		float responseTime = response.getTimeIn(TimeUnit.SECONDS);
		System.out.println("Response time is : " + responseTime + " secs");
	}

	public void checkResponseBody() {
		String responseBody = response.getBody().asString();
		System.out.println("The response body is: " + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	public void fetchResponseCityName() {
		responseCityName = jsonPath.get("name");
		System.out.println("The name of the city is: " + responseCityName);
		Assert.assertEquals(responseCityName, cityName);
	}

	public boolean fetchResponseTemp() {
		String responseTemperature;
		responseTemperature = (jsonPath.get("main.temp").toString());

		if (units.equalsIgnoreCase("metric")) {
			System.out.println("The temperature is: " + responseTemperature + "C");
			responseTemp = Float.parseFloat(responseTemperature);
			return true;
		}

		else if (units.equalsIgnoreCase("Imperial")) {
			System.out.println("The temperature is: " + responseTemperature + "F");
			responseTemp = Float.parseFloat(responseTemperature);
			return true;
		} else {
			System.out.println("Temperature cannot be captured from API as Invalid unit value entered!!!");
			return false;
		}
	}

	public void fetchResponseWeather() {
		responseWeather = response.jsonPath().get("weather[0].main").toString();
		System.out.println("The overall weather is: " + responseWeather);

		Assert.assertTrue(responseWeather != null);
	}

	public void fetchResponseHumidity() {
		String responseHumid;
		responseHumid = jsonPath.get("main.humidity").toString();
		responseHumidity = Float.parseFloat(responseHumid);
		System.out.println("The humidity is: " + responseHumidity + "%");
	}

	public void fetchResponseWindSpeed() {
		String responseWind;
		responseWind = response.jsonPath().get("wind.speed").toString();
		if (units.equalsIgnoreCase("metric")) {
			System.out.println("The wind speed is: " + responseWind + " meter/sec");
			responseWindSpeed = Float.parseFloat(responseWind);
			responseWindSpeed = (float) (responseWindSpeed * 3.6);
			System.out.println("The wind speed after coversion is : " + responseWindSpeed + " Km/hour");
		} else if (units.equalsIgnoreCase("imperial")) {
			System.out.println("The wind speed is: " + responseWind + " miles/hour");
			responseWindSpeed = Float.parseFloat(responseWind);
			responseWindSpeed = (float) (responseWindSpeed * 1.609);
			System.out.println("The wind speed after coversion is : " + responseWindSpeed + " Km/hour");
		} else {
			System.out.println("Wind speed cannot be captured from API as Invalid unit value entered!!!");
		}

	}
}
