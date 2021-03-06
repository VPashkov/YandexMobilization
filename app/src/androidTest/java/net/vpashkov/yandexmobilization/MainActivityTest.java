package net.vpashkov.yandexmobilization;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static net.vpashkov.yandexmobilization.BottomNavigationViewMatcher.withSelectedItemId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void sourceTextEditorIsVisible() throws Exception {
        onView(withId(R.id.sourceText)).check(matches(isDisplayed()));
    }

    @Test
    public void translatedTextViewIsVisible() throws Exception {
        onView(withId(R.id.translatedText)).check(matches(isDisplayed()));
    }

    @Test
    public void translatedTextContainsTranslationOfSourceText() throws Exception {
        onView(withId(R.id.sourceText)).perform(typeText("Hello world"));
        onView(withId(R.id.translateButton)).perform(click());
        onView(withId(R.id.translatedText)).check(matches(withText("Привет мир")));
    }

    @Test
    public void translateNavigationMenuItemIsSelected() throws Exception {
        onView(withId(R.id.navigation_bar)).check(matches(withSelectedItemId(R.id.menu_translate)));
    }

    @Test
    public void bottomNavigationBarIsVisible() throws Exception {
        onView(withId(R.id.navigation_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void translateNavigationMenuItemIsVisible() throws Exception {
        onView(withId(R.id.menu_translate)).check(matches(isDisplayed()));
    }

    @Test
    public void historyNavigationMenuItemIsVisible() throws Exception {
        onView(withId(R.id.menu_history)).check(matches(isDisplayed()));
    }

    @Test
    public void settingsNavigationMenuItemIsVisible() throws Exception {
        onView(withId(R.id.menu_settings)).check(matches(isDisplayed()));
    }
}
