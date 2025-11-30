package dynamic.tests;


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

        try {
            // 1. Aceder à página
            driver.get("https://the-internet.herokuapp.com/dynamic_content");

            // 2. Recolher o conteúdo da primeira secção (texto)
            WebElement firstText = driver.findElement(By.xpath("(//div[@id='content']//div[@class='large-10 columns'])[1]"));
            String textoAntes = firstText.getText();

            // 3. Fazer refresh
            driver.navigate().refresh();

            // 4. Recolher novamente
            WebElement firstTextAfter = driver.findElement(By.xpath("(//div[@id='content']//div[@class='large-10 columns'])[1]"));
            String textoDepois = firstTextAfter.getText();

            // 5. Verificar que o conteúdo mudou
            Assertions.assertNotEquals(textoAntes, textoDepois,
                    "O conteúdo dinâmico deveria ter mudado após o refresh!");

        } finally {
            driver.quit();
        }
    }
}



