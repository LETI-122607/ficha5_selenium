package ex1B.Basic2.Tests;

import ex1B.Basic2.Pages.InputsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class InputsTest {

    private WebDriver driver;
    private InputsPage inputsPage;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        inputsPage = new InputsPage(driver);
        inputsPage.abrirPagina();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void escreverNumero() {
        inputsPage.escrever("123");
        Assertions.assertEquals("123", inputsPage.obterValor());
    }

    @Test
    public void escreverNegativo() {
        inputsPage.escrever("-50");
        Assertions.assertEquals("-50", inputsPage.obterValor());
    }

    @Test
    public void ignorarCaracteresInvalidos() {
        inputsPage.escrever("abc");
        Assertions.assertNotEquals("abc", inputsPage.obterValor());
    }

    @Test
    public void incrementarValor() {
        inputsPage.escrever("0");
        inputsPage.incrementar();
        Assertions.assertEquals("1", inputsPage.obterValor());
    }

    @Test
    public void decrementarValor() {
        inputsPage.escrever("1");
        inputsPage.decrementar();
        Assertions.assertEquals("0", inputsPage.obterValor());
    }
}
