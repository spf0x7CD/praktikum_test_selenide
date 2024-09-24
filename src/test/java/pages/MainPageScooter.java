package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class MainPageScooter {
    public static final String MAIN_PAGE_RELATIVE_URL = "/";
    private final SelenideElement headerOrderButton = $x(".//div[contains(@class,'Header_Nav')]/button[text()='Заказать']");
    private final SelenideElement homeOrderButton = $x(".//div[contains(@class,'Home_Finish')]/button[text()='Заказать']");
    private final SelenideElement expandedAnswer = $x(".//div[@class='accordion__panel' and not(@hidden)]");

    public MainPageScooter open(boolean popUpCookieIsDisabled) {
        Selenide.open(MAIN_PAGE_RELATIVE_URL);
        if (popUpCookieIsDisabled) {
            executeJavaScript("arguments[0].style.display = 'none'", $x(".//div[contains(text(), 'И здесь куки!')]/parent::*/parent::*/parent::*"));
        }
        return page(this);
    }

    public void clickHeaderOrderButton() {
        headerOrderButton.click();
    }

    public void clickHomeOrderButton() {
        homeOrderButton.click();
    }

    public MainPageScooter clickQuestionWithText(String text) {
        $x(String.format(".//div[text()='%s']", text))
                .click();
        return page(this);
    }

    public String getTextOfExpandedAnswer() {
        return expandedAnswer.getText();
    }
}
