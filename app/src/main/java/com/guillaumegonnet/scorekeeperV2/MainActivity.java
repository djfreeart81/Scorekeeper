package com.guillaumegonnet.scorekeeperV2;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.guillaumegonnet.scorekeeperV2.ui.scores.EndMatchDialogFragment;
import com.guillaumegonnet.scorekeeperV2.ui.selectgame.SelectGameFragment;

public class MainActivity extends AppCompatActivity implements EndMatchDialogFragment.EndMatchDialogListener {

    private AppBarConfiguration mAppBarConfiguration;

    public ListenFromActivity activityListener;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //Interface to send back the result of EndMatchDialogFragment to ScoresFragment
    boolean team1Win = false;
    boolean team2Win = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FAB setup
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create a new game", Snackbar.LENGTH_LONG)
                        .setAction("Create", null).show();
                Fragment selectGameFragment = new SelectGameFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment, selectGameFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Drawer setup
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_scores, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void setActivityListener(ListenFromActivity activityListener) {
        this.activityListener = activityListener;
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        if (null != activityListener) {
            activityListener.startNewMatch();
        }
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        if (null != activityListener) {
            activityListener.goHome();
        }
    }

    public interface ListenFromActivity {
        void startNewMatch();

        void goHome();
    }
}
