package com.example.farmerama.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.farmerama.R;
import com.example.farmerama.data.model.User;
import com.example.farmerama.viewmodel.EditAccountViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditAccountFragment extends Fragment {

    private EditAccountViewModel viewModel;
    private ImageView profilePicture;
    private TextView email;
    private TextView firstName;
    private TextView lastName;
    private TextView password;
    private Spinner role;
    private Button save;
    private StorageReference storageRef;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_edit_account, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(EditAccountViewModel.class);

        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        profilePicture = view.findViewById(R.id.userEditPicture);
        progressBar = view.findViewById(R.id.progressbar2);
        email = view.findViewById(R.id.editAccount_email);
        firstName = view.findViewById(R.id.editAccount_firstName);
        lastName = view.findViewById(R.id.editAccount_LastName);
        password = view.findViewById(R.id.editAccount_password);
        role = view.findViewById(R.id.editAccount_role);
        save = view.findViewById(R.id.editAccount_saveButton);
    }

    private void setupViews() {
        email.requestFocus();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapter);

        viewModel.getLoggedInUser().observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                viewModel.setUserId(user.getUserId());
                progressBar.setVisibility(View.VISIBLE);
                storageRef = FirebaseStorage.getInstance().getReference().child("users/"+user.getUserId()+"/profile.jpg");
                storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    Picasso.get().load(uri).into(profilePicture);
                    progressBar.setVisibility(View.INVISIBLE);
                });

                email.setText(user.getEmail());
                String[] tokens = user.getUserName().split(" ");
                firstName.setText("");
                for (int i = 0; i < tokens.length - 1; i++)
                    firstName.setText(firstName.getText().toString() + tokens[i] + " ");
                firstName.setText(firstName.getText().toString().trim());
                lastName.setText(tokens[tokens.length - 1]);
                password.setText(user.getPassword());


                if (user.getRole().equals("ADMINISTRATOR"))
                    role.setSelection(1);
                else
                    role.setSelection(0);
                role.setEnabled(false);
            }
        });
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getData() != null) {
                Uri imageUri = result.getData().getData();
                uploadImageToFirebase(imageUri);
            }
        });
        profilePicture.setOnClickListener(view -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(openGallery);
        });


        save.setOnClickListener(v -> {
            if (viewModel.validate(firstName.getText().toString(), lastName.getText().toString(),
                    email.getText().toString(), password.getText().toString(),
                    role.getSelectedItem().toString())) {
                User user = new User(firstName.getText().toString(), lastName.getText().toString(),
                        email.getText().toString(), password.getText().toString(),
                        role.getSelectedItem().toString());
                user.setUserId(viewModel.getUserId());
                viewModel.saveAccount(user);
            }
        });

    }

    public void uploadImageToFirebase(Uri imageUri) {
        User user = viewModel.getLoggedInUser().getValue();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference fileRef = storageReference.child("users/"+user.getUserId()+"/profile.jpg");
        ProgressDialog pd = new ProgressDialog(this.getContext());
        pd.setMessage("Loading");
        pd.show();

        fileRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
            pd.dismiss();
            fileRef.getDownloadUrl().addOnSuccessListener(uri -> Picasso.get().load(uri).into(profilePicture));

        }).addOnFailureListener(e -> {
            pd.dismiss();
            Toast.makeText(getContext(), "Try again", Toast.LENGTH_SHORT).show();
        }).addOnProgressListener(snapshot -> {
            double progressPercent = (100.00*snapshot.getBytesTransferred()/snapshot.getTotalByteCount());
            pd.setMessage("Percentage: " +(int) progressPercent+ "");
        });
    }
}