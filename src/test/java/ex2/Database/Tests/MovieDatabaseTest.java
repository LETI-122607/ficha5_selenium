package ex2.Database.Tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import ex2.Database.Pages.VaadinDatabaseExampleDemoPage; // CORRIGIDO: Usa o nome e package corretos
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

@Epic("EX2 - Automação Web")
@Feature("Database Module: Filmes")
public class MovieDatabaseTest {

    private final VaadinDatabaseExampleDemoPage moviePage = new VaadinDatabaseExampleDemoPage();

    // URLs IMDB dos filmes
    private static final String URL_LAW_ABIDING = "https://www.imdb.com/title/tt1197624/";
    private static final String URL_KNIVES_OUT = "https://www.imdb.com/title/tt8946378/";
    private static final String URL_LAST_JEDI = "https://www.imdb.com/title/tt2527336/";

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true));
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        // Opcional: para maior estabilidade ao mudar de janelas
        Configuration.reportsFolder = "build/reports/tests";
    }

    @BeforeEach
    void carregarPagina() {
        moviePage.abrirPagina(); // Abre o URL obrigatório
        moviePage.validarPaginaCarregada(); // Valida que a tabela de filmes está visível
    }

    @Test
    @DisplayName("Ordenar a tabela pelo Título (Ascendente e Descendente)")
    void testOrdenarTitle() {
        moviePage.clicarParaOrdenar("Title"); // 1º clique (Ascendente)
        moviePage.clicarParaOrdenar("Title"); // 2º clique (Descendente)
    }

    @Test
    @DisplayName("Ordenar a tabela pelo Ano de Lançamento (Release Year)")
    void testOrdenarReleaseYear() {
        moviePage.clicarParaOrdenar("Release Year");
    }

    @Test
    @DisplayName("Validar e clicar no link IMDB do filme 'Law Abiding Citizen'")
    void testLinkLawAbidingCitizen() {
        moviePage.clicarNoLinkPelaPosicao(0, URL_LAW_ABIDING);
    }

    @Test
    @DisplayName("Validar e clicar no link IMDB do filme 'Knives Out'")
    void testLinkKnivesOut() {
        moviePage.clicarNoLinkPelaPosicao(1, URL_KNIVES_OUT);
    }


     @Test
     @DisplayName("Validar e clicar no link IMDB do filme 'The Last Jedi'")
     void testLinkLastJedi() {
         moviePage.clicarNoLinkPelaPosicao(2, URL_LAST_JEDI);
     }
}