package com.example.farmerama;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.farmerama.data.model.UserRole;
import com.example.farmerama.data.util.NotificationWorker;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.viewmodel.MainActivityViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationDrawer;
    private MainActivityViewModel viewModel;
    private TextView usernameHeader;
    private TextView emailHeader;
    private ImageView profilePicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setUpViews();
        setupNavigation();
        setUpLoggedInUser();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return viewModel.isLogged();
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        View header = navigationDrawer.getHeaderView(0);
        usernameHeader = header.findViewById(R.id.usernameHeader);
        emailHeader = header.findViewById(R.id.emailHeader);
        profilePicture = header.findViewById(R.id.profilePicture_imageView);
    }

    private void setUpViews() {
        viewModel.retrieveAreas();
        viewModel.retrieveBarns();

        ToastMessage.getToastMessage().observe(this, toast -> {
            if (!toast.isEmpty())
                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        });
    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.latestDataFragment,
                R.id.loginFragment,
                R.id.historicalDataFragment,
                R.id.areasFragment,
                R.id.employeesFragment,
                R.id.logsFragment,
                R.id.thresholdDataFragment,
                R.id.thresholdModificationFragment,
                R.id.registerFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);

        navigationDrawer.setNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            drawerLayout.closeDrawers();
            return true;
        });

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();

            if (id == R.id.loginFragment || id == R.id.introVPFragment) {
                toolbar.setVisibility(View.GONE);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                toolbar.setVisibility(View.VISIBLE);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
    }

    private void setUpLoggedInUser() {
        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(NotificationWorker.class, 15, TimeUnit.MINUTES).build();

        viewModel.getLoggedInUser().observe(this, loggedInUser -> {
            if (loggedInUser != null) {
                Toast.makeText(this, "Logged in user", Toast.LENGTH_SHORT).show();
                viewModel.saveLoggedInUser(loggedInUser);

                if (viewModel.isGettingNotifications())
                    WorkManager.getInstance(this).enqueueUniquePeriodicWork("notification", ExistingPeriodicWorkPolicy.KEEP, request);

                usernameHeader.setText(loggedInUser.getUserName());
                emailHeader.setText(loggedInUser.getEmail());
                profilePicture.setVisibility(View.VISIBLE);
                StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("users/" + loggedInUser.getUserId() + "/profile.jpg");
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profilePicture));
                toolbar.setVisibility(View.VISIBLE);

                for (int i=0; i<navigationDrawer.getMenu().size();i++) {
                    for (int j=0; j< navigationDrawer.getMenu().getItem(i).getSubMenu().size(); j++) {
                        navigationDrawer.getMenu().getItem(i).getSubMenu().getItem(j).setVisible(true);
                    }
                }

                if (loggedInUser.getRole().equals(UserRole.EMPLOYEE)) {
                    navigationDrawer.getMenu().findItem(R.id.registerFragment).setVisible(false);
                    navigationDrawer.getMenu().findItem(R.id.thresholdModificationFragment).setVisible(false);
                    navigationDrawer.getMenu().findItem(R.id.employeesFragment).setVisible(false);
                }
                if (loggedInUser.getRole().equals(UserRole.OFFLINE)) {

                }
                navigationDrawer.getMenu().findItem(R.id.loginFragment).setVisible(false);

                if (!viewModel.isLogged()) {
                    navController.navigate(R.id.latestDataFragment);
                }

                viewModel.setLogged(true);
            } else {
                WorkManager.getInstance(this).cancelAllWork();

                profilePicture.setVisibility(View.INVISIBLE);
                usernameHeader.setText("Guest");
                emailHeader.setText("Email");

                for (int i=0; i<navigationDrawer.getMenu().size();i++) {
                    for (int j=0; j< navigationDrawer.getMenu().getItem(i).getSubMenu().size(); j++) {
                        navigationDrawer.getMenu().getItem(i).getSubMenu().getItem(j).setVisible(false);
                    }
                }
                navigationDrawer.getMenu().findItem(R.id.latestDataFragment).setVisible(true);
                navigationDrawer.getMenu().findItem(R.id.loginFragment).setVisible(true);

                WorkManager.getInstance(this).cancelAllWork();
                viewModel.setLogged(false);
                navController.navigate(R.id.loginFragment);
            }
            invalidateOptionsMenu();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signOut) {
            viewModel.logOut();
            return true;
        }

        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        if (item.getItemId() == R.id.accountFragment) {
            navController.navigate(item.getItemId());
            return true;
        }

        return super.onSupportNavigateUp();
    }
}