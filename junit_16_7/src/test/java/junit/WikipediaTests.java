package junit;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WikipediaTests {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        open("https://ru.wikipedia.org");
    }

    static Stream<Arguments> chooseLanguageTest() {
        return Stream.of(
                Arguments.of("en", List.of("Main page", "Contents", "Current events", "Random article", "About Wikipedia", "Contact us", "Donate")),
                Arguments.of("it", List.of("Pagina principale", "Ultime modifiche", "Una voce a caso", "Nelle vicinanze", "Vetrina", "Aiuto", "Sportello informazioni"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} в соответствии с локализацией {0}")
    @Tag("BLOCKER")
    void chooseLanguageTest(String locale, List<String> buttons) {
        $("[lang=" + locale + "]").click();
        for (String button : buttons) {
            $("[aria-labelledby=p-navigation-label]").shouldHave(text(button));
        }
    }

    @ValueSource(strings = {"Not logged in", "Talk", "Contributions", "Create account", "Log in"})
    @ParameterizedTest(name = "Проверка наличия кнопки {0} в хэддере")
    @Tag("BLOCKER")
    void checkHeadersElements(String button) {
        $("[lang=en]").click();
        $("[class=vector-menu-content]").shouldHave(text(button));
    }
}
