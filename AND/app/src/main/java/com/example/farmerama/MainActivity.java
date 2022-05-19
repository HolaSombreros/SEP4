package com.example.farmerama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.farmerama.domainlayer.LoginViewModel;
import com.example.farmerama.domainlayer.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private SharedPreferences sharedPreferences;
    private MainActivityViewModel activityViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();
        /*Menu navMenu = navigationView.getMenu();
        if(sharedPreferences.getBoolean("GuestVisit", false))
            navMenu.findItem(R.id.accountFragment).setVisible(false);*/

    }

    private void setupNavigation() {
        sharedPreferences = getSharedPreferences("GuestVisit", Context.MODE_PRIVATE);
        navController = Navigation.findNavController(this, R.id.fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.latestMeasurementFragment,
                R.id.accountFragment,
                R.id.historicalMeasurements,
                R.id.areasFragment,
                R.id.employeesFragment,
                R.id.sensorsFragment,
                R.id.employeeAreasFragment,
                R.id.registerFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
//        navigationDrawer.setNavigationItemSelectedListener(item -> {
//            Bundle bundle = new Bundle();
//            if (item.getItemId() == R.id.historicalMeasurements) {
//                bundle.putString("measurementsType", "historical");
//            }
//            if (item.getItemId() == R.id.latestMeasurementFragment) {
//                bundle.putString("measurementsType", "latest");
//            }
//
//            navController.navigate(item.getItemId(), bundle);
//            drawerLayout.closeDrawers();
//            return true;
//        });

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();

            if (id == R.id.loginFragment) {
                toolbar.setVisibility(View.GONE);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
            else {
                toolbar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        activityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        sharedPreferences = getSharedPreferences("GuestVisit", Context.MODE_PRIVATE);
        activityViewModel.getLoggedUser().observe(this, loggedUser -> {
            if(loggedUser) {
                Toast.makeText(this, "Logged in user",Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("measurementsType", "latest");
                //sharedPreferences.edit().putBoolean("GuestVisit", true);
                navController.navigate(R.id.latestMeasurementFragment, bundle);
            }
            else {
                //TODO: LOG OUT
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);

    }
}