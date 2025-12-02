package test_suite6.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import test_suite6.pages.DataInputPage;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Vaadin Sampler")
@Feature("Rich Text Area Component") // Atualizado para refletir o componente real
public class DataInputTest {

    @BeforeAll
    static void setup() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        // Aumentamos o timeout global porque o Vaadin/GWT é lento para carregar os scripts
        Configuration.timeout = 15000;
    }

    @Test
    void testRichTextEditorInput() {
        String textoDeTeste = "Testing Selenium com Iframe";

        DataInputPage page = new DataInputPage()
                .openPage() // Abre, busca "Rich text area" e clica
                .setComment(textoDeTeste); // Digita no iframe

        // VERIFICAÇÃO:
        // Como não existe botão "Submit" que junta nome + comentário nessa página,
        // validamos se o texto realmente ficou gravado dentro do editor.
        assertEquals(textoDeTeste, page.getResult());
    }
}