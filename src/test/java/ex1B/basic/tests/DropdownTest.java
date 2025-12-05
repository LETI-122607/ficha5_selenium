package ex1B.basic.tests;

import ex1B.basic.pages.DropdownPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DropdownTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            DropdownPage page = new DropdownPage(driver);
            page.open();

            // Selecionar Option 1
            page.selectOption1();
            if (!page.getSelectedOption().equals("Option 1")) {
                System.out.println("❌ ERRO: Option 1 deveria estar selecionada.");
                System.exit(1);
            }

            // Selecionar Option 2
            page.selectOption2();
            if (!page.getSelectedOption().equals("Option 2")) {
                System.out.println("❌ ERRO: Option 2 deveria estar selecionada.");
                System.exit(1);
            }

            System.out.println("✔ TESTE PASSOU: Dropdown funcionando corretamente.");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            driver.quit();
        }
    }
}


