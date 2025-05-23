import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import models.LoginModel;

public class Login_test {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/index.html");
    }
    @AfterMethod
    public void tearDown() {
        // Close the browser after each test
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testvalidLogin(){
        LoginModel loginModel = new LoginModel(driver);
        HomePage homePage = loginModel.InterUsername("7amada")
                .InterPassword("123456")
                .clickLoginButton();
        Assert.assertTrue(homePage.getWelcomeText().contains("7amada"));
    }



    @Test
    public void testInvalidLogin() {
        LoginModel loginModel = new LoginModel(driver);
        String alertText = loginModel.InterUsername("validUser")
                .InterPassword("wrongPassword")
                .clickLoginExpectingAlert();
        Assert.assertTrue(alertText.contains("Wrong password."));

    }
    @Test
    public void testEmptyLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateToLoginModel()
                .InterUsername("7amada")
                .InterPassword("123456")
                .clickLoginButton();
    }

}
