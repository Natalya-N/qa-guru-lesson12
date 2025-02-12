package pages.components;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {

    public void checkTableResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(partialText(value));

    }

}
