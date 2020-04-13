package br.lucas.pereira.bb.bot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FabricaSelenium {

	private static WebDriver driver;

	public FabricaSelenium() {
		if (driver == null) {
			driver = construirWebDriver();
		}
	}

	private WebDriver construirWebDriver() {
		Boolean mac = System.getProperty("os.name").contains("Mac");
		Boolean linux = System.getProperty("os.name").equals("Linux");
		Boolean arquitetura64 = System.getProperty("os.arch").equals("amd64");
		if (linux && arquitetura64) {
			return construirChromeParaLinux64();
		}
		if (mac && arquitetura64) {
			return construirChromeParaMac64();
		}
		throw new RuntimeException("Ambiente não suportado");
	}

	WebDriver construirChromeParaMac64() {
		System.setProperty("webdriver.chrome.driver", "drivers/chrome-mac-64");
		return new ChromeDriver();
	}

	WebDriver construirChromeParaLinux64() {
		System.setProperty("webdriver.chrome.driver", "drivers/chrome-linux-64");
		return new ChromeDriver();
	}

	WebDriver construirChromeHeadlessParaLinux64() {
		System.setProperty("webdriver.chrome.driver", "drivers/chrome-linux-64");
		ChromeOptions opcoes = new ChromeOptions();
		opcoes.addArguments("--headless");
		opcoes.addArguments("--no-sandbox");
		opcoes.addArguments("--window-size=1920,1080");
		opcoes.addArguments("--remote-debugging-port=9222");
		return new ChromeDriver(opcoes);
	}

	public Selenium construir() {
		return new Selenium(driver);
	}

}
