import model.Location;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.ExpediaFlightsPage;
import page.ExpediaHomePage;
import service.LocationsCreator;
import util.CommonConditions;

public class ExpediaFlightsPageSearchFormTest extends CommonConditions {
    private static final String TRIP_TYPE = "Multi-city";
    private static final int MAX_NUMBER_OF_FORMS = 5;

    @Test
    public void swapFromAndToFieldsFlightsPageTest() {
        Location testLocations = LocationsCreator.locationsFromProperty();
        ExpediaHomePage homePage = new ExpediaHomePage(driver);
        ExpediaFlightsPage flightsPage = homePage.openFlightsPage();

        final String[] locations = flightsPage.enterFromLocation(testLocations.getFromLocation())
                                              .enterToLocation(testLocations.getToLocation())
                                              .getFromAndToLocations();

        final String[] changedLocations = flightsPage.swapLocations()
                                                     .getFromAndToLocations();

        Assert.assertTrue(locations[0].equals(changedLocations[1]) && locations[1].equals(changedLocations[0]));
    }

    @Test
    public void sameFromAndToFieldsFlightsPageTest() {
        Location testLocations = LocationsCreator.sameLocationsFromProperty();
        ExpediaHomePage homePage = new ExpediaHomePage(driver);

        ExpediaFlightsPage flightsPage = homePage.openFlightsPage()
                                                 .enterSameLocations(testLocations.getFromLocation());

        flightsPage.searchFlights();

        Assert.assertTrue(flightsPage.isSameLocationsExceptionVisible());
    }

    @Test
    public void maxNumberOfFlightFormsFlightsPageTest() {
        ExpediaHomePage homePage = new ExpediaHomePage(driver);
        ExpediaFlightsPage flightsPage = homePage.openFlightsPage();

        final int maxNumberOfForms = flightsPage.changeTripType(TRIP_TYPE)
                                                .addMaxNumberOfFlightForm()
                                                .getNumberOfFlightForms();

        Assert.assertEquals(maxNumberOfForms, MAX_NUMBER_OF_FORMS);
    }
}
