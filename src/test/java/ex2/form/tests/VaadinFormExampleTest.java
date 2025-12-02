package ex2.form.tests;
import ex2.form.pages.VaadinFormExampleDemoPage;
import org.junit.jupiter.api.Test;
public class VaadinFormExampleTest {

    @Test
    public void acceptanceFillForm() {
        VaadinFormExampleDemoPage page = VaadinFormExampleDemoPage.openPage();
        String userHandle = "@joaos";
        page.setName("João Silva")
                .setUserHandle("@joaos")
                .setPassword("P4ssw0rd")

                .submit();

        // ajuste o texto esperado conforme a aplicação
        page.shouldSeeSuccess("Data saved, welcome " + userHandle);
    }
}
