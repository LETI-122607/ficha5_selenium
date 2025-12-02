package ex1B.basic.tests;

import ex1B.basic.pages.CheckboxesPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CheckboxesTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            CheckboxesPage page = new CheckboxesPage(driver);
            page.open();

            // Garantir que checkbox 1 fica selecionada
            if (!page.isCheckbox1Selected()) {
                page.clickCheckbox1();
            }

            // Garantir que checkbox 2 fica desmarcada
            if (page.isCheckbox2Selected()) {
                page.clickCheckbox2();
            }

            // "Asserts" manuais
            if (!page.isCheckbox1Selected()) {
                System.out.println("❌ ERRO: Checkbox 1 deveria estar selecionada.");
                System.exit(1);
            }

            if (page.isCheckbox2Selected()) {
                System.out.println("❌ ERRO: Checkbox 2 deveria estar desmarcada.");
                System.exit(1);
            }

            System.out.println("✔ TESTE PASSOU: Checkboxes funcionando corretamente.");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}
