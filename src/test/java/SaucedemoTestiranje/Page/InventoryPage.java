package SaucedemoTestiranje.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    @FindBy(id = "react-burger-menu-btn")
    public WebElement burgerButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement logOutButton;

    @FindBy(id = "shopping_cart_container")
    public WebElement shoppingCartButton;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement sauceLabsBackpackAddToCartButton;

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    public void clickBurgerButton() {
        burgerButton.click();
    }

    public void clickOnLogOutButton() {
        logOutButton.click();
    }

    public WebElement getShoppingCartButton() {
        return shoppingCartButton;
    }

    public void clickOnShoppingCartButton() {
        getShoppingCartButton().click();
    }

    public WebElement getSauceLabsBackpackAddToCartButton() {
        return sauceLabsBackpackAddToCartButton;
    }

    public void clickOnSauceLabsBackpackAddToCartButton() {

        getSauceLabsBackpackAddToCartButton().click();
    }
}
