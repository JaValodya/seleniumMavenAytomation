package com.dice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FIndTechJobs {
	public static void main(String[] args) {
//		System.setProperty("webdriver.chrome.driver",
//				"C:\\Users\\Red\\Documents\\selenium dependencies\\drivers/chromedriver.exe ");
		
		//set up chrome driver pass
		
		WebDriverManager.chromedriver().setup();
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		// make it full screen
		driver.manage().window().maximize();
		
		//set universal wait time in case the web page get slow
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		
		
		String url = "https://www.dice.com/";
		driver.get("https://www.dice.com/");
		String expected = "Job Search for Technology Professionals | Dice.com";
		String actual = driver.getTitle();
		
		String actualURL = driver.getCurrentUrl();

		if (actual.equals(expected)) {
			System.out.println("pass");

		} else {
			System.out.println("fail to access");
			System.out.println("Expected:\t" + expected);
			System.out.println("Actual:\t" + actual);
            throw new RuntimeException("Step Fail. Dice home page is not load successfully ");
		}
		
		List <String> keyWordsList = new ArrayList<String>();
		List <String> keyWordsListPlusNumber = new ArrayList<String>();
		Faker faker = new Faker();
		
		for(int i = 0; i < 20; i++) {
			
			keyWordsList.add(faker.job().title());
			
		
		
		//String keyword = "java developer";  
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(/*keyword*/keyWordsList.get(i));
		
		String location = "Gaithersburg, MD";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		//System.out.println(count);
		
		//ensure count is more than 0
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		
//		if(countResult > 0) {
//			System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
//			countResult +" results in " + location);
//		}else {
//			System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
//					countResult +" results in " + location);
//		}
		keyWordsListPlusNumber.add(keyWordsList.get(i)+"-"+countResult);
		
		driver.navigate().back();
		}
		
		driver.close();
		
		for(String temp : keyWordsListPlusNumber) {
			System.out.println(temp);
		}
	}

}
