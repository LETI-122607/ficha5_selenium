package ex1B.Complex.Tests;

import ex1B.Complex.Pages.ComplexPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;

public class ComplexTest {

    private WebDriver driver;
    private ComplexPage uploadPage;
    private final String NOME_FICHEIRO = "teste.txt";
    private String caminhoAbsoluto;


    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        uploadPage = new ComplexPage(driver);

        // Resolve o caminho do ficheiro (feito uma vez)
        File arquivo = new File("src/test/java/" + NOME_FICHEIRO);
        Assertions.assertTrue(arquivo.exists(), "ERRO CRÍTICO: O ficheiro de teste '" + NOME_FICHEIRO + "' não foi encontrado.");
        caminhoAbsoluto = arquivo.getAbsolutePath();
        uploadPage.abrirPagina();
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void EscolherFicheiro() {
        uploadPage.escolherFicheiro(caminhoAbsoluto);
        System.out.println("DEBUG: Ficheiro escolhido com sucesso. Verificação visual necessária.");
    }

    @Test
    public void ValidarMensagemSucesso() {
        uploadPage.escolherFicheiro(caminhoAbsoluto);
        uploadPage.clicarNoBotaoUpload();
        String mensagem = uploadPage.obterMensagemSucesso();
        Assertions.assertEquals("File Uploaded!", mensagem, "A mensagem de sucesso da página de resultados está incorreta.");
    }

    @Test
    public void ValidarNomeFicheiroNaLista() {
        uploadPage.escolherFicheiro(caminhoAbsoluto);
        uploadPage.clicarNoBotaoUpload();
        String ficheiroNaTela = uploadPage.obterNomeDoFicheiroNaLista();
        Assertions.assertTrue(ficheiroNaTela.contains(NOME_FICHEIRO), "O ficheiro apresentado na lista de uploads não contém o nome " + NOME_FICHEIRO + ".");
    }

    @Test
    public void ValidarAcessoAoSite() {
        String tituloEsperado = "The Internet";
        String tituloAtual = driver.getTitle();
        Assertions.assertEquals(tituloEsperado, tituloAtual, "O título da página está incorreto.");
        String url = uploadPage.obterUrlPagina();
        Assertions.assertTrue(url.contains("/upload"), "O URL não contém '/upload', sugerindo que a navegação falhou.");
    }

    @Test
    public void ValidarInputFicheiro() {
        // 1. Verificar se o campo está visível
        boolean isVisivel = uploadPage.isInputFicheiroVisivel();
        Assertions.assertTrue(isVisivel, "O campo 'Escolher ficheiro' não está visível no ecrã.");

        // 2. Verificar se o campo está habilitado (pronto para receber entrada)
        boolean isHabilitado = uploadPage.isInputFicheiroHabilitado();
        Assertions.assertTrue(isHabilitado, "O campo 'Escolher ficheiro' está desabilitado (disabled).");
    }
}