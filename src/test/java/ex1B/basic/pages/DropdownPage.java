package ex1B.basic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {

    private WebDriver driver;

    // Locator
    private By dropdown = By.id("dropdown");

    public DropdownPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    public void selectOption1() {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByVisibleText("Option 1");
    }

    public void selectOption2() {
        Select select = new Select(driver.findElement(dropdown));
        select.selectByVisibleText("Option 2");
    }

    public String getSelectedOption() {
        Select select = new Select(driver.findElement(dropdown));
        return select.getFirstSelectedOption().getText();
    }
}
