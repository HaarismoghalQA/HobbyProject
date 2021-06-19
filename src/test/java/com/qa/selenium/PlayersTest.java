package com.qa.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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

@SpringBootTest(classes = HobbyProjectApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlayersTest {

	private static WebDriver driver;
	private static WebElement targ;

	@BeforeAll
	public static void setup() {
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
	public void testCreatePlayer() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		Thread.sleep(2000);

		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[2]/a/p"));
		targ.click();
		Thread.sleep(2000);

		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[1]/div/form[1]/select"));
		targ.click();
		Thread.sleep(2000);

		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[1]/div/form[1]/select/option"));
		targ.click();
		Thread.sleep(2000);

		WebElement u = driver.findElement(By.name("playerName"));

		u.sendKeys("Henry");

		Thread.sleep(2000);

		WebElement a = driver.findElement(By.name("age"));

		a.sendKeys("25");
		Thread.sleep(2000);
		u.submit();

		Thread.sleep(2000);

		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[2]/div/div[1]/p[4]"));
		assertEquals("playerName: Henry", targ.getText());

		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[2]/div/div[1]/p[5]"));
		assertEquals("Age: 25", targ.getText());

	}

	@Test
	public void testUpdatePlayer() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		// clicks on FantasyTeam tab on nav bar
		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[2]/a/p"));
		targ.click();
		Thread.sleep(2000);

		// Clicks on update on the player
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[2]/a[2]"));
		targ.click();
		Thread.sleep(2000);
		// Targets the player name input box
		WebElement u = driver.findElement(By.xpath("/html/body/div[2]/div/section[1]/div/form[2]/input[1]"));

		u.sendKeys("Cristiano Ronaldo");
		Thread.sleep(2000);
		WebElement a = driver.findElement(By.xpath("/html/body/div[2]/div/section[1]/div/form[2]/input[2]"));

		a.sendKeys("29");
		Thread.sleep(2000);
		u.submit();

		Thread.sleep(5000);

		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[1]/p[4]"));
		assertEquals("playerName: Cristiano Ronaldo", targ.getText());

		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[1]/p[5]"));
		assertEquals("Age: 29", targ.getText());

	}

	@Test
	public void testReadTeam() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		// clicks on FantasyTeam tab on nav bar
		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[2]/a/p"));
		targ.click();
		Thread.sleep(2000);
		
		// ASSERTIONS
		// Targets the player id
		WebElement i = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[3]"));
		assertEquals("PlayerId: 1", i.getText());
		// Targets the player name
		WebElement n = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[4]"));
		assertEquals("playerName: Haaris", n.getText());
		
		
		

	}

	@Test
	public void testDeletePlayer() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		// clicks on FantasyTeam tab on nav bar
		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[2]/a/p"));
		targ.click();
		Thread.sleep(2000);

		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[2]/a[1]"));
		targ.click();
		Thread.sleep(2000);

		// ASSERTIONS
		// targ =
		// driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[3]"));
		// assertTrue("PlayerId: 6", targ.getText());

	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
