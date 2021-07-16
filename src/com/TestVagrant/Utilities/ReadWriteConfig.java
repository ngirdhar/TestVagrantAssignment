package com.TestVagrant.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadWriteConfig {
	Properties prop;

	public ReadWriteConfig() {
		File src = new File("./Configurations\\config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);

		} catch (FileNotFoundException e) {
			System.out.println("The exception is: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class PropertiesHolder {
		private static final ReadWriteConfig INSTANCE = new ReadWriteConfig();
	}

	public static ReadWriteConfig getInstance() {
		return PropertiesHolder.INSTANCE;
	}

	public String getBaseUrl() {
		String url = prop.getProperty("baseUrl");
		return url;
	}

	public String getCityName() {
		String cityName = prop.getProperty("cityName");
		return cityName;
	}

	public String getChromePath() {
		String chromePath = prop.getProperty("chromepath");
		return chromePath;
	}

	public String getFirefoxPath() {
		String ffPath = prop.getProperty("firefoxpath");
		return ffPath;
	}

	public String getBaseURI() {
		String uri = prop.getProperty("apiBaseURI");
		return uri;
	}

	public String getApiToken() {
		String key = prop.getProperty("apiToken");
		return key;
	}

	public Float getUserTempCelVariance() {
		String celsiusVariance = prop.getProperty("tempCelsiusVariance");
		return Float.parseFloat(celsiusVariance);
	}

	public String getApiUnits() {
		String units = prop.getProperty("units");
		return units;
	}

	public void setProperty(String key, String value) {
		prop.setProperty(key, value);
	}

	public void saveChanges() throws FileNotFoundException, IOException {
		try (final OutputStream outputstream = new FileOutputStream("./Configurations\\config.properties");) {
			prop.store(outputstream, "File Updated");
			outputstream.close();
		}
	}
}
