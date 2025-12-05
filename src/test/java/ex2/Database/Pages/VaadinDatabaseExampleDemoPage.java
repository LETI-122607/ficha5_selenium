package ex2.Database.Pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

// Nome da classe que reflete o conteúdo
public class VaadinDatabaseExampleDemoPage {

    private static final String URL_CORRETO = "https://vaadin-database-example.demo.vaadin.com/";

    private final SelenideElement grid = $("vaadin-grid");

    // Locators robustos: Encontrar o elemento de ordenação (vaadin-grid-sorter) dentro do texto da coluna.
    // O Selenide é inteligente: se clicarmos no texto, o elemento sorter dentro é ativado.
    private final SelenideElement titleHeader = grid.$(byText("Title"));
    private final SelenideElement releaseYearHeader = grid.$(byText("Release Year"));
    // O grid tem 4 colunas de dados visíveis (Title, Year, Director, Link)
    private static final int COLUNAS_POR_LINHA = 4;
    // O link IMDB é a 4ª coluna, que é o índice 3 (0-base).
    private static final int INDICE_LINK_DENTRO_DA_LINHA = 3;

    @Step("Abrir a página da Base de Dados de Filmes (URL: " + URL_CORRETO + ")")
    public void abrirPagina() {
        // Usa o URL obrigatório, que agora sabemos que carrega a tabela de filmes.
        Selenide.open(URL_CORRETO);
    }

    @Step("Validar que a página carregou")
    public void validarPaginaCarregada() {
        // Procura o título que está visível na página de filmes
        SelenideElement titulo = $(byText("Accessing in-memory database using JdbcTemplate"));
        titulo.shouldBe(visible);

        grid.shouldBe(visible);

        // Verifica se pelo menos o primeiro filme está presente
        grid.shouldHave(text("Law Abiding Citizen"));
    }

    @Step("Ordenar coluna '{0}'")
    public void clicarParaOrdenar(String coluna) {
        SelenideElement header;
        if (coluna.equalsIgnoreCase("Title")) {
            header = titleHeader;
        } else if (coluna.equalsIgnoreCase("Release Year")) {
            header = releaseYearHeader;
        } else {
            throw new IllegalArgumentException("Coluna inválida: " + coluna);
        }

        header.click();

        // Valida que a ordenação está ativa (verificando o atributo 'direction' no sorter)
        header.shouldHave(attribute("direction"));
    }

    @Step("Clicar no link IMDB do filme na Linha {0} e validar navegação")
    public void clicarNoLinkPelaPosicao(int indiceLinha, String esperado) {

        // 1. Calcular o índice absoluto da célula do link no DOM completo da grelha.
        // Índice Absoluto = (Posição da Linha * Colunas por Linha) + Índice da Coluna de Link
        int indiceAbsolutoCelula = (indiceLinha * COLUNAS_POR_LINHA) + INDICE_LINK_DENTRO_DA_LINHA;

        // 2. Aceder diretamente à célula do link na grelha usando o índice absoluto.
        // grid.$$("vaadin-grid-cell-content") obtém TODAS as células da grelha em sequência.
        SelenideElement celulaLink = grid.$$("vaadin-grid-cell-content").get(indiceAbsolutoCelula);

        // Garante que o elemento está visível e pronto antes de interagir.
        celulaLink.shouldBe(visible);

        // 3. Dentro da célula, procura a tag <a> (o link).
        SelenideElement linkImdb = celulaLink.find("a");

        // 4. Ação e Validação
        linkImdb.click();

        switchTo().window(1); // Mudar para a nova aba (IMDB)
        webdriver().shouldHave(url(esperado)); // Valida o URL
        closeWindow(); // Fecha a aba
        switchTo().window(0); // Volta para a aba principal
    }

}