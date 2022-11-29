package junitTests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class SteamLocalizationTests{

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        open("https://store.steampowered.com");
    }

    @DisplayName("Проверка открытия выпадающего селекта с языками")
    @Test
    @Tag("BLOCKER")
    void displaySelectLanguageTest() {
        $("#language_pulldown").click();
        $("#language_dropdown").should(appear);
    }
}
