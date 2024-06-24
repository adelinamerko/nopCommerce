package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.training.pages.LoginPage;

import static selenium.training.utils.RegistrationDetails.EMAIL;
import static selenium.training.utils.RegistrationDetails.PASSWORD;

public class LoginPageTest {

    private final LoginPage loginPage;

    public LoginPageTest() {
        loginPage = new LoginPage();
    }

    @Test
    public void successfulLoginTest() {
        loginPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);
        Assert.assertEquals(loginPage.getSuccessMessage(), LoginPage.EXPECTED_SUCCESS_MESSAGE);
        loginPage.logout();
    }
}
