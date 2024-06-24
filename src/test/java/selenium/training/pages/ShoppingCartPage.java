package selenium.training.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import selenium.training.utils.CheckoutSteps;
import selenium.training.utils.Wait;

import java.util.List;

public class ShoppingCartPage extends BasePage {

    public static final String EXPECTED_ORDER_SUCCESS_MESSAGE = "Your order has been successfully processed!";
    public static final String EXPECTED_PAGE_TITLE = "Shopping cart";

    private double totalPriceValue;
    private final NavigationPage navigationBar;

    public ShoppingCartPage() {
        super();
        navigationBar = new NavigationPage();
    }

    @FindBy(css = "div.page-title>h1")
    private WebElement pageTitle;

    @FindBy(css = "div.cart-options div.common-buttons button.button-2.continue-shopping-button")
    private WebElement continueShoppingButton;

    @FindBy(id = "open-estimate-shipping-popup")
    private WebElement estimateShippingButton;

    @FindBy(css = ".product-subtotal")
    private List<WebElement> productSubtotalPrice;

    @FindBy(css = ".cart-total-right .value-summary strong")
    private WebElement totalPrice;

    @FindBy(id = "termsofservice")
    private WebElement chbTermsOfService;

    @FindBy(id = "checkout")
    private WebElement btnCheckout;

    //region Checkout Form
    @FindBy(id = "BillingNewAddress_FirstName")
    private WebElement txtFirstName;

    @FindBy(id = "BillingNewAddress_LastName")
    private WebElement txtLastName;

    @FindBy(id = "BillingNewAddress_Email")
    private WebElement txtEmail;

    @FindBy(id = "BillingNewAddress_Company")
    private WebElement txtCompany;

    @FindBy(id = "BillingNewAddress_CountryId")
    private WebElement ddlCountryElement;

    @FindBy(id = "BillingNewAddress_City")
    private WebElement txtCity;

    @FindBy(id = "BillingNewAddress_Address1")
    private WebElement txtAddress1;

    @FindBy(id = "BillingNewAddress_ZipPostalCode")
    private WebElement txtZipPostalCode;

    @FindBy(id = "BillingNewAddress_PhoneNumber")
    private WebElement txtPhoneNumber;
    //endregion

    //region Continue Buttons
    @FindBy(css = "#billing-buttons-container button.button-1.new-address-next-step-button")
    private WebElement btnBillingAddressContinue;

    @FindBy(css = "button.button-1.shipping-method-next-step-button")
    private WebElement btnShippingMethodContinue;

    @FindBy(css = "button.button-1.payment-method-next-step-button")
    private WebElement btnPaymentMethodContinue;

    @FindBy(css = "button.button-1.payment-info-next-step-button")
    private WebElement btnPaymentInformationContinue;

    @FindBy(css = "button.button-1.confirm-order-next-step-button")
    private WebElement btnConfirmOrderContinue;
    //endregion

    @FindBy(id = "shippingoption_1")
    private WebElement rdoNextDayAir;

    @FindBy(id = "paymentmethod_0")
    private WebElement rdoCashOnDelivery;

    @FindBy(css = ".section.order-completed .title strong")
    private WebElement orderSuccessMessage;

    @FindBy(css = ".section.order-completed .order-number strong")
    private WebElement orderNumber;

    public void enterBillingAddressDetails(String countryName, String city, String address1, String zipCode, String phoneNumber) {
        selectCountry(countryName);
        enterCity(city);
        enterAddress1(address1);
        enterZipPostalCode(zipCode);
        enterPhoneNumber(phoneNumber);
    }

    public void navigateToShoppingCartPage() {
        navigationBar.navigateToShoppingCartPage(true);
    }

    public void acceptTermsOfService() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(chbTermsOfService));
        chbTermsOfService.click();
    }

    public void checkout() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(btnCheckout));
        btnCheckout.click();
    }

    public void checkoutNextStep(CheckoutSteps currentStep) {
        switch (currentStep) {
            case BILLING_ADDRESS:
                Wait.getWait().until(ExpectedConditions.visibilityOf(btnBillingAddressContinue));
                btnBillingAddressContinue.click();
                break;
            case SHIPPING_METHOD:
                Wait.getWait().until(ExpectedConditions.visibilityOf(btnShippingMethodContinue));
                btnShippingMethodContinue.click();
                break;
            case PAYMENT_METHOD:
                Wait.getWait().until(ExpectedConditions.visibilityOf(btnPaymentMethodContinue));
                btnPaymentMethodContinue.click();
                break;
            case PAYMENT_INFORMATION:
                Wait.getWait().until(ExpectedConditions.visibilityOf(btnPaymentInformationContinue));
                btnPaymentInformationContinue.click();
                break;
            case CONFIRM_ORDER:
                Wait.getWait().until(ExpectedConditions.visibilityOf(btnConfirmOrderContinue));
                btnConfirmOrderContinue.click();
                break;
        }
    }

    public void chooseNextDayAirShippingMethod() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(rdoNextDayAir));
        rdoNextDayAir.click();
    }

    public void chooseCashOnDeliveryPaymentMethod() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(rdoCashOnDelivery));
        rdoCashOnDelivery.click();
    }

    public boolean areCartOptionsButtonsDisplayed() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(continueShoppingButton));
        Wait.getWait().until(ExpectedConditions.visibilityOf(estimateShippingButton));
        return continueShoppingButton.isDisplayed() && estimateShippingButton.isDisplayed();
    }

    public boolean isOrderSuccessful() {
        return getOrderSuccessMessage().equals(EXPECTED_ORDER_SUCCESS_MESSAGE) && getOrderNumber().get(1).matches("\\d+");
    }

    public double getTotalPriceValue() {
        return this.totalPriceValue;
    }

    public double getCalculatedProductsTotalPrice() {
        double sum = 0;
        for (WebElement totalElement : productSubtotalPrice) {
            String totalText = totalElement.getText().replace("$", "").replace(",", "").trim();
            double totalValue = Double.parseDouble(totalText);
            sum += totalValue;
        }
        return sum;
    }

    public double getTotalPrice() {
        String totalPriceText = totalPrice.getText().replace("$", "").replace(",", "").trim();
        this.totalPriceValue = Double.parseDouble(totalPriceText);
        return totalPriceValue;
    }

    public String getPageTitle() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }

    public String getFirstName() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtFirstName));
        return txtFirstName.getAttribute("value");
    }

    public String getLastName() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtLastName));
        return txtLastName.getAttribute("value");
    }

    public String getEmail() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtEmail));
        return txtEmail.getAttribute("value");
    }

    public String getCompany() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtCompany));
        return txtCompany.getAttribute("value");
    }

    private String getOrderSuccessMessage() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(orderSuccessMessage));
        return orderSuccessMessage.getText();
    }

    private List<String> getOrderNumber() {
        Wait.getWait().until(ExpectedConditions.visibilityOf(orderNumber));
        String orderNumberText = orderNumber.getText();
        return List.of(orderNumberText.split(": "));
    }

    private void selectCountry(String countryName) {
        Wait.getWait().until(ExpectedConditions.visibilityOf(ddlCountryElement));
        Select select = new Select(ddlCountryElement);
        select.selectByVisibleText(countryName);
    }

    private void enterCity(String city) {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtCity));
        txtCity.sendKeys(city);
    }

    private void enterAddress1(String address1) {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtAddress1));
        txtAddress1.sendKeys(address1);
    }

    private void enterZipPostalCode(String zipPostalCode) {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtZipPostalCode));
        txtZipPostalCode.sendKeys(zipPostalCode);
    }

    private void enterPhoneNumber(String phoneNumber) {
        Wait.getWait().until(ExpectedConditions.visibilityOf(txtPhoneNumber));
        txtPhoneNumber.sendKeys(phoneNumber);
    }

}


