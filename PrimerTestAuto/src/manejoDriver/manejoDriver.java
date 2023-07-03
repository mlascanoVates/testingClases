package manejoDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

import MetodosUtiles.Utiles;

public class manejoDriver {
	
	private enum browsers {	EXPLORER, FIREFOX, CHROME};
	
	public static WebDriver LevantarBrowser(WebDriver driver,  ITestContext context )
	{
	String URL= context.getCurrentXmlTest().getParameter("URL");
	String browserName = context.getCurrentXmlTest().getParameter("NombreNavegador");
	switch (browsers.valueOf(browserName)) {
	case CHROME: // Using WebDriver
	{
	System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
	Reporter.log("Abro browser");
	driver = new ChromeDriver();
	break;
	}
	case FIREFOX:// Using WebDriver
	{
	System.setProperty("webdriver.gecko.driver", "C:\\Drivers\\geckodriver.exe");
	Reporter.log("Abro browser");
	driver = new FirefoxDriver();
	break;
	}
	default:
	Reporter.log("No selecciono ningun browser correcto, se le asignara Chrome");
	System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
	Reporter.log("Abro browser");
	driver = new ChromeDriver();
	break;
	}
	Utiles.escribir("Borro todas las cookies");
    driver.manage().deleteAllCookies();
    
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get(URL);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	return driver;
	}
}
