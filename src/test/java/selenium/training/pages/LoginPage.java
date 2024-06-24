package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.training.utils.Wait;

public class LoginPage extends BasePage {

    public static final String EXPECTED_SUCCESS_MESSAGE = "Welcome to our store";

    private final NavigationPage navigationPage;

    public LoginPage() {
        super();
        navigationPage = new NavigationPage();
    }

    @FindBy(id = "Email")
    public WebElement txtEmail;

    @FindBy(id = "Password")
    public WebElement txtPassword;

    @FindBy(css = ".button-1.login-button")
    public WebElement btnSubmitLogin;

    @FindBy(css = ".topic-block-title>h2")
    public WebElement lblSuccessMessage;

    public void login(String email, String password) {
        txtEmail.clear();
        txtEmail.sendKeys(email);

        txtPassword.clear();
        txtPassword.sendKeys(password);

        btnSubmitLogin.click();
    }

    public void logout() {
        navigationPage.logout();
    }

    public void navigateToLoginPage() {
        navigationPage.navigateToHomePage();
        navigationPage.navigateToLoginPage();
    }

    public String getSuccessMessage() {
        Wait.getWait().until(driver -> lblSuccessMessage.isDisplayed());
        return lblSuccessMessage.getText();
    }

}
