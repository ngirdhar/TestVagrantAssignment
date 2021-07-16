package com.TestVagrant.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnlineWeatherPage {
	WebDriver driver;
	WebElement checkboxCityName, cityNameOnMap;

	public static String weatherCityName;
	public static String weatherTempInCelValue;
	WebDriverWait wait;
	WebDriverWait shortWait;

	public OnlineWeatherPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
		shortWait = new WebDriverWait(driver, 5);
	}

	@FindBy(xpath = "//input[@name='query']")
	WebElement txtSearchBox;

	@FindBy(css = "[class='locations-list content-module'] >a:nth-child(1)")
	WebElement citySearchedLink;

	@FindBy(css = "[class='header-temp']")
	WebElement weatherTempInCelcius;

	private boolean isElementLoadedByXpath(String selector) {
		try {
			shortWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selector)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void enterCityName(String cityName) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(txtSearchBox));
		txtSearchBox.clear();
		txtSearchBox.sendKeys(cityName);
		txtSearchBox.sendKeys(Keys.ENTER);
	}

	public boolean selectCityName(String cityName) throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(citySearchedLink));
		citySearchedLink.click();
		if (!isElementLoadedByXpath("//h2[contains(text(),'Current Weather')]")) {
			driver.navigate().back();
			checkboxCityName = driver.findElement(By.cssSelector("[class='header-city-link']"));
			wait.until(ExpectedConditions.elementToBeClickable(checkboxCityName));
			driver.navigate().forward();
		}
		return isElementLoadedByXpath("//h2[contains(text(),'Current Weather')]");
	}

	public boolean validateCityNameOnPage(String cityName) throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Current Weather')]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='header-loc']")));
		cityNameOnMap = driver.findElement(By.cssSelector("[class='header-loc']"));
		weatherCityName = cityNameOnMap.getText();
		weatherCityName = weatherCityName.substring(0, weatherCityName.indexOf(","));
		System.out.print("City displayed on page is " + weatherCityName + "  ");
		return weatherCityName.equals(cityName);
	}

	public boolean weatherTempInCelciusDetails() {
		String celciusTemp = weatherTempInCelcius.getText();
		weatherTempInCelValue = celciusTemp.substring(0, celciusTemp.indexOf("°")).trim();
		System.out.println("Online Weather temperature in Celsius displayed is " + celciusTemp);
		return celciusTemp.contains("° C");
	}
}
