package page;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExpediaFlightsPage extends AbstractPage{
    private static final Logger LOGGER = LogManager.getRootLogger();

    private By addFlightButtonLocator = By.xpath("//button[text()='Add another flight']");

    private By flightFormLegendLocator = By.xpath("//legend");

    private By fromLocationButtonLocator = By.xpath("//button[@aria-label='Leaving from']");

    private By fromLocationInputLocator = By.xpath("//input[@id='location-field-leg1-origin']");

    private By toLocationButtonLocator = By.xpath("//button[@aria-label='Going to']");

    private By toLocationInputLocator = By.xpath("//input[@id='location-field-leg1-destination']");

    private By swapButtonLocator = By.xpath("//button[@aria-label='Swap origin and destination']");

    private By sameLocationsExceptionLocator = By.xpath("//div[contains(text(),'different destination from origin')]");

    private By searchButtonLocator = By.xpath("//button[text()='Search']");

    protected ExpediaFlightsPage(WebDriver driver) {
        super(driver);
    }

    public ExpediaFlightsPage changeTripType(String tripType) {
        findElementByLocatorAndClick(By.xpath("//span[text()='" + tripType + "']"));
        LOGGER.log(Level.INFO, "Trip type is changed by [{}]", tripType);
        return this;
    }

    public ExpediaFlightsPage addMaxNumberOfFlightForm() {
        try{
            while (null != new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.elementToBeClickable(addFlightButtonLocator))){
                findElementByLocatorAndClick(addFlightButtonLocator);
                LOGGER.log(Level.INFO, "Adding flight form...");
            }}
        catch(org.openqa.selenium.TimeoutException ignored) {
        }
        return this;
    }

    public int getNumberOfFlightForms() {
        final int flightForms = driver.findElements(flightFormLegendLocator).size();
        LOGGER.log(Level.INFO, "Number of flight form equals [{}]",flightForms);
        return flightForms;
    }

    public ExpediaFlightsPage enterFromLocation(String fromLocation) {
        findElementByLocatorAndClick(fromLocationButtonLocator);
        findElementByLocatorAndClick(fromLocationInputLocator).sendKeys(fromLocation + Keys.ENTER);
        LOGGER.log(Level.INFO, "From location [{}] is entered", fromLocation);
        return this;
    }

    public ExpediaFlightsPage enterToLocation(String toLocation) {
        findElementByLocatorAndClick(toLocationButtonLocator);
        findElementByLocatorAndClick(toLocationInputLocator).sendKeys(toLocation + Keys.ENTER);
        LOGGER.log(Level.INFO, "To location [{}] is entered", toLocation);
        return this;
    }

    public String[] getFromAndToLocations() {
        final String fromLocation = findElementByLocator(fromLocationInputLocator).getAttribute("value");
        final String toLocation = findElementByLocator(toLocationInputLocator).getAttribute("value");
        LOGGER.log(Level.INFO, "From location [{}] and to location [{}] are received", fromLocation, toLocation);
        return new String[]{fromLocation, toLocation};
    }

    public ExpediaFlightsPage swapLocations() {
        findElementByLocatorAndClick(swapButtonLocator);
        LOGGER.log(Level.INFO, "From location and to location are swapped");
        return this;
    }

    public ExpediaFlightsPage enterSameLocations(String sameLocation) {
        enterFromLocation(sameLocation);
        enterToLocation(sameLocation);
        return this;
    }

    public ExpediaFlightsResultsPage searchFlights() {
        LOGGER.log(Level.INFO, "Searching...");
        findElementByLocatorAndClick(searchButtonLocator);
        return new ExpediaFlightsResultsPage(driver);
    }

    public boolean isSameLocationsExceptionVisible() {
        boolean isVisible = findElementByLocator(sameLocationsExceptionLocator).isDisplayed();
        if (isVisible) {
            LOGGER.log(Level.INFO, "Same locations exception is visible");
        } else {
            LOGGER.log(Level.ERROR, "Same locations exception is not visible");
        }
        return isVisible;
    }
}
