package tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.OrderPageScooter;
import pages.values.RentalDays;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPositiveScenarioTest extends BaseTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final int calendarDaysAfterToday;
    private final RentalDays rentalDays;

    public OrderPositiveScenarioTest(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber,
            int calendarDaysAfterToday,
            RentalDays rentalDays
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.calendarDaysAfterToday = calendarDaysAfterToday;
        this.rentalDays = rentalDays;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Алёша", "Попович", "ул. Пушкина, д. Колотушкина", "Лихоборы", "88005553535", 99, RentalDays.SEVEN},
                {"Йеллоу", "Кард", "Оушен Авеню", "Деловой центр", "+79999999999", 2003, RentalDays.ONE},
        };
    }
    @Test
    public void shouldMakeOrder() {
        OrderPageScooter orderPageScooter = new OrderPageScooter()
                .open(true)
                .fillNameField(name)
                .fillSurnameField(surname)
                .fillAddressField(address)
                .selectMetroStation(metroStation)
                .fillPhoneNumberField(phoneNumber)
                .clickNextButton()
                .selectDeliveryDate(calendarDaysAfterToday)
                .selectRentPeriod(rentalDays)
                .clickMakeOrderMiddleButton()
                .clickConfirmOrderWindowYesButton();
        assertTrue(
                "Сообщение об успешном оформлении заказа не появилось.",
                orderPageScooter.orderHasBeenPlaced()
        );
    }
}