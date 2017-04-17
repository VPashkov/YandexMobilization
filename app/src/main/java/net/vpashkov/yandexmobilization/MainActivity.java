package net.vpashkov.yandexmobilization;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity
        extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final TranslateFragment translateFragment = new TranslateFragment();

    private final HistoryFragment historyFragment = new HistoryFragment();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigateToTranslateView();

        final BottomNavigationView navigationBar = (BottomNavigationView) findViewById(R.id.navigation_bar);
        navigationBar.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_translate:
                navigateToTranslateView();
                break;
            case R.id.menu_history:
                navigateToHistoryView();
                break;
            case R.id.menu_settings:
                break;
            default:
                final String message = String.format(
                        "Unrecognized menu item %s(%d)", item.getTitle(), item.getItemId());
                throw new IllegalStateException(message);
        }

        return true;
    }

    private void navigateToTranslateView() {
        navigateToFragment(translateFragment);
    }

    private void navigateToHistoryView() {
        navigateToFragment(historyFragment);
    }

    private void navigateToFragment(Fragment fragment) {
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}