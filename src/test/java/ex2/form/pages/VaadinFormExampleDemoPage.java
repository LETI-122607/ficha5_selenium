package ex2.form.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VaadinFormExampleDemoPage {
    private final String url = "https://vaadin-form-example.demo.vaadin.com/";

    // Uso de XPath com alternativas para evitar problemas com seletores compostos
    @FindBy(xpath = "/html/body/vaadin-vertical-layout/vaadin-form-layout/vaadin-text-field[1]//div/div[1]/slot[2]/input")
    private SelenideElement nameInput;
    @FindBy(xpath = "//input[@placeholder='User handle' or @id='userhandle' or @name='handle']")
    private SelenideElement handleInput;

    @FindBy(xpath = "//input[@placeholder='Password' or @type='password' or @id='password']")
    private SelenideElement passwordInput;

    @FindBy(xpath = "//button[@type='submit' or @title='Submit' or contains(normalize-space(.),'Submit')]")
    private SelenideElement submitButton;

    @FindBy(css = ".notification, .result, #success")
    private SelenideElement successMessage;

    public static VaadinFormExampleDemoPage openPage() {
        Selenide.open("https://vaadin-form-example.demo.vaadin.com/");
        return Selenide.page(VaadinFormExampleDemoPage.class);
    }

    // métodos fluent para preencher o form
    //  public VaadinFormExampleDemoPage setName(String name) {
    //      nameInput.setValue(name);
    //    return this;
    //  }
    public VaadinFormExampleDemoPage setName(String name) {
        String[] partes = name.trim().split("\\s+");
        String nome1 = partes[0];
        String nome2 = partes[1];
        WebElement input = Selenide.executeJavaScript(
                "return document.querySelector('vaadin-text-field').shadowRoot.querySelector('input')");
        WebElement input1 = Selenide.executeJavaScript(
                "return document.querySelectorAll('vaadin-text-field')[1]" +
                        ".shadowRoot.querySelector('input')"
        );
        ;
        $(input).setValue(nome1);
        $(input1).setValue(nome2);
        return this;
    }

    // public VaadinFormExampleDemoPage setUserHandle(String handle) {
    //    handleInput.setValue(handle);
    //    return this;
    // }
    public VaadinFormExampleDemoPage setUserHandle(String handle) {
        WebElement input = Selenide.executeJavaScript(
                "return document.querySelectorAll('vaadin-text-field')[2]" +
                        ".shadowRoot.querySelector('input')"
        );
        SelenideElement handleInput = $(input);
        handleInput.setValue(handle);
        return this;
    }

    // public VaadinFormExampleDemoPage setPassword(String password) {
    //   passwordInput.setValue(password);
    //   return this;
    //}
    public VaadinFormExampleDemoPage setPassword(String password) {
        WebElement input = Selenide.executeJavaScript(
                "return document.querySelectorAll('vaadin-password-field')[0]" +
                        ".shadowRoot.querySelector('input')"
        );
        WebElement input2 = Selenide.executeJavaScript(
                "return document.querySelectorAll('vaadin-password-field')[1]" +
                        ".shadowRoot.querySelector('input')"
        );
        SelenideElement handleInput = $(input);
        SelenideElement handleInput2 = $(input2);
        handleInput.setValue(password);
        handleInput2.setValue(password);
        return this;
    }

    // public VaadinFormExampleDemoPage submit() {
//submitButton.click();
    //    return this;
    // }
    public VaadinFormExampleDemoPage submit() {
        WebElement button = Selenide.executeJavaScript(
                "return document.querySelector('vaadin-button')" +
                        ".shadowRoot.querySelector('button')"
        );

        $(button).click();
        return this;
    }

    // verificação simples de sucesso (ajuste o texto conforme necessário)
    public void shouldSeeSuccess(String expectedText) {

        WebElement msg = null;

        for (int i = 0; i < 10; i++) {
            msg = Selenide.executeJavaScript(
                    """
                    const cards = [...document.querySelectorAll('vaadin-notification-card')];
                    return cards.find(c => c.innerText.includes(arguments[0]));
                    """,
                    expectedText
            );

            if (msg != null) break;

            Selenide.sleep(500);
        }

        $(msg)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }
}
