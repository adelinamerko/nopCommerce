package selenium.training.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.enums.AddToType;
import selenium.training.utils.Driver;
import selenium.training.utils.GlobalConfigs;
import selenium.training.utils.Wait;

public class NavigationPage extends BasePage {

    @FindBy(css = ".ico-login")
    public WebElement btnLogin;

    @FindBy(css = ".ico-logout")
    public WebElement btnLogout;

    @FindBy(css = ".ico-register")
    public WebElement btnRegister;

    @FindBy(css = ".ico-cart")
    public WebElement btnShoppingCart;

    @FindBy(css = "a.ico-wishlist > span.wishlist-qty")
    WebElement qtyWishlist;

    @FindBy(css = "a.ico-cart > span.cart-qty")
    WebElement qtyCart;

    @FindBy(css = "div.mini-shopping-cart  button.button-1.cart-button")
    WebElement btnGoToCart;

    public void navigateToLoginPage() {
        Wait.getWait().until(driver -> btnLogin.isEnabled());
        btnLogin.click();
    }

    public void navigateToRegisterPage() {
        Wait.getWait().until(driver -> btnRegister.isEnabled());
        btnRegister.click();
    }

    public void navigateToShoppingCartPage() {
        Wait.getWait().until(driver -> btnShoppingCart.isEnabled());
        btnShoppingCart.click();
    }

    public void navigateToShoppingCartPage(boolean hover) {
        Wait.getWait().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(btnShoppingCart));
        if (hover) {
            Actions actions = new Actions(Driver.getDriver());
            actions.moveToElement(btnShoppingCart).perform();
            Wait.getWait().until(driver -> btnGoToCart.isDisplayed());
            btnGoToCart.click();
        } else {
            navigateToShoppingCartPage();
        }
    }

    public void logout() {
        Wait.getWait().until(driver -> btnLogout.isEnabled());
        btnLogout.click();
    }

    public void navigateToHomePage() {
        Driver.getDriver().get(GlobalConfigs.URL);
    }

    public int getQty(AddToType addToType) {
        String qty = "";
        switch (addToType) {
            case WISHLIST:
                Wait.getWait().until(driver -> qtyWishlist.isDisplayed());
                qty = qtyWishlist.getText();
                break;
            case CART:
                Wait.getWait().until(driver -> qtyCart.isDisplayed());
                qty = qtyCart.getText();
                break;
        }
        return Integer.parseInt(qty.replaceAll("[^0-9]", ""));
    }

}
