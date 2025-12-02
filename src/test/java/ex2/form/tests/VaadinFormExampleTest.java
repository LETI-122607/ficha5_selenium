package ex2.form.tests;

import ex2.form.pages.VadinFormExampleDemoPage;
import org.junit.jupiter.api.Test;
public class VaadinFormExampleTest {

    @Test
    public void acceptanceFillForm() {
        VadinFormExampleDemoPage page = VadinFormExampleDemoPage.openPage();
        String userHandle = "@joaos";
        page.setName("João Silva")
                .setUserHandle("@joaos")
                .setPassword("P4ssw0rd")

                .submit();

        // ajuste o texto esperado conforme a aplicação
       // page.shouldSeeSuccess("Data saved, welcome " + userHandle);
    }
}
