package com.browserstack;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest extends SeleniumTest {

    WebDriverWait wait;

    private void typeReactInput(By locator, String value) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        el.clear();
        el.sendKeys(value);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            el
        );

        
        Assert.assertEquals(el.getAttribute("value"), value,
                "Input value did not update correctly for " + locator);
    }

    private void clickReactCheckbox(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", el);

        Assert.assertTrue(Boolean.parseBoolean(el.getAttribute("aria-checked")),
                "Checkbox was not checked: " + locator);
    }

    private void clickButton(By locator) {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            btn.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        }
    }


    @Test
    public void SignUpForAnAccount() throws InterruptedException {
        System.out.println("[[BSTACK_SET_CUSTOM_TAG||ID=TC-1539961]]");
        driver.get("https://stackbet-io.vercel.app/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Navigate to Sign Up page
        clickButton(By.xpath("//button[.//span[text()='Sign In']]"));
        clickButton(By.xpath("//button[text()='Sign Up']"));

        String username = "demoUser2430";

        typeReactInput(By.id("username"), username);
        typeReactInput(By.id("email"), "demoAccount@email.com");
        typeReactInput(By.id("password"), "myDemoPassword123");
        typeReactInput(By.id("confirmPassword"), "myDemoPassword123");
        typeReactInput(By.id("country"), "Canada");
        typeReactInput(By.id("dateOfBirth"), "15/03/2001");

        clickReactCheckbox(By.id("termsAndAge"));

        typeReactInput(By.id("idType"), "ID Card");
        typeReactInput(By.id("idNumber"), "27AB1344890");
        typeReactInput(By.id("addressLine1"), "H-08, Green Park");
        typeReactInput(By.id("city"), "Toronto");
        typeReactInput(By.id("postcode"), "483911");


        WebElement createAccountBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()='Create Account']")));
        createAccountBtn.click();


    }
}
