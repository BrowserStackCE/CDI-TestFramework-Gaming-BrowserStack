package com.browserstack;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SignIn_PlaceABet extends SeleniumTest {

    WebDriverWait wait;

    @Test
    public void SignInAndPlaceABet() throws InterruptedException {
        System.out.println("[[BSTACK_SET_CUSTOM_TAG||ID=TC-1540653]]");
        driver.get("https://stackbet-io.vercel.app/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // --- Sign In Flow ---
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("[data-testid='header-signin-button']")
        ));
        signInButton.click();

        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("[data-testid='email-input'][aria-label='Email address']")
        ));
        emailInput.sendKeys("demoAccount@email.com");

        WebElement passwordInput = driver.findElement(
            By.cssSelector("[data-testid='password-input'][aria-label='Password']")
        );
        passwordInput.sendKeys("myDemoPassword123");

        WebElement formSignInButton = driver.findElement(
            By.cssSelector("[data-testid='signin-button'][aria-label='Sign in to your account']")
        );
        formSignInButton.click();
        System.out.println("Signed in successfully.");

        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        List<WebElement> redOddsButtons = driver.findElements(
            By.xpath("//button[contains(@class, 'bg-red-600') and contains(., '3.45')]")
        );

        if (!redOddsButtons.isEmpty()) {
            System.out.println("Desktop detected — clicking red odds button (3.45 +).");

            WebElement redOddsButton = wait.until(ExpectedConditions.elementToBeClickable(redOddsButtons.get(0)));
            redOddsButton.click();


        } else {
            // --- Mobile Flow ---
            System.out.println("Mobile detected — clicking 'Real Madrid' button directly.");

            WebElement realMadridButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[text()='Real Madrid']]")
            ));
            realMadridButton.click();
            System.out.println("Clicked on 'Real Madrid' button (Mobile flow).");
        }

        // --- Bet Slip Modal ---
        WebElement betSlipInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[type='number'][placeholder='0.00']")
        ));
        betSlipInput.clear();
        betSlipInput.sendKeys("35");
        System.out.println("Entered bet amount: 35");

        WebElement placeBetButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(., 'Place Bet')]")
        ));
        placeBetButton.click();
        System.out.println("Clicked on 'Place Bet' button.");
    }
}
