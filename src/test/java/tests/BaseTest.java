package tests;

import com.codeborne.selenide.Configuration;
import org.junit.Before;

abstract public class BaseTest {
    @Before
    public void presetting() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x720";
        Configuration.baseUrl = "https://qa-scooter.praktikum-services.ru";
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
    }
}
