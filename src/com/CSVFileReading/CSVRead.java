package com.CSVFileReading;

import java.io.FileReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import au.com.bytecode.opencsv.CSVReader;

public class CSVRead {
	String path = "./CSV File/CSV.csv";
	WebDriver driver;
	
	@BeforeTest
	public void setup(){
		System.setProperty("webdriver.chrome.driver", "./ChromeDriverJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://only-testing-blog.blogspot.com/2014/05/form.html");
	}
	
	@Test
	public void Example() throws Exception{
		CSVReader reader = new CSVReader(new FileReader(path));
		String[] csvCell;
		while ((csvCell = reader.readNext())!=null) {
			String Fname = csvCell[0];
			String Lname = csvCell[1];
			String email = csvCell[2];
			String number = csvCell[3];
			String company = csvCell[4];
			
			driver.findElement(By.name("FirstName")).sendKeys(Fname);
			driver.findElement(By.name("LastName")).sendKeys(Lname);
			driver.findElement(By.name("EmailID")).sendKeys(email);
			driver.findElement(By.name("MobNo")).sendKeys(number);
			driver.findElement(By.name("Company")).sendKeys(company);
			
			driver.findElement(By.xpath("//*[@id=\"post-body-8228718889842861683\"]/div[1]/form/input[6]")).click();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
		}
		reader.close();
		
		
	}
	@AfterTest
	public void teardown() {
		driver.quit();
	}
	
	
}
