package ex1B.basic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class CheckboxesPage {

    private WebDriver driver;

    // Locators
    private By checkbox1 = By.xpath("//*[@id='checkboxes']/input[1]");
    private By checkbox2 = By.xpath("//*[@id='checkboxes']/input[2]");

    // Constructor
    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void open() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    public void clickCheckbox1() {
        driver.findElement(checkbox1).click();
    }

    public void clickCheckbox2() {
        driver.findElement(checkbox2).click();
    }

    public boolean isCheckbox1Selected() {
        return driver.findElement(checkbox1).isSelected();
    }

    public boolean isCheckbox2Selected() {
        return driver.findElement(checkbox2).isSelected();
    }
}
