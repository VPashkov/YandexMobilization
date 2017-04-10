package net.vpashkov.yandexmobilization;

import android.support.design.widget.BottomNavigationView;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class BottomNavigationViewMatcher extends BoundedMatcher<View, BottomNavigationView> {

    private final int menuItemId;

    public BottomNavigationViewMatcher(int menuItemId) {
        super(BottomNavigationView.class);
        this.menuItemId = menuItemId;
    }

    public static Matcher<View> withSelectedItemId(final int menuItemId) {
        return new BottomNavigationViewMatcher(menuItemId);
    }

    @Override
    protected boolean matchesSafely(BottomNavigationView item) {
        return item.getSelectedItemId() == menuItemId;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("selected item id: ");
        description.appendValue(menuItemId);
    }

    @Override
    public void describeMismatch(Object item, Description description) {

        if (item == null) {
            super.describeMismatch(item, description);
            return;
        }

        if (!item.getClass().isAssignableFrom(BottomNavigationView.class)) {
            super.describeMismatch(item, description);
            return;
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) item;
        description.appendText("selected item id: ");
        description.appendValue(bottomNavigationView.getSelectedItemId());
    }
}
