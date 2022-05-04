package com.example.farmerama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
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

import com.example.farmerama.domainlayer.LoginViewModel;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private NavigationView navigationView;
    private LoginViewModel loginViewModel;
    private SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_drawer);
        initViews();
        setupNavigation();
        Menu navMenu = navigationView.getMenu();
        if(sharedPreferences.getBoolean("GuestVisit", false))
            navMenu.findItem(R.id.accountFragment).setVisible(false);

    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.latestMeasurementFragment,
                R.id.accountFragment,
                R.id.areasFragment,
                R.id.employeesFragment,
                R.id.sensorsFragment,
                R.id.employeeAreasFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);
        sharedPreferences = getSharedPreferences("GuestVisit", Context.MODE_PRIVATE);
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