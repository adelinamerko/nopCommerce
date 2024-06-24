package selenium.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selenium.training.enums.AddToType;
import selenium.training.pages.*;

import static org.junit.Assert.assertThat;
import static selenium.training.utils.RegistrationDetails.EMAIL;
import static selenium.training.utils.RegistrationDetails.PASSWORD;

public class DashboardPageTest {

    private final LoginPage loginPage;
    private final DashboardPage dashboardPage;
    private final NotebooksPage notebooksPage;
    private final NavigationPage navigationBar;

    public DashboardPageTest() {
        this.loginPage = new LoginPage();
        this.dashboardPage = new DashboardPage();
        this.notebooksPage = new NotebooksPage();
        this.navigationBar = new NavigationPage();
    }

    @Test
    public void testAddNotebooksToWishlistAndCart() {
        dashboardPage.navigateToLoginPage();
        loginPage.login(EMAIL, PASSWORD);
        dashboardPage.navigateToNotebooksPage();

        //Verify that we have navigate to Notebooks Page
        Assert.assertEquals(notebooksPage.getPageTitle(), NotebooksPage.EXPECTED_TITLE);

        // Add the second adn third items to wishlist
        notebooksPage.addProductTo(2, AddToType.WISHLIST);
        Assert.assertEquals(notebooksPage.getSuccessMessage(), NotebooksPage.EXPECTED_ADDED_TO_WISHLIST_SUCCESS_MESSAGE);
        notebooksPage.addProductTo(3, AddToType.WISHLIST);
        Assert.assertEquals(notebooksPage.getSuccessMessage(), NotebooksPage.EXPECTED_ADDED_TO_WISHLIST_SUCCESS_MESSAGE);

        // Add the fourth & fifth and sixth items to cart
        notebooksPage.addProductTo(4, AddToType.CART);
        Assert.assertEquals(notebooksPage.getSuccessMessage(), NotebooksPage.EXPECTED_ADDED_TO_CART_SUCCESS_MESSAGE);
        notebooksPage.addProductTo(5, AddToType.CART);
        Assert.assertEquals(notebooksPage.getSuccessMessage(), NotebooksPage.EXPECTED_ADDED_TO_CART_SUCCESS_MESSAGE);
        notebooksPage.addProductTo(6, AddToType.CART);
        Assert.assertEquals(notebooksPage.getSuccessMessage(), NotebooksPage.EXPECTED_ADDED_TO_CART_SUCCESS_MESSAGE);

        //Verify that Wishlist on Menu bar displays 2, and Shopping Cart on Menu bar displays 3
        Assert.assertEquals(navigationBar.getQty(AddToType.WISHLIST), 2, "Wishlist quantity is not as expected");
        Assert.assertEquals(navigationBar.getQty(AddToType.CART), 3, "Cart quantity is not as expected");
    }

}

