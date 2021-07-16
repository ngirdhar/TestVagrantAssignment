package com.TestVagrant.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.Utilities.Comparator;

public class TC_003_Compare_UI_API extends Comparator{

	@Test
	public void compareUiAndAPIValues()
	{
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("------------------------------------------------------------------------------");
		boolean cityCompare = compareCityName();//Comparing City names on UI and API
		Assert.assertTrue(cityCompare);
		System.out.println("------------------------------------------------------------------------------");
		boolean tempCompare = compareTempBetweenUIAndAPI();//Comparing temperature on UI and API
		Assert.assertTrue(tempCompare);
		System.out.println("------------------------------------------------------------------------------");
	}
}
