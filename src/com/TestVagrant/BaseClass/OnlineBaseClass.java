package com.TestVagrant.BaseClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.TestVagrant.Pages.OnlineWeatherPage;
import com.TestVagrant.Utilities.ReadWriteConfig;

public class OnlineBaseClass {
	ReadWriteConfig instance = ReadWriteConfig.getInstance();
	private String baseUrl = instance.getBaseUrl();
	public String cityName = instance.getCityName();
	public WebDriver driver;
	public OnlineWeatherPage page;

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", instance.getChromePath());
			driver = new ChromeDriver();

		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", instance.getFirefoxPath());
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.get(baseUrl);
		page = new OnlineWeatherPage(driver);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
