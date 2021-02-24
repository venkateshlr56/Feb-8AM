package org.redbus;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RedBusLogin {
	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();

		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		int brokenCount=0;
		for (int i = 0; i < links.size(); i++) {
			String linksName = links.get(i).getAttribute("href");
			System.out.println(linksName);

			if (!(linksName == null)) {

				URL url = new URL(linksName);
				URLConnection openConnection = url.openConnection();
				HttpsURLConnection connection = (HttpsURLConnection) openConnection;
				int responseCode = connection.getResponseCode();
				if (!(responseCode==200)) {
					
					brokenCount++;
					System.out.println("Broken Links :"+linksName);
					
				}
			}
		}
		
		System.out.println("Broken Links Counts :"+brokenCount);

	}
}
