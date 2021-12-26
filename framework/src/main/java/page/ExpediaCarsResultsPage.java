package page;

import org.openqa.selenium.WebDriver;

public class ExpediaCarsResultsPage extends AbstractPage{
    protected ExpediaCarsResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isInitialized(String location) {
        return driver.getCurrentUrl().contains(location);
    }
}
