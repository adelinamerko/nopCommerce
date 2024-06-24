package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.utils.Driver;
import selenium.training.utils.Wait;

public class DashboardPage extends BasePage {

    private final NavigationPage navigationPage;

    public DashboardPage() {
        super();
        this.navigationPage = new NavigationPage();
    }

    @FindBy(linkText = "Computers")
    public WebElement btnComputerMenu;

    @FindBy(linkText = "Notebooks")
    private WebElement btnNotebooks;

    private void hoverOverComputersButton() {
        Actions actions = new Actions(Driver.getDriver());
        Wait.getWait().until(ExpectedConditions.visibilityOf(btnComputerMenu));
        actions.moveToElement(btnComputerMenu).perform();
    }

    public void navigateToNotebooksPage() {
        hoverOverComputersButton();
        Wait.getWait().until(ExpectedConditions.elementToBeClickable(btnComputerMenu));
        btnNotebooks.click();
    }

    public void navigateToLoginPage() {
        navigationPage.navigateToHomePage();
        navigationPage.navigateToLoginPage();
    }

}
