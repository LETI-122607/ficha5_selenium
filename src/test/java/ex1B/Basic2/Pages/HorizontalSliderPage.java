package ex1B.Basic2.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HorizontalSliderPage {

    private WebDriver driver;

    private By slider = By.xpath("//input[@type='range']");
    private By valueText = By.id("range");

    public HorizontalSliderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");
    }

    public void moverPara(double target) {
        double atual = Double.parseDouble(driver.findElement(valueText).getText());
        var element = driver.findElement(slider);

        Keys tecla = target > atual ? Keys.ARROW_RIGHT : Keys.ARROW_LEFT;

        while (Double.parseDouble(driver.findElement(valueText).getText()) != target) {
            element.sendKeys(tecla);
        }
    }

    public String obterValor() {
        return driver.findElement(valueText).getText();
    }
}
