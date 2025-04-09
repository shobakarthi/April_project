import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStepdefs {
    WebDriver driver;
    WebDriverWait wait;
    String email = "sarah" + System.currentTimeMillis() + "@gmail.com";

    @Given("I am on user registration page")
    public void iAmOnUserRegistrationPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I enter valid user details")
    public void iEnterValidUserDetails() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("14/12/1988");
        driver.findElement(By.id("member_firstname")).click(); // so dob pop-up will close
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_firstname")));
        firstNameInput.sendKeys("Shoba");
        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_lastname")));
        lastNameInput.sendKeys("Karthikeyan");
        WebElement emailAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_emailaddress")));
        emailAddress.sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
    }

    @And("I enter matching passwords")
    public void iEnterMatchingPasswords() {
        WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("signupunlicenced_password")));
        pwd.sendKeys("tesla123");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("tesla123");
    }

    @And("I accept the terms and conditions")
    public void iAcceptTheTermsAndConditions() {
        WebElement firstCheckBox = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("#signup_form > div:nth-child(11) > div > div > div:nth-child(3) > div > label")));
        firstCheckBox.click();

        WebElement secondCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > label > span.box")));
        secondCheckbox.click();

        WebElement thirdCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div.md-checkbox.margin-top-10 > label > span.box")));
        thirdCheckbox.click();

        WebElement fourthCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(4) > label > span.box")));
        fourthCheckbox.click();

        WebElement fifthCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(7) > label > span.box")));
        fifthCheckbox.click();

    }

    @Then("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By
                .cssSelector("#signup_form > div.form-actions.noborder > input")));
        submitButton.click();

    }

    @When("I enter details without a last name")
    public void iEnterDetailsWithoutALastName() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("14/12/1988");
        driver.findElement(By.id("member_firstname")).click(); // so dob pop-up will close
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_firstname")));
        firstNameInput.sendKeys("Shoba");
//        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_lastname")));
//        lastNameInput.sendKeys("Karthikeyan");
        WebElement emailAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_emailaddress")));
        emailAddress.sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        iAcceptTheTermsAndConditions();
    }

    @Then("I should see an error message for the missing last name")
    public void iShouldSeeAnErrorMessageForTheMissingLastName() {
//        WebElement error = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > " +
//                "div > div:nth-child(2) > div:nth-child(1) > span > span"));
        //<span for="member_lastname" generated="true" class="">Last Name is required</span>
        WebElement error = driver.findElement(By.cssSelector("#signup_form > div:nth-child(6) > div:nth-child(2) > div > span > span"));

        assertTrue(error.isDisplayed(), "Last name is required");

    }


    @Then("I should see an error message for the mismatching passwords")
    public void iShouldSeeAnErrorMessageForTheMismatchingPasswords() {
        WebElement pwd = wait.until(ExpectedConditions.elementToBeClickable(By.id("signupunlicenced_password")));
        pwd.sendKeys("tesla123");
        driver.findElement(By.id("signupunlicenced_confirmpassword")).sendKeys("tesla");
//#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span

        WebElement pwdMissmatch = driver.findElement(By.cssSelector("#signup_form > div:nth-child(9) > div > div.row > div:nth-child(2) > div > span > span"));
        assertTrue(pwdMissmatch.isDisplayed(), "Password did not match\n");

    }

    @When("I enter details by giving different passwords")
    public void iEnterDetailsByGivingDifferentPasswords() {
        driver.findElement(By.cssSelector("#dp")).sendKeys("14/12/1988");
        driver.findElement(By.id("member_firstname")).click(); // so dob pop-up will close
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_firstname")));
        firstNameInput.sendKeys("Shoba");
        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_lastname")));
          lastNameInput.sendKeys("Karthikeyan");
        WebElement emailAddress = wait.until(ExpectedConditions.elementToBeClickable(By.id("member_emailaddress")));
        emailAddress.sendKeys(email);
        driver.findElement(By.id("member_confirmemailaddress")).sendKeys(email);
        iAcceptTheTermsAndConditions();
    }

    @And("I do not accept the terms and conditions")
    public void iDoNotAcceptTheTermsAndConditions() {


    }

    @Then("I should see an error message that the terms and conditions are not approved")
    public void iShouldSeeAnErrorMessageThatTheTermsAndConditionsAreNotApproved() {
        //#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span > span
        iSubmitTheRegistrationForm();
        WebElement conditionsNotApproved = driver.findElement(By.cssSelector("#signup_form > div:nth-child(12) > div > div:nth-child(2) > div:nth-child(1) > span > span\n"));
        assertTrue(conditionsNotApproved.isDisplayed(), " You must confirm that you have read and accepted our Terms and Conditions");

    }



    @And("I should see a success message")
    public void iShouldSeeASuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement membershipNumber = driver.findElement(
                By.xpath("//h5[contains(text(),'Your Basketball England Membership Number')]/following-sibling::h2")
        );
        String number = membershipNumber.getText();
        System.out.println("Account created and the membership number is :" + number);
    }
}
