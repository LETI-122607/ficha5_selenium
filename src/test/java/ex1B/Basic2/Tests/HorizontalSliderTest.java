package ex1B.Basic2.Tests;

import ex1B.Basic2.Pages.HorizontalSliderPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HorizontalSliderTest {

    private WebDriver driver;
    private HorizontalSliderPage sliderPage;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        sliderPage = new HorizontalSliderPage(driver);
        sliderPage.abrirPagina();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void moverParaTresEmeio() {
        sliderPage.moverPara(3.5);
        Assertions.assertEquals("3.5", sliderPage.obterValor());
    }

    @Test
    public void moverParaCinco() {
        sliderPage.moverPara(5.0);
        Assertions.assertEquals("5", sliderPage.obterValor());
    }

    @Test
    public void moverParaDois() {
        sliderPage.moverPara(2.0);
        Assertions.assertEquals("2", sliderPage.obterValor());
    }
}
