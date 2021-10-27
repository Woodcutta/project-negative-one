package com.revature.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		// To start using Selenium WebDriver, we will need to specify the location of the webdriver file
		System.setProperty("webdriver.chrome.driver", "C://webdrivers/chromedriver.exe");
		
		// Once we specify the location of the webdriver, we can instantiate a WebDriver object
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://localhost:8080"); // the get method will navigate us to the website
		
		//Thread.sleep(10000);
		
		// Locate the 2 input elements and button for adding numbers
		WebElement addInput1 = driver.findElement(By.id("addNum1"));
		WebElement addInput2 = driver.findElement(By.id("addNum2"));
		WebElement addButton = driver.findElement(By.id("addButton"));
		
		addInput1.sendKeys("10.5"); // type 10.5 into the first input element
		addInput2.sendKeys("15.3"); // type 15.3 into the second input element
		addButton.click(); //click the button
		
		driver.switchTo().frame("addResult"); // switch into the inside of the iframe
		
		WebElement addOutput = driver.findElement(By.tagName("pre")); // find the pre tag that the addition result is embedded into inside of this frame
		System.out.println("The result of adding 10.5 and 15.3 is " + addOutput.getText()); // print out the text of the output element
		
		Thread.sleep(10000);
		
		driver.switchTo().defaultContent(); // switch back outside of the iframe
		
		WebElement subInput1 = driver.findElement(By.id("subNum1"));
		WebElement subInput2 = driver.findElement(By.id("subNum2"));
		WebElement subButton = driver.findElement(By.id("subButton"));
		
		subInput1.sendKeys("10.5"); 
		subInput2.sendKeys("15.3"); 
		subButton.click(); 
		
		driver.switchTo().frame("subResult"); 
		
		WebElement subOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of subtracting 10.5 and 15.3 is " + subOutput.getText()); 
		
		driver.switchTo().defaultContent(); 
		
		WebElement multiInput1 = driver.findElement(By.id("multiNum1"));
		WebElement multiInput2 = driver.findElement(By.id("multiNum2"));
		WebElement multiButton = driver.findElement(By.id("multiButton"));
		
		multiInput1.sendKeys("10.5"); 
		multiInput2.sendKeys("15.3"); 
		multiButton.click(); 
		
		driver.switchTo().frame("multiResult"); 
		
		WebElement multiOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of multiplying 10.5 and 15.3 is " + multiOutput.getText()); 
		
		driver.switchTo().defaultContent(); 
		
		WebElement divInput1 = driver.findElement(By.id("divNum1"));
		WebElement divInput2 = driver.findElement(By.id("divNum2"));
		WebElement divButton = driver.findElement(By.id("divButton"));
		
		divInput1.sendKeys("10.5"); 
		divInput2.sendKeys("15.3"); 
		divButton.click(); 
		
		driver.switchTo().frame("divResult"); 
		
		WebElement divOutput = driver.findElement(By.tagName("pre")); 
		System.out.println("The result of dividing 10.5 and 15.3 is " + divOutput.getText()); 
		
		driver.switchTo().defaultContent(); 
		
		Thread.sleep(5000); // pausing for 5 seconds
		//quit method
		//Whenever you are done with the scripted tasks that you provide to Selenium WebDriver, you should quit the driver
		//This is what will actually close the chromedriver.exe process, and free up the memory allocated to this process
		//So, this is actually an important step
		driver.quit();
	}

}
