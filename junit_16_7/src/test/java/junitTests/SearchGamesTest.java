package junitTests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SearchGamesTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        open("https://store.steampowered.com");
    }

    @CsvSource({
            "detroit, Detroit: Become Human",
            "cs, Counter-Strike: Global Offensive"
    })
    @ParameterizedTest(name = "Проверка поиска игры {0}, результатом будет найденная игра {1}")
    @Tags({@Tag("BLOCKER"), @Tag("FEATURE")})
    void searchGamesTest(String searchGame, String expectedSearch) {
        $("#store_nav_search_term").setValue(searchGame).pressEnter();
        $("#search_resultsRows").shouldHave(text(expectedSearch));
    }
}
