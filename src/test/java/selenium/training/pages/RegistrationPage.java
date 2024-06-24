package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import selenium.training.utils.Wait;

public class RegistrationPage extends BasePage {

    public static final String EXPECTED_TITLE = "Register";
    public static final String SUCCESSFUL_REGISTRATION_MSG = "Your registration completed";

    private final NavigationPage navigationPage;

    public RegistrationPage() {
        super();
        navigationPage = new NavigationPage();
    }

    //region Registration Form
    @FindBy(id = "gender-male")
    public WebElement rdoGenderMale;

    @FindBy(id = "FirstName")
    public WebElement txtFirstName;

    @FindBy(id = "LastName")
    public WebElement txtLastName;

    @FindBy(name = "DateOfBirthDay")
    public WebElement ddlBirthDayElement;
    public Select ddlBirthDay;

    @FindBy(name = "DateOfBirthMonth")
    public WebElement ddlBirthMonthElement;
    public Select ddlBirthMonth;

    @FindBy(name = "DateOfBirthYear")
    public WebElement ddlBirthYearElement;
    public Select ddlBirthYear;

    @FindBy(id = "Email")
    public WebElement txtEmail;

    @FindBy(id = "Company")
    public WebElement txtCompany;

    @FindBy(id = "Password")
    public WebElement txtPassword;

    @FindBy(id = "ConfirmPassword")
    public WebElement txtConfirmPassword;

    @FindBy(css = "#register-button")
    public WebElement btnSubmitRegister;

    @FindBy(css = "div.result")
    public WebElement divResult;

    @FindBy(css = "div.page-title>h1")
    public WebElement registerPageTitle;
    //endregion

    public void fillRegistrationForm(String firstName, String lastName, String birthDate, String email, String company, String password, String confirmPassword) {
        rdoGenderMale.click();

        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);

        txtLastName.clear();
        txtLastName.sendKeys(lastName);

        selectBirthDate(birthDate);

        txtEmail.clear();
        txtEmail.sendKeys(email);

        txtCompany.clear();
        txtCompany.sendKeys(company);

        txtPassword.clear();
        txtPassword.sendKeys(password);

        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(confirmPassword);

    }

    public void register(String firstName, String lastName, String birthDate, String email, String company, String password, String confirmPassword) {
        navigateToRegistrationPage();
        fillRegistrationForm(firstName, lastName, birthDate, email, company, password, confirmPassword);
        submitRegistrationForm();
    }

    public void submitRegistrationForm() {
        btnSubmitRegister.click();
    }

    public void navigateToRegistrationPage() {
        navigationPage.navigateToHomePage();
        navigationPage.navigateToLoginPage();
        navigationPage.navigateToRegisterPage();
    }

    public void logout() {
        navigationPage.logout();
    }

    private void selectBirthDate(String birthDate) {
        String[] dateParts = birthDate.split("/");
        String day = dateParts[0];
        String month = dateParts[1];
        String year = dateParts[2];

        Wait.getWait().until(d -> ddlBirthDayElement.isDisplayed());
        ddlBirthDay = new Select(ddlBirthDayElement);

        Wait.getWait().until(d -> ddlBirthMonthElement.isDisplayed());
        ddlBirthMonth = new Select(ddlBirthMonthElement);

        Wait.getWait().until(d -> ddlBirthYearElement.isDisplayed());
        ddlBirthYear = new Select(ddlBirthYearElement);

        ddlBirthDay.selectByValue(day);
        ddlBirthMonth.selectByValue(month);
        ddlBirthYear.selectByValue(year);
    }

    public String getPageTitle() {
        return registerPageTitle.getText();
    }

    public String getRegistrationResult() {
        return divResult.getText();
    }

}
