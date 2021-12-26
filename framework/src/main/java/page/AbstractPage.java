package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {
	protected WebDriver driver;
	protected final int WAIT_TIMEOUT_SECONDS = 15;

	protected AbstractPage(WebDriver driver)
	{
		this.driver = driver;
	}

	protected WebElement findElementByLocator(By locator) {
		return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions
						.presenceOfElementLocated(locator));
	}

	protected WebElement findElementByLocatorAndClick(By locator) {
		WebElement element = findElementByLocator(locator);
		element.click();
		return element;
	}

	protected String findElementByLocatorAndGetText(By locator) {
		return findElementByLocator(locator).getText();
	}
}
