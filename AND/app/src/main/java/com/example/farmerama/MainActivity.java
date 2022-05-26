package com.example.farmerama;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farmerama.data.model.LogObj;
import com.example.farmerama.data.util.NotificationWorker;
import com.example.farmerama.data.util.ToastMessage;
import com.example.farmerama.viewmodel.MainActivityViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
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
        return true;
    }


    private void initViews() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationDrawer = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }

    private void setUpViews() {
        NotificationChannel channel = new NotificationChannel("22", "thresholdNotification", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Channel for the notification regarding exceeding thresholds");
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        PeriodicWorkRequest request = new PeriodicWorkRequest.Builder(NotificationWorker.class, 5, TimeUnit.MINUTES).build();
        WorkManager.getInstance(this).enqueue(request);

        ToastMessage.getToastMessage().observe(this, toast -> {
            if (!toast.isEmpty())
                Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
        });

        viewModel.getTodayLogs().observe(this, logs -> {
            for (LogObj log : logs) {
                publishNotification(log);
            }
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
                R.id.registerFragment,
                R.id.accountFragment,
                R.id.editAccountFragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);

        navigationDrawer.setNavigationItemSelectedListener(item -> {
            navController.navigate(item.getItemId());
            drawerLayout.closeDrawers();
            return true;
        });

        for (int i = 0; i < navigationDrawer.getMenu().size(); i++) {
            navigationDrawer.getMenu().getItem(i).setVisible(false);
        }
        navigationDrawer.getMenu().findItem(R.id.loginFragment).setVisible(true);
        navigationDrawer.getMenu().findItem(R.id.latestDataFragment).setVisible(true);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            int id = destination.getId();

            if (id == R.id.loginFragment) {
                toolbar.setVisibility(View.GONE);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                toolbar.setVisibility(View.VISIBLE);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        });
//        usernameHeader = findViewById(R.id.UsernameHeader);
//        emailHeader = findViewById(R.id.EmailHeader);
    }

    private void setUpLoggedInUser() {
        viewModel.getLoggedInUser().observe(this, loggedInUser -> {
            if (loggedInUser != null) {
                Toast.makeText(this, "Logged in user", Toast.LENGTH_SHORT).show();
                viewModel.saveLoggedInUser(loggedInUser);

                TextView usernameHeader = findViewById(R.id.UsernameHeader);
                TextView emailHeader = findViewById(R.id.EmailHeader);
                ImageView profilePicture = findViewById(R.id.imageView);
                if(usernameHeader != null && emailHeader != null)
                {
                    usernameHeader.setText(loggedInUser.getName());
                    emailHeader.setText(loggedInUser.getEmail());
                    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("users/"+loggedInUser.getUserId()+"/profile.jpg");
                    storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.get().load(uri).into(profilePicture);
                        }
                    });
                }


                toolbar.setVisibility(View.VISIBLE);
                for (int i = 0; i < navigationDrawer.getMenu().size(); i++) {
                    navigationDrawer.getMenu().getItem(i).setVisible(true);
                }

                if (loggedInUser.getRole().equals("EMPLOYEE")) {
                    navigationDrawer.getMenu().findItem(R.id.registerFragment).setVisible(false);
                    navigationDrawer.getMenu().findItem(R.id.thresholdModificationFragment).setVisible(false);
                }
                navigationDrawer.getMenu().findItem(R.id.loginFragment).setVisible(false);
                navController.navigate(R.id.latestDataFragment);
            } else {
                for (int i = 0; i < navigationDrawer.getMenu().size(); i++) {
                    navigationDrawer.getMenu().getItem(i).setVisible(false);
                }
                navigationDrawer.getMenu().findItem(R.id.loginFragment).setVisible(true);
                navigationDrawer.getMenu().findItem(R.id.latestDataFragment).setVisible(true);

                viewModel.removeLoggedInUser();
                navController.navigate(R.id.loginFragment);
            }
        });
    }

    private void publishNotification(LogObj log) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "22")
                .setSmallIcon(R.mipmap.application_launcher)
                .setContentTitle("Measurement out of the thresholds")
                .setContentText(String.format("Exceeded %s in area %s", log.getMeasurementType(), log.getAreaName()))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setChannelId("22");

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(22, builder.build());
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
        return NavigationUI.onNavDestinationSelected(item, navController) || super.onOptionsItemSelected(item);
    }
}