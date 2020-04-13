package br.lucas.pereira.bb.bot;

public class Principal {

	public static void main(String[] args) {
		Selenium selenium = new FabricaSelenium().construir();
		entrar(selenium);
		votar(selenium);
	}

	private static void entrar(Selenium selenium) {
		selenium.abrir("https://gshow.globo.com/realities/bbb/bbb20/votacao/paredao-bbb20-quem-voce-quer-eliminar-babu-gizelly-ou-mari-6b0c783d-65cd-4a4e-940c-ad086cf73fee.ghtml");
		selenium.clicar("#header-produto > div.gui-color-primary-bg.header-principal.header-navegacao-color > div > div > div.menu-area > div > span");
		selenium.clicar("#menu-cascade > ul > li.menu-login > ul > li.menu-login__item.menu-login__item-login > div.menu-login--not-logged > a > span");
		selenium.focarJanelaInterna("#globoid-modal-container > div.login-modal > iframe");
		selenium.digitar("lucas@dominiol.com.br", "#login");
		selenium.digitar("11NSupamdc00=", "#password");
		selenium.clicar("#login-form > div.actions > button");
		selenium.focarJanelaPrincipal();
		selenium.esperarPorTexto("Lucas", "#menu-cascade > ul > li.menu-login > ul > li.menu-login__item.menu-login__item-login > div.menu-login--logged > span");
	}

	private static void votar(Selenium selenium) {
		selenium.abrir("https://gshow.globo.com/realities/bbb/bbb20/votacao/paredao-bbb20-quem-voce-quer-eliminar-babu-gizelly-ou-mari-6b0c783d-65cd-4a4e-940c-ad086cf73fee.ghtml");
		selenium.clicar("#roulette-root > div > div._3xDixtS9TduMA-tXdgvxyM._2Pew7ybocyFGE7NnNDvN5- > div._3_xVTlDqedwj53qq7UdnrX > div:nth-child(2) > div");
		selenium.clicarNoCentro("#roulette-root > div > div._3xDixtS9TduMA-tXdgvxyM._2Pew7ybocyFGE7NnNDvN5- > div._3_xVTlDqedwj53qq7UdnrX > div._3HY6tUrdeykwokPXP7PdwT._2aD4IDjNuQn6421Wiyamez > div._3xDixtS9TduMA-tXdgvxyM._2DsRxsoPgkhrq5exq-TSVO._2cZtCsRea_lK2Xi3dqwru > div > div > div.gc__2pMn3 > div > div:nth-child(2) > img");
		selenium.clicar("#roulette-root > div > div._3xDixtS9TduMA-tXdgvxyM._1cxsNtqqoQL6-CsTkWplIP._2Pew7ybocyFGE7NnNDvN5- > div > div > div > button");
		selenium.esperarPorTexto("Pasredão BBB20: Quem você quer eliminar? Babu, Gizelly ou Mari?", "#roulette-root > div > div._3xDixtS9TduMA-tXdgvxyM._2Pew7ybocyFGE7NnNDvN5- > div._2tD_QSR2Tqe3JXUEyCCrOJ > div > div > div");
	}

}
