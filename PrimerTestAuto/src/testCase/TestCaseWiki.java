package testCase;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import MetodosUtiles.Utiles;
import manejoDriver.manejoDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCaseWiki {
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void precondition(ITestContext context) {
		Utiles.escribir("Nombre: LASCANO MARIA EMILIA ");
		
	    
		Utiles.escribir("Abro Navegador");
		driver = manejoDriver.LevantarBrowser(driver, context);
		
	}
	
	@AfterMethod
	public void postcondition() {
		Utiles.escribir("Cierro Navegador");
		driver.quit();
	}
	
	@DataProvider(name = "datos")
	public Object[][] createData() {
	return new Object[][] {
		{ "Selenium","Selenium"},
		{ "TDD", "Desarrollo guiado por pruebas"},
		{ "DATA DRIVEN TESTING","Data-driven testing"},
		{ "Testeo","Pruebas de software"}
		};	
	}

	 	
	@Test(dataProvider = "datos", description = "Verificar y Validar la caja de texto de Wikipedia")
	public void ValidarBusquedaWikipedia(String varBuscar, String resultado) throws Exception {
	
		
	Utiles.escribir("ubicar el elemento web de la caja de texto busqued");
	WebElement searchInput = driver.findElement(By.id("searchInput"));
	
	Utiles.escribir("Se visualiza o despliega la caja de texto");
	Assert.assertTrue(searchInput.isDisplayed(),"No se pudo visualizar la caja de busqueda");
	
	Utiles.escribir("Ingresar la palabra: "+ varBuscar);
	searchInput.sendKeys(varBuscar);
	
	Utiles.escribir("presionar enter");
	searchInput.submit();
	
	Thread.sleep(2000);
	
	Utiles.escribir("En la pagina de resultado, busco el titulo");
	WebElement tituloResultado = driver.findElement(By.id("firstHeading"));
	System.out.println("Texto encontrado "+ tituloResultado.getText());
	
	Utiles.escribir("El titulo es visible");
	Assert.assertTrue(tituloResultado.isDisplayed());
	
	Utiles.escribir("Verificar que el titulo sea: "+ resultado);
	Assert.assertTrue(tituloResultado.getText().toString().contains(resultado), "No se visualizo el titulo");

	}
	
}
