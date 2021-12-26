package page;

import org.openqa.selenium.WebDriver;

public class ExpediaStaysResultsPage extends AbstractPage {
    protected ExpediaStaysResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized(String location, int adults) {
        return driver.getCurrentUrl().contains(location) &&
               driver.getCurrentUrl().contains("adults=" + adults);
    }
}
