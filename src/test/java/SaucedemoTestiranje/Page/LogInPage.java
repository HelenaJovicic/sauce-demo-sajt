package SaucedemoTestiranje.Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "user-name")
    public WebElement username;

     @FindBy(id = "password")
     public WebElement password;

     @FindBy(id = "login-button")
     public WebElement logInButton;

    @FindBy(css=".error-message-container.error")
    public WebElement notification;



    public void insertUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }

    public void insertPassword(String password){
        this.password.clear();
        this.password.sendKeys(password);
    }

    public void clickOnLogInButton(){
        logInButton.click();
    }

    public String getNotificationText(){
        return notification.getText();
    }



}
