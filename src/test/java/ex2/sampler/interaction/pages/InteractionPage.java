package ex2.sampler.interaction.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class InteractionPage {

    private final String url = "https://demo.vaadin.com/sampler";

    // Primeiro clicável (abre o exemplo Interaction)
    private SelenideElement interactionExample =
            $x("//*[@id='listing']/div[1]/div/div/div[1]/div[2]/div[2]");

    // Botão interno com texto "Click"
    private SelenideElement clickButton =
            $x("//*[@id='display']/div[5]/div[2]/div[2]/div/div/div");

    // Mensagem final "The button was clicked"
    private SelenideElement messageLabel =
            $x("//*[@id='sampler-1864843272-overlays']/div[2]");

    public void openPage() {
        open(url);
    }

    public void clickInteractionExample() {
        executeJavaScript("arguments[0].click();", interactionExample);
    }

    public void clickButton() {
        clickButton.shouldBe(visible).click();
    }

    public String getMessageText() {
        return messageLabel.shouldBe(visible).getText();
    }
}



