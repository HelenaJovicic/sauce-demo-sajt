package SaucedemoTestiranje.LogInTests;

import SaucedemoTestiranje.Base.Base;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogIn extends Base {

    @BeforeMethod
    public void pageSetUp() {
        driver.get(homeUrl);
    }

    @Test
    public void userCanLogIn() throws InterruptedException {
        for (int rows = 1; rows <= 3; rows++) {
            driver.get(homeUrl);

            String validUsername = excelReader.getStringData("LogIn", rows, 0);
            String validPassword = excelReader.getStringData("LogIn", rows, 1);

            Thread.sleep(2000);
            logIn(validUsername, validPassword);
            Thread.sleep(1000);
            String expectedUrl = excelReader.getStringData("URL", 1, 1);
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
            Assert.assertTrue(isDisplayed(inventoryPage.burgerButton));

            inventoryPage.burgerButton.click();
            waitForVisibility(inventoryPage.logOutButton);
            Assert.assertTrue(isDisplayed(inventoryPage.logOutButton));


        }

    }


    @Test // test ce pasti jer ne moze da se uloguje sa lockeduserom
    public void LockedOutUserCannotLogIn() {
        String lockedUsername = excelReader.getStringData("LockedLogIn", 1, 0);
        String validPassword = excelReader.getStringData("LockedLogIn", 1, 1);
        logIn(lockedUsername, validPassword);

        String expectedUrl = excelReader.getStringData("URL", 1, 0);
        //String expectedUrl= "https://www.saucedemo.com/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertFalse(isDisplayed(inventoryPage.logOutButton));
    }

    @Test
    public void userCannotLogInWrongCredentials() {
        for (int rows = 1; rows <= 4; rows++) {
            String invalidUsername = excelReader.getStringData("InvalidLogIn", rows, 0);
            String invalidPassword = excelReader.getStringData("InvalidLogIn", rows, 1);
            logIn(invalidUsername, invalidPassword);

            String expectedUrl = excelReader.getStringData("URL", 1, 0);
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
            Assert.assertEquals(logInPage.getNotificationText(), "Epic sadface: Username and password do not match any user in this service");

        }
    }

    @Test
    public void userCannotLogInEmptyField() {
        logInPage.password.clear();
        logInPage.username.clear();
        logIn("", "");

        String expectedUrl = excelReader.getStringData("URL", 1, 0);
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertEquals(logInPage.getNotificationText(), "Epic sadface: Username is required");
    }

    @Test
    public void userCannotLogInWithInvalidPassword() {
        for (int rows = 1; rows <= 3; rows++) {
            String validUsername = excelReader.getStringData("ValidUserInvalidPass", rows, 0);
            String invalidPassword = excelReader.getStringData("ValidUserInvalidPass", rows, 1);
            logIn(validUsername, invalidPassword);

            String expectedUrl = excelReader.getStringData("URL", 1, 0);
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
            Assert.assertEquals(logInPage.getNotificationText(), "Epic sadface: Username and password do not match any user in this service");

        }
    }

    @Test
    public void userCanAddShoppingItemsInCart() throws InterruptedException {
        String validUsername = excelReader.getStringData("LogIn", 1, 0);
        String validPassword = excelReader.getStringData("LogIn", 1, 1);

        Thread.sleep(2000);
        logIn(validUsername, validPassword);

        //assert that cart is empty
        inventoryPage.clickOnShoppingCartButton();
        Assert.assertTrue(cartPage.isCartEmpty(),
                "When user is logged in and visit shopping cart, the cart should be empty");

        cartPage.clickOnContinueShopping();

        //dodajemo proizvod u korpu
        inventoryPage.clickOnSauceLabsBackpackAddToCartButton();

        //idem na korpu
        inventoryPage.clickOnShoppingCartButton();
        Assert.assertFalse(cartPage.isCartEmpty(),
                "When user add item to shopping cart then item should be visible on shopping cart");

    }

    private void logIn(String username, String password) {
        logInPage.insertUsername(username);
        logInPage.insertPassword(password);
        logInPage.clickOnLogInButton();
    }

//    @AfterMethod
//    public void tearDown() {
//        driver.quit();
//
//    }


}