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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.farmerama.viewmodel.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupNavigation();
        setUpLoggedInUser();
    }

    private void setupNavigation() {
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
        navigationDrawer.getMenu().findItem(R.id.signOut).setOnMenuItemClickListener(item -> {
            viewModel.logOut();
            return true;
        });
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private void setUpLoggedInUser() {
        viewModel.getLoggedInUser().observe(this, loggedInUser -> {
            if (loggedInUser != null) {
                Toast.makeText(this, "Logged in user", Toast.LENGTH_SHORT).show();
                viewModel.saveLoggedInUser(loggedInUser);

                toolbar.setVisibility(View.VISIBLE);
                for (int i = 0; i < navigationDrawer.getMenu().size(); i++) {
                    navigationDrawer.getMenu().getItem(i).setVisible(true);
                }

                if (loggedInUser.getRole().equals("EMPLOYEE")) {
                    navigationDrawer.getMenu().findItem(R.id.registerFragment).setVisible(false);
                }

                navController.navigate(R.id.latestMeasurementFragment);
            } else {
                viewModel.removeLoggedInUser();
                toolbar.setVisibility(View.GONE);
                navController.navigate(R.id.loginFragment);
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