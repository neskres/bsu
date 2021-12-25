import org.testng.Assert;
import org.testng.annotations.Test;
import page.ExpediaHomePage;
import util.CommonConditions;

public class ExpediaStaysPageSearchFormTest extends CommonConditions {
    private static final String DESTINATION_EXCEPTION_TEXT = "To continue, please correct the error below.";
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
}
