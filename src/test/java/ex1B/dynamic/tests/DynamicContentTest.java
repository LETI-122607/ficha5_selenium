package ex1B.dynamic.tests;


import ex1B.dynamic.pages.DynamicContentPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicContentTest {

    @Test
    public void testDynamicContentChanges() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        DynamicContentPage dynamicContentPage = new DynamicContentPage(driver);

        try {
            // 1. Aceder à página
            dynamicContentPage.abrirPagina();

            // 2. Recolher o conteúdo da primeira secção (texto)
            String textoAntes= dynamicContentPage.obterPrimeiroTexto();

            // 3. Fazer refresh
            dynamicContentPage.atualizarPagina();

            // 4. Recolher novamente
            String textoDepois= dynamicContentPage.obterPrimeiroTexto();
            // 5. Verificar que o conteúdo mudou
            Assertions.assertNotEquals(textoAntes, textoDepois,
                    "O conteúdo dinâmico deveria ter mudado após o refresh!");

        } finally {
            driver.quit();
        }
    }
}



