package selenium.training.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.training.enums.AddToType;
import selenium.training.utils.Driver;
import selenium.training.utils.Wait;

public class NotebooksPage extends BasePage {

    public static final String EXPECTED_TITLE = "Notebooks";
    public static final String EXPECTED_ADDED_TO_WISHLIST_SUCCESS_MESSAGE = "The product has been added to your wishlist";
    public static final String EXPECTED_ADDED_TO_CART_SUCCESS_MESSAGE = "The product has been added to your shopping cart";

    @FindBy(css = "div.bar-notification.success")
    public WebElement divSuccessNotification;

    @FindBy(css = "div.page-title>h1")
    public WebElement pageTitle;

    public void addProductTo(int elementIndex, AddToType addToType) {
        String itemCssSelector = "div.item-grid > div.item-box:nth-child(" + elementIndex + ")";
        String addToWishListCssSelector = "button.button-2.add-to-wishlist-button";
        String addToCartCssSelector = "button.button-2.product-box-add-to-cart-button";

        WebElement addToWishListItem = Driver.getDriver().findElement(By.cssSelector(itemCssSelector));
        WebElement btnAddToCart = addToWishListItem.findElement(By.cssSelector(addToCartCssSelector));
        WebElement btnAddToWishlist = addToWishListItem.findElement(By.cssSelector(addToWishListCssSelector));


        switch (addToType) {
            case WISHLIST:
                Wait.getWait().until(ExpectedConditions.elementToBeClickable(btnAddToWishlist));
                btnAddToWishlist.click();
                break;
            case CART:
                Wait.getWait().until(ExpectedConditions.elementToBeClickable(btnAddToCart));
                btnAddToCart.click();
                break;
        }
    }

    public String getSuccessMessage() {
        WebElement contentParagraph = divSuccessNotification.findElement(By.cssSelector("p.content"));
        WebElement closeSpan = divSuccessNotification.findElement(By.cssSelector("span.close"));

        String message = contentParagraph.getText();
        closeSpan.click();

        Wait.getWait().until(ExpectedConditions.invisibilityOfAllElements(divSuccessNotification, contentParagraph, closeSpan));

        return message;
    }

    public String getPageTitle() {
        Wait.getWait().until(driver -> pageTitle.isDisplayed());
        return pageTitle.getText();
    }

}
