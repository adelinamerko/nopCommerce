package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.training.pages.RegistrationPage;

import static selenium.training.utils.RegistrationDetails.EMAIL;
import static selenium.training.utils.RegistrationDetails.PASSWORD;
import static selenium.training.utils.RegistrationDetails.FIRST_NAME;
import static selenium.training.utils.RegistrationDetails.LAST_NAME;
import static selenium.training.utils.RegistrationDetails.COMPANY;
import static selenium.training.utils.RegistrationDetails.BIRTH_DATE;

public class RegistrationPageTest {

    private final RegistrationPage registrationPage;

    public RegistrationPageTest() {
        registrationPage = new RegistrationPage();
    }

    @Test
    public void successfulRegistrationTest() {
        registrationPage.navigateToRegistrationPage();

        Assert.assertEquals(RegistrationPage.EXPECTED_TITLE, registrationPage.getPageTitle());

        registrationPage.register(FIRST_NAME, LAST_NAME, BIRTH_DATE, EMAIL, COMPANY, PASSWORD, PASSWORD);

        Assert.assertEquals(RegistrationPage.SUCCESSFUL_REGISTRATION_MSG, registrationPage.getRegistrationResult());

        registrationPage.logout();
    }
}

