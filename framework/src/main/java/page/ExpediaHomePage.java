package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpediaHomePage extends AbstractPage {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String HOMEPAGE_URL = "https://www.expedia.com";
    private static final String CARS_PAGE_URL = "https://www.expedia.com/Cars";
    private static final String FLIGHTS_PAGE_URL = "https://www.expedia.com/Flights";

    private By discardAdvertisementLocator = By.xpath("//div[contains(@class,'site-header')]");

    private By destinationExceptionLocator = By.xpath("//h3[@class='uitk-error-summary-heading']");

    private By roomFormLocator = By.xpath("//a[@data-testid='travelers-field']");

    private By addAdultButtonLocator = By.xpath("//button[contains(@class,'step-input')][2]");

    private By roomFormApplyButtonLocator = By.xpath("//button[@data-testid='guests-done-button']");

    private By searchButtonLocator = By.xpath("//button[text()='Search']");

    public ExpediaHomePage(WebDriver driver) {
        super(driver);
    }

    public ExpediaHomePage openHomePage() {
        driver.get(HOMEPAGE_URL);
        LOGGER.log(Level.INFO, "Home page is opened");
        findElementByLocatorAndClick(discardAdvertisementLocator);
        return this;
    }

    public ExpediaStaysResultsPage searchHotels() {
        findElementByLocatorAndClick(searchButtonLocator);
        LOGGER.log(Level.INFO, "Searching...");
        return new ExpediaStaysResultsPage(driver);
    }

    public String getDestinationExceptionText() {
        LOGGER.log(Level.INFO, "Destination exception text is received");
        return findElementByLocatorAndGetText(destinationExceptionLocator);
    }

    public ExpediaHomePage openRoomForm() {
        findElementByLocatorAndClick(roomFormLocator);
        LOGGER.log(Level.INFO,"Room form is opened");
        return this;
    }

    public ExpediaHomePage changeAdultsNumberByMaxValue() {
        try{
            while (null != new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(addAdultButtonLocator))){
                findElementByLocatorAndClick(addAdultButtonLocator);
                LOGGER.log(Level.INFO, "Adding adult...");
            }}
        catch(org.openqa.selenium.TimeoutException ignored) {
        }
        return this;
    }

    public String getNumberOfAdults() {
        LOGGER.log(Level.INFO, "Number of adults is received");
        return findElementByLocatorAndGetText(roomFormLocator).replace("1 room,","")
                                                              .replace("travelers","")
                                                              .trim();
    }

    public ExpediaHomePage clickRoomFormApplyButton() {
        findElementByLocatorAndClick(roomFormApplyButtonLocator);
        LOGGER.log(Level.INFO, "Apply button in the room form is clicked");
        return this;
    }
}
