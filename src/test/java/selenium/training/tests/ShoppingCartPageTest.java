package selenium.training.tests;

import org.junit.jupiter.api.AfterAll;
import org.testng.annotations.Test;
import selenium.training.pages.*;
import selenium.training.utils.CheckoutSteps;
import selenium.training.utils.Driver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static selenium.training.utils.RegistrationDetails.FIRST_NAME;
import static selenium.training.utils.RegistrationDetails.LAST_NAME;
import static selenium.training.utils.RegistrationDetails.EMAIL;
import static selenium.training.utils.RegistrationDetails.COMPANY;

public class ShoppingCartPageTest {

    private final ShoppingCartPage shoppingCartPage;


    public ShoppingCartPageTest() {
        this.shoppingCartPage = new ShoppingCartPage();
    }

    @AfterAll
    public static void tearDown() {
        Driver.getDriver().quit();
    }

    @Test
    public void testCheckoutOrder() {
        shoppingCartPage.navigateToShoppingCartPage();

        assertEquals(shoppingCartPage.getPageTitle(), ShoppingCartPage.EXPECTED_PAGE_TITLE);

        assertTrue(shoppingCartPage.areCartOptionsButtonsDisplayed(), "Cart options buttons are not displayed");

        assertEquals(shoppingCartPage.getCalculatedProductsTotalPrice(), shoppingCartPage.getTotalPrice(), "Total price is not as expected");

        shoppingCartPage.acceptTermsOfService();
        shoppingCartPage.checkout();

        assertEquals(shoppingCartPage.getFirstName(), FIRST_NAME, "First name is not as expected");
        assertEquals(shoppingCartPage.getLastName(), LAST_NAME, "Last name is not as expected");
        assertEquals(shoppingCartPage.getEmail(), EMAIL, "Email is not as expected");
        assertEquals(shoppingCartPage.getCompany(), COMPANY, "Company is not as expected");

        shoppingCartPage.enterBillingAddressDetails("Albania", "Tirana", "Rruga e Kavajes", "1000", "1234567890");
        shoppingCartPage.checkoutNextStep(CheckoutSteps.BILLING_ADDRESS);

        shoppingCartPage.chooseNextDayAirShippingMethod();
        shoppingCartPage.checkoutNextStep(CheckoutSteps.SHIPPING_METHOD);

        shoppingCartPage.chooseCashOnDeliveryPaymentMethod();
        shoppingCartPage.checkoutNextStep(CheckoutSteps.PAYMENT_METHOD);

        shoppingCartPage.checkoutNextStep(CheckoutSteps.PAYMENT_INFORMATION);

        assertEquals(shoppingCartPage.getTotalPrice(), shoppingCartPage.getTotalPriceValue(), "Total price is not as expected");
        shoppingCartPage.checkoutNextStep(CheckoutSteps.CONFIRM_ORDER);

        assertTrue(shoppingCartPage.isOrderSuccessful(), "Order is not successful");
    }
}
