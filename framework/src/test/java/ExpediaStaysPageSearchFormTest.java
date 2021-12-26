import org.testng.Assert;
import org.testng.annotations.Test;
import page.ExpediaHomePage;
import page.ExpediaStaysResultsPage;
import util.CommonConditions;

public class ExpediaStaysPageSearchFormTest extends CommonConditions {
    private static final String DESTINATION_EXCEPTION_TEXT = "Please select a destination";
    private static final String DESTINATION = "Minsk";
    private static final int NUMBER_OF_ADULTS = 4;
    private static final int DEFAULT_NUMBER_OF_ADULTS = 2;
    private static final int MAX_NUMBER_OF_ADULTS = 14;

    @Test
    public void emptyDestinationFieldStaysPageTest() {
        ExpediaHomePage homePage = new ExpediaHomePage(driver);

        homePage.openHomePage()
                .searchHotels();

        final String destinationExceptionText = homePage.getDestinationExceptionText();

        Assert.assertEquals(DESTINATION_EXCEPTION_TEXT, destinationExceptionText);
    }

    @Test
    public void maxNumberOfAdultsStaysPageTest() {
        ExpediaHomePage homePage = new ExpediaHomePage(driver);

        homePage.openHomePage()
                .openRoomForm()
                .changeAdultsNumberByMaxValue()
                .clickRoomFormApplyButton();

        final int maxNumberOfAdults = Integer.parseInt(homePage.getNumberOfAdults());

        Assert.assertEquals(MAX_NUMBER_OF_ADULTS, maxNumberOfAdults);
    }

    @Test
    public void findStaysTest() {
        ExpediaHomePage homePage = new ExpediaHomePage(driver);
        ExpediaStaysResultsPage resultsPage = homePage.openHomePage()
                                                      .enterDestination(DESTINATION)
                                                      .openRoomForm()
                                                      .addAdults(NUMBER_OF_ADULTS)
                                                      .clickRoomFormApplyButton()
                                                      .searchHotels();

        Assert.assertTrue(resultsPage.isInitialized(DESTINATION, DEFAULT_NUMBER_OF_ADULTS + NUMBER_OF_ADULTS));
    }
}
