package com.qa.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class TeamTest {

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
	public void testCreateTeam() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[3]/a/p"));
		targ.click();

		Thread.sleep(5000);

		WebElement u = driver.findElement(By.name("teamName"));

		u.sendKeys("Chelsea");
		Thread.sleep(3000);
		u.submit();

		Thread.sleep(3000);

		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div[1]/p[2]"));
		assertEquals("Team Name: Arsenal", targ.getText());
	}

	@Test
	public void testReadTeam() throws InterruptedException {

		driver.get("http://localhost:8080/index.html");

		// navigates to create team tab
		targ = driver.findElement(By.xpath("/html/body/main/div/header/div[3]/ul/li[3]/a/p"));
		targ.click();
		Thread.sleep(2000);

		// ASSERTIONS
		// find id
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[1]"));
		assertEquals("Team Id: 1", targ.getText());
		// Find Team name
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[2]"));
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

		u.sendKeys("test");
		u.submit();

		Thread.sleep(2000);

		// ASSERTIONS
		targ = driver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]/div/div[1]/p[2]"));
		assertEquals("Team Name: test", targ.getText());
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

		// Assertion
		// find element
		Boolean isPresent = driver.findElements(By.xpath("/html/body/div[2]/div/section[2]/div/div[1]")).size() > 0;
		assertEquals(false, isPresent);

	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}
}
