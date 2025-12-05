package ex2.input.pages;

import com.codeborne.selenide.ElementsCollection; // Importar isso
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import java.time.Duration;

public class DataInputPage {

    private static final String URL = "https://demo.vaadin.com/sampler/";

    private final SelenideElement searchBox = $("#searchbox");

    // CORREÇÃO 1: Usamos uma coleção ($$) para poder filtrar qual está visível
    private final ElementsCollection searchResults = $$(".searchresult");

    private final SelenideElement richTextFrame = $(".gwt-RichTextArea");
    private final SelenideElement richTextBody = $("body");

    public DataInputPage openPage() {
        open(URL);

        // CORREÇÃO 2: .pressEnter() é crucial para disparar o evento de busca no GWT
        searchBox.shouldBe(visible, Duration.ofSeconds(15))
                .setValue("Rich text area")
                .pressEnter();

        // CORREÇÃO 3: Procuramos na lista de resultados UM que esteja visível e contenha o texto
        // Isso ignora o elemento oculto que estava causando o erro
        searchResults.findBy(visible)
                .shouldHave(text("Rich text area"), Duration.ofSeconds(10))
                .click();

        return this;
    }

    public DataInputPage setComment(String comment) {
        // Agora o iframe deve carregar
        richTextFrame.should(exist, Duration.ofSeconds(15));

        switchTo().frame(richTextFrame);
        richTextBody.click();
        richTextBody.sendKeys(comment);
        switchTo().defaultContent();

        return this;
    }

    public String getResult() {
        switchTo().frame(richTextFrame);
        String text = $("body").getText();
        switchTo().defaultContent();
        return text;
    }
}