package SaucedemoTestiranje.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {

    /*
    pronalazi sve web elemente koji imaju class="cart_item".
    Posto svaki item dodat u korpu mora da ima ovaj class name. (proizvod)

    ovo je isto sto i driver.findWebElements(By.className("cart_item"))
     */
    @FindAll(@FindBy(className = "cart_item"))
    public List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    public WebElement continueShopping;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public WebElement getContinueShopping() {
        return continueShopping;
    }

    public void clickOnContinueShopping() {
        getContinueShopping().click();
    }

    public boolean isCartEmpty() {
        if (getCartItems().size() == 0) {
            // ima proizvoda u listi
            return true;
        } else {
            return false;
        }
    }
}
