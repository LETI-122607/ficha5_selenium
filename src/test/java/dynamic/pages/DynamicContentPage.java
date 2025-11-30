package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class DynamicContentPage {

    private WebDriver driver;

    // === LOCATORS ===
    private By textos = By.className("large-10");

    // === CONSTRUTOR ===
    public DynamicContentPage(WebDriver driver) {
        this.driver = driver;
    }

    // === AÇÕES ===

    public void abrirPagina() {
        driver.get("https://the-internet.herokuapp.com/dynamic_content");
    }

    public String obterPrimeiroTexto() {
        List<WebElement> lista = driver.findElements(textos);
        return lista.get(0).getText();
    }

    public void atualizarPagina() {
        driver.navigate().refresh();
    }
}
