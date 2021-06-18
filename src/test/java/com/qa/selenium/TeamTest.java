package com.qa.selenium;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.hobby.HobbyProjectApplication;

@SpringBootTest(classes = HobbyProjectApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:test-schema.sql",
"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class TeamTest {

	private WebDriver driver;
	private WebElement targ;

	@Before
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(chromeCfg());
		driver.manage().window().setSize(new Dimension(1600, 900)); 

	}
	
	public static ChromeOptions chromeCfg() {
		Map<String, Object> prefs = new HashMap<String, Object>();
		ChromeOptions cOptions = new ChromeOptions();

		// Settings
		prefs.put("profile.default_content_setting_values.cookies", 2);
		prefs.put("network.cookie.cookieBehavior", 2);
		prefs.put("profile.block_third_party_cookies", true);

		// Create ChromeOptions to disable Cookies pop-up
		cOptions.setExperimentalOption("prefs", prefs);

		return cOptions;
	}

	@Test
	public void testCreateTeam() throws InterruptedException { 

		driver.get("http://localhost:8080/index.html");

		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[3]/a/p"));
		targ.click();
		
		WebElement u = driver.findElement(By.name("teamName"));

		u.sendKeys("Arsenal");
		Thread.sleep(3000); 
		u.submit();

		Thread.sleep(3000); 
		
		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[1]/p[2]"));
		assertEquals("Team Name: Arsenal", targ.getText());
	}
	
	@Test
	public void testUpdateTeam() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[3]/a/p"));
		targ.click();
		Thread.sleep(2000); 
		
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[2]/a[2]"));
		targ.click();
		Thread.sleep(2000); 
		
		WebElement u = driver.findElement(By.xpath("/html/body/div[2]/div/section[1]/div/form[2]/input"));
		
		
		u.sendKeys("Arsenal");
		u.submit();

		Thread.sleep(2000); 
		
		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[1]/p[2]"));
		assertEquals("Team Name: Arsenal", targ.getText());
	}
	
	@Test
	public void testDeleteTeam() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[3]/a/p"));
		targ.click();
		Thread.sleep(2000); 
		
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[2]/a[1]"));
		targ.click();
		Thread.sleep(2000); 
		

		Thread.sleep(1000); 
	}

	

	@After
	public void tearDown() {
		driver.quit();
	}
}
