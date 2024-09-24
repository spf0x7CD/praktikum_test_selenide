package tests;

import org.junit.Test;
import pages.MainPageScooter;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.junit.Assert.assertEquals;
import static pages.OrderPageScooter.ORDER_PAGE_RELATIVE_URL;

public class OrderButtonsTest extends BaseTest {
    @Test
    public void shouldOpenOrderPageViaHeaderOrderButton() {
        new MainPageScooter()
                .open(true)
                .clickHeaderOrderButton();
        String actualFrameUrl = baseUrl + ORDER_PAGE_RELATIVE_URL;
        assertEquals(currentFrameUrl(), actualFrameUrl);
    }
    @Test
    public void shouldOpenOrderPageViaHomeOrderButton() {
        new MainPageScooter()
                .open(true)
                .clickHomeOrderButton();
        String actualFrameUrl = baseUrl + ORDER_PAGE_RELATIVE_URL;
        assertEquals(currentFrameUrl(), actualFrameUrl);
    }

}
