package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class ExpediaCarsPage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private By searchButtonLocator = By.xpath("//button[text()='Search']");

    private By originExceptionLocator = By.xpath("//div[contains(@class,'message-error')]");

    private By locationButtonLocator = By.xpath("//button[@aria-label='Pick-up']");

    private By locationInputLocator = By.xpath("//input[@id='location-field-locn']");

    protected ExpediaCarsPage(WebDriver driver) {
        super(driver);
    }

    public ExpediaCarsResultsPage searchCars() {
        findElementByLocatorAndClick(searchButtonLocator);
        LOGGER.log(Level.INFO, "Searching...");
        return new ExpediaCarsResultsPage(driver);
    }

    public String getOriginExceptionText() {
        LOGGER.log(Level.INFO, "Origin exception text is received");
        return findElementByLocatorAndGetText(originExceptionLocator);
    }

    public ExpediaCarsPage enterLocation(String location) {
        findElementByLocatorAndClick(locationButtonLocator);
        findElementByLocatorAndClick(locationInputLocator).sendKeys(location + Keys.ENTER);
        LOGGER.log(Level.INFO, "Location [{}] is entered", location);
        return this;
    }
}
