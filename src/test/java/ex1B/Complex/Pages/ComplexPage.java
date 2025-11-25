package ex1B.Complex.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ComplexPage {

    private WebDriver driver;

    // O campo onde o computador escreve o caminho do ficheiro
    private By inputFicheiro = By.id("file-upload");
    // O botão azul de enviar
    private By botaoUpload = By.id("file-submit");
    // Mensagem de título após o sucesso
    private By mensagemSucesso = By.tagName("h3");
    // Área onde aparece o nome do ficheiro carregado
    private By ficheiroCarregado = By.id("uploaded-files");

    public ComplexPage(WebDriver driver) {
        this.driver = driver;
    }

    public void abrirPagina() {
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    // Método crucial: O Selenium precisa do caminho ABSOLUTO para o ficheiro
    public void escolherFicheiro(String caminhoAbsoluto) {
        driver.findElement(inputFicheiro).sendKeys(caminhoAbsoluto);
    }

    public void clicarNoBotaoUpload() {
        driver.findElement(botaoUpload).click();
    }

    public String obterUrlPagina() {
        // Retorna o URL da página que o driver está a visitar
        return driver.getCurrentUrl();
    }

    public String obterMensagemSucesso() {
        return driver.findElement(mensagemSucesso).getText();
    }

    public String obterNomeDoFicheiroNaLista() {
        return driver.findElement(ficheiroCarregado).getText();
    }

    public boolean isInputFicheiroVisivel() {
        return driver.findElement(inputFicheiro).isDisplayed();
    }

    public boolean isInputFicheiroHabilitado() {
        return driver.findElement(inputFicheiro).isEnabled();
    }
}