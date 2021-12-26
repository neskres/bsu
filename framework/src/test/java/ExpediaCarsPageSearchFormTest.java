import model.Location;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ExpediaCarsPage;
import page.ExpediaCarsResultsPage;
import page.ExpediaHomePage;
import service.LocationsCreator;
import util.CommonConditions;

public class ExpediaCarsPageSearchFormTest extends CommonConditions {
    private static final String ORIGIN_EXCEPTION_TEXT = "Please select an origin";

    @Test
    public void emptyPickUpFieldCarsPageTest() {
        ExpediaHomePage homePage = new ExpediaHomePage(driver);
        ExpediaCarsPage carsPage = homePage.openCarsPage();

        carsPage.searchCars();

        final String originExceptionText = carsPage.getOriginExceptionText();

        Assert.assertEquals(originExceptionText, ORIGIN_EXCEPTION_TEXT);
    }

    @Test
    public void findCarsTest() {
        Location testLocation = LocationsCreator.locationsFromProperty();
        ExpediaHomePage homePage = new ExpediaHomePage(driver);
        ExpediaCarsResultsPage resultsPage = homePage.openCarsPage()
                                                     .enterLocation(testLocation.getPickupLocation())
                                                     .searchCars();

        Assert.assertTrue(false);
    }
}
