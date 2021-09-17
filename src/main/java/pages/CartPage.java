package pages;

import decorator.custom.webelements.TextBlock;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span")
    private TextBlock price;

    @FindBy(css = "a.cart-product__title")
    private TextBlock title;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Get the total order value from the cart")
    public int getOrderPriceTotal() {
        return Integer.parseInt(price.safeGetText(15));
    }

    @Step("Get the product name from the cart")
    public String getTitle() {
        return title.safeGetText(15);
    }
}

