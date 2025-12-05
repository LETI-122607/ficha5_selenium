package ex2.sampler.interaction.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.qameta.allure.Description;
import ex2.sampler.interaction.pages.InteractionPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InteractionTest {

    private final InteractionPage page = new InteractionPage();

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demo.vaadin.com";
    }

    @Test
    @Description("Test Suite 5 - Verificar componente de Interaction")
    public void testInteractionComponent() {
        page.openPage();

        // Primeiro clicável → abre o exemplo
        page.clickInteractionExample();

        // Botão interno → "Click"
        page.clickButton();

        // Mensagem final
        String result = page.getMessageText();
        assertEquals("The button was clicked", result,
                "Mensagem após interação deve ser 'The button was clicked'");
    }

}

