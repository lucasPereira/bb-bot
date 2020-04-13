package br.lucas.pereira.bb.bot;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;

public class Selenium {

	private WebDriver driver;

	Selenium(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS).pageLoadTimeout(10, TimeUnit.SECONDS).setScriptTimeout(10, TimeUnit.SECONDS);
	}

	public void abrir(String uri) {
		driver.get(uri);
		driver.manage().window().maximize();
	}

	public void clicar(String seletor) {
		System.out.println("[Clicou] " + seletor);
		clicarComEspera(seletor);
	}

	public void digitar(String texto, String seletor) {
		System.out.println("[Digitou] " + texto + " em " + seletor);
		obter(seletor).sendKeys(texto);
	}

	public void clicarNoCentro(String seletor) {
		System.out.println("[Clicou com offset] " + seletor);
		WebElement elemento = obter(seletor);
		new Actions(driver).moveToElement(elemento).click().build().perform();
	}

	public void focarJanelaInterna(String seletor) {
		WebElement janela = driver.findElement(By.cssSelector(seletor));
		driver.switchTo().frame(janela);
	}

	public void focarJanelaPrincipal() {
		driver.switchTo().defaultContent();
	}

	public void esperarPorTexto(String textoEsperado, String seletor) {
		FluentWait<WebDriver> fluente = new FluentWait<WebDriver>(driver);
		fluente.withTimeout(Duration.ofSeconds(10));
		fluente.pollingEvery(Duration.ofMillis(500));
		fluente.ignoring(NoSuchElementException.class);
		fluente.until((WebDriver driverFluente) -> {
			WebElement elemento = driverFluente.findElement(By.cssSelector(seletor));
			String textoObtido = elemento.getText();
			if (!textoObtido.equals(textoEsperado)) {
				throw new NoSuchElementException("Texto obtido '" + textoObtido + "' diferente do esperado '" + textoEsperado + "'");
			}
			return elemento;
		});
	}

	WebElement obter(String seletor) {
		FluentWait<WebDriver> fluente = new FluentWait<WebDriver>(driver);
		fluente.withTimeout(Duration.ofSeconds(10));
		fluente.pollingEvery(Duration.ofMillis(500));
		fluente.ignoring(NoSuchElementException.class);
		WebElement elemento = fluente.until((WebDriver driverFluente) -> {
			return driverFluente.findElement(By.cssSelector(seletor));
		});
		return elemento;
	}

	WebElement clicarComEspera(String seletor) {
		FluentWait<WebDriver> fluente = new FluentWait<WebDriver>(driver);
		fluente.withTimeout(Duration.ofSeconds(10));
		fluente.pollingEvery(Duration.ofMillis(500));
		fluente.ignoring(NoSuchElementException.class);
		fluente.ignoring(ElementClickInterceptedException.class);
		return fluente.until((WebDriver driverFluente) -> {
			WebElement elemento = driverFluente.findElement(By.cssSelector(seletor));
			elemento.click();
			return elemento;
		});
	}

}
