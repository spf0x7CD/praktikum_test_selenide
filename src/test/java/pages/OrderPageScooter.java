package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.values.RentalDays;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.*;

public class OrderPageScooter {
    public static final String ORDER_PAGE_RELATIVE_URL = "/order";
    private final SelenideElement nameField = $x(".//input[@placeholder='* Имя']");
    private final SelenideElement surnameField = $x(".//input[@placeholder='* Фамилия']");
    private final SelenideElement addressField = $x(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final SelenideElement metroStationField = $x(".//input[@placeholder='* Станция метро']");
    private final SelenideElement phoneNumberField = $x(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final SelenideElement nextButtonOrderPage = $x(".//button[text()='Далее']");
    private final SelenideElement deliveryDateField = $x(".//input[@placeholder='* Когда привезти самокат']");
    private final SelenideElement rentPeriodField = $x(".//div[@class='Dropdown-root']");
    private final SelenideElement makeOrderMiddleButton = $x(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    private final SelenideElement confirmOrderWindowYesButton = $x(".//button[text()='Да']");
    private final SelenideElement orderHasBeenPlaced = $x(".//div[text()='Заказ оформлен']");

    public OrderPageScooter open(boolean popUpCookieIsDisabled) {
        Selenide.open(ORDER_PAGE_RELATIVE_URL);
        if (popUpCookieIsDisabled) {
            executeJavaScript("arguments[0].style.display = 'none'", $x(".//div[contains(text(), 'И здесь куки!')]/parent::*/parent::*/parent::*"));
        }
        return page(this);
    }

    public OrderPageScooter fillNameField(String name) {
        nameField.setValue(name);
        return page(this);
    }

    public OrderPageScooter fillSurnameField(String surname) {
        surnameField.setValue(surname);
        return page(this);
    }

    public OrderPageScooter fillAddressField(String address) {
        addressField.setValue(address);
        return page(this);
    }

    public OrderPageScooter selectMetroStation(String metroStation) {
        metroStationField.click();
        $x(String.format("//*[text()='%s']/parent::*", metroStation)).click();
        return page(this);
    }

    public OrderPageScooter fillPhoneNumberField(String phoneNumber) {
        phoneNumberField.setValue(phoneNumber);
        return page(this);
    }

    public OrderPageScooter clickNextButton() {
        nextButtonOrderPage.click();
        return page(this);
    }

    public OrderPageScooter selectDeliveryDate(int daysAfterToday) {
        deliveryDateField.click();
        ZonedDateTime plusDate = new Date()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .plusDays(Math.max(daysAfterToday, 0));
        int requiredYear = plusDate.getYear();
        String requiredMonth = plusDate
                .getMonth()
                .getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
        int requiredDay = plusDate.getDayOfMonth();
        SelenideElement currMonthElem = $x(".//div[@class='react-datepicker__current-month']");
        SelenideElement nextMonthButton = $x(".//button[@aria-label='Next Month']");
        while (!currMonthElem.getText().equals(String.format("%s %d", requiredMonth, requiredYear))) {
            nextMonthButton.click();
        }
        $x(String.format(".//div[@class='react-datepicker__month']/div/div[not(contains(@class, '--outside-month')) and text()=%d]", requiredDay))
                .click();
        return page(this);
    }

    public OrderPageScooter selectRentPeriod(RentalDays days) {
        rentPeriodField.click();
        $$x(".//div[@class='Dropdown-menu']/div")
                .get(days.ordinal())
                .click();
        return page(this);
    }

    public OrderPageScooter clickMakeOrderMiddleButton() {
        makeOrderMiddleButton.click();
        return page(this);
    }

    public OrderPageScooter clickConfirmOrderWindowYesButton() {
        confirmOrderWindowYesButton.click();
        return page(this);
    }

    public boolean orderHasBeenPlaced() {
        return orderHasBeenPlaced.isDisplayed();
    }
}

