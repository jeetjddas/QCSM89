package com.ras.testscript;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestFramework 
{
	@Test
	public void tc_01()
	{
		Reporter.log("First testcase",true);
	}
	@Test
	public void tc_02()
	{
		Reporter.log("Second testcase",true);
	}
	
}
