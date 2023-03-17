package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class MobileTests extends TestBase {
    @Test
    @Tag("android")
    @DisplayName("Search test")
    void searchInputTest() {
        step("Type content into Search field", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Hotdog");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0))
        );
    }

    @Test
    @Tag("android")
    @DisplayName("Check error when article is open")
    void openArticleWithError() {
        step("Go to search input and type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Hotdog");
        });
        step("Go to article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title")).first().click()
        );
        step("Verify article opens with error", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(exactText("An error occurred"))
        );
    }

    @Tag("ios")
    @DisplayName("iOS test")
    @Test
    void outputTextTest() {
        step("iOS test", () -> {
            $(id("Text Button")).click();
            $(id("Text Input")).sendKeys("hello@browserstack.com");
            $(id("Text Input")).pressEnter();
            $(id("Text Output")).shouldHave(text("hello@browserstack.com"));
        });
    }
}
