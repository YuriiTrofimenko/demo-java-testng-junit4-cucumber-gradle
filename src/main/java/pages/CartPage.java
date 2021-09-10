package pages;

import decorator.custom.webelements.TextBlock;
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

    public int getOrderPriceTotal() {
        return Integer.parseInt(price.safeGetText(15));
    }

    public String getTitle() {
        return title.safeGetText(15);
    }
}

