package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStepdefs {
    WebDriver driver;
    String email = "sarah" + System.currentTimeMillis() + "@gmail.com";

    private void waitAndClick(String css){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(css)));
        element.click();
    }

    @Given("I am on user registration page using {string}")
    public void iAmOnUserRegistrationPageUsing(String browser) {
        if(browser.equals("chrome")) driver= new ChromeDriver();
        if(browser.equals("edge")) driver= new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I enter valid user details")
    public void iEnterValidUserDetails() {
        driver.findElement(By.cssSelector(".custom-date")).sendKeys("14/12/1988");
        driver.findElement(By.id("member_firstname")).click(); // so dob pop-up will close
        driver.findElement(By.id("member_firstname")).sendKeys("Shoba");
        driver.findElement(By.id("member_lastname")).sendKeys("Karthikeyan");
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
    }

    @And("I enter matching passwords")
    public void iEnterMatchingPasswords() {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("tesla123");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("tesla123");
    }

    @And("I accept the terms and conditions")
    public void iAcceptTheTermsAndConditions() {
        waitAndClick("label[for='signup_basketballrole_16']");
        waitAndClick("label[for='sign_up_25']");
        waitAndClick("label[for='sign_up_26']");
        waitAndClick("label[for='sign_up_27']");
        waitAndClick("label[for='fanmembersignup_agreetocodeofethicsandconduct']");

    }

    @Then("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        waitAndClick("#signup_form > div.form-actions.noborder > input");

    }

    @And("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        WebElement membershipNumber = driver.findElement(
                By.cssSelector("h2.bold.margin-bottom-40"));
        String number = membershipNumber.getText();
        System.out.println("Account created :" + number);
    }


    @When("I enter details without a last name")
    public void iEnterDetailsWithoutALastName() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("15/08/1988");
        driver.findElement(By.id("member_firstname")).click(); // so dob pop-up will close
        driver.findElement(By.id("member_firstname")).sendKeys("Surya");
        driver.findElement(By.id("member_emailaddress")).sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        iAcceptTheTermsAndConditions();
    }

    @Then("I should see an error message for the missing last name")
    public void iShouldSeeAnErrorMessageForTheMissingLastName() {
        WebElement error = driver.findElement(By.cssSelector("#signup_form > div:nth-child(6) > div:nth-child(2) > div > span > span"));
        assertTrue(error.isDisplayed(), "Last name is required");

    }
    @And("I enter mismatching passwords")
    public void iEnterMismatchingPasswords() {
        driver.findElement(By.id("signupunlicenced_password")).sendKeys("tesla123");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("tesla"); // entering non-matching passwords
        iAcceptTheTermsAndConditions();
    }

    @Then("I should see an error message for the mismatching passwords")
    public void iShouldSeeAnErrorMessageForTheMismatchingPasswords() {

        WebElement pwdMissmatch = driver.findElement(By.cssSelector("#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span"));
        assertTrue(pwdMissmatch.isDisplayed(), "Password did not match\n");

    }

    @And("I do not accept the terms and conditions")
    public void iDoNotAcceptTheTermsAndConditions() {
    // Left empty so that it doesn't accept the terms and conditions.

    }

    @Then("I should see an error message that the terms and conditions are not approved")
    public void iShouldSeeAnErrorMessageThatTheTermsAndConditionsAreNotApproved() {
        waitAndClick("label[for='signup_basketballrole_19']");
        iSubmitTheRegistrationForm();
        WebElement conditionsNotApproved = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span > span\n"));
        assertTrue(conditionsNotApproved.isDisplayed(), " You must confirm that you have read and accepted our Terms and Conditions");

    }
}
