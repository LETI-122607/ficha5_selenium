package ex1B.Basic2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Keys;

public class InputsPage {

    private WebDriver driver;

    private By input = By.xpath("//input[@type='number']");

    public InputsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("https://the-internet.herokuapp.com/inputs");
    }

    public void escrever(String valor) {
        var campo = driver.findElement(input);
        campo.clear();
        campo.sendKeys(valor);
    }

    public String obterValor() {
        return driver.findElement(input).getAttribute("value");
    }

    public void incrementar() {
        driver.findElement(input).sendKeys(Keys.ARROW_UP);
    }

    public void decrementar() {
        driver.findElement(input).sendKeys(Keys.ARROW_DOWN);
    }
}
