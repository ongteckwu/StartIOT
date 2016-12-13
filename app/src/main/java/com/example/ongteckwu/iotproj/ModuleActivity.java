package com.example.ongteckwu.iotproj;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ongteckwu.iotproj.components.Server;
import com.example.ongteckwu.iotproj.modules.DataModType;
import com.example.ongteckwu.iotproj.modules.DataModule;
import com.example.ongteckwu.iotproj.modules.DoorModule;
import com.example.ongteckwu.iotproj.modules.ModType;
import com.example.ongteckwu.iotproj.modules.Module;
import com.example.ongteckwu.iotproj.modules.ModuleRVAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

public class ModuleActivity extends AppCompatActivity {

    private enum AppState {
        ENTER_SERVER_NAME_PW, CONNECT_TO_SERVER, NO_MODULES, MODULES_CONNECTED
    }

    private AppState appState;
    private ServerLoginTask mAuthTask = null;

    // UI references.
    private EditText mPasswordView;
    private EditText mServerNameView;
    private DilatingDotsProgressBar mProgressView;

    // List of servers
    ArrayList<Server> servers = new ArrayList<Server>();

    // List of modules
    List<Module> modules = new ArrayList<>();

    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;

    private ImageButton logoutButton;
    private ImageButton settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module);

        // set app state
        appState = AppState.ENTER_SERVER_NAME_PW;

        // initialize private variables
        mPasswordView = (EditText) findViewById(R.id.server_password);
        mServerNameView = (EditText) findViewById(R.id.server_name);
        mProgressView = (DilatingDotsProgressBar) findViewById(R.id.progressDots);

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycle_view_module);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);

        // Temp modules
        modules.add(new DoorModule("122023", R.drawable.door, new ModType("Door")));
        modules.add(new DataModule("123231", R.drawable.analytics, new DataModType.TemperatureModType()));
        modules.add(new DataModule("123231", R.drawable.analytics, new DataModType.TemperatureModType()));

        ModuleRVAdapter adapter = new ModuleRVAdapter(modules, getApplicationContext());
        rv.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference("Server");
        myRef.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot snapshot){
                Log.i("SNAPSHOT", "Snapshot taken");
                servers = new ArrayList<>();
                for ( DataSnapshot child : snapshot.getChildren()){
                    Server s = child.getValue(Server.class);
                    servers.add(s);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError);
            }
        } );

        // Initialize buttons
        logoutButton = (ImageButton) findViewById(R.id.logoutButton);
        settingsButton = (ImageButton) findViewById(R.id.settingsButton);

        mAuth = FirebaseAuth.getInstance();
    }

    public void onClickConnect(View v) {
        // if add module is clicked
        if (appState == AppState.NO_MODULES || appState == AppState.MODULES_CONNECTED) {
            DialogFragment newFragment = new AddModuleDialog();
            newFragment.show(getSupportFragmentManager(), "add module");
            changeToModuleState();
        }
        goNextState();
    }

    private void changeToModuleState() {
        if (modules.isEmpty()) {
            appState = AppState.NO_MODULES;
        } else {
            appState = AppState.MODULES_CONNECTED;
        }
    }

    private void goNextState() {
        switch(appState) {
            case ENTER_SERVER_NAME_PW: {
                if (attemptServerLogin()) {
                    View serverLoginView = findViewById(R.id.server_login_form);
                    serverLoginView.setVisibility(View.GONE);
                    settingsButton.setVisibility(View.INVISIBLE);
                    logoutButton.setVisibility(View.INVISIBLE);
                    appState = AppState.CONNECT_TO_SERVER;
                }
                break;
            }
            case CONNECT_TO_SERVER: {
                mProgressView.hideNow();
                Button connectButton = (Button) findViewById(R.id.connectButton);
                settingsButton.setVisibility(View.VISIBLE);
                logoutButton.setVisibility(View.VISIBLE);
                connectButton.setText("ADD MODULE");
                // change appState to either NO_MODULES if server has no modules connected,
                // or MODULES_CONNECTED if server has modules connected
                changeToModuleState();
                goNextState();
                break;
            }
            case NO_MODULES: {
                View noModView = findViewById(R.id.no_module_text);
                noModView.setVisibility(View.VISIBLE);
                break;
            }
            case MODULES_CONNECTED: {
                View noModView = findViewById(R.id.no_module_text);
                View recycleView = findViewById(R.id.recycle_view_module);

                noModView.setVisibility(View.GONE);
                recycleView.setVisibility(View.VISIBLE);
                break;
            }
        }
    }

    private void goPreviousState() {
        switch(appState) {
            case CONNECT_TO_SERVER: {
                View serverLoginView = findViewById(R.id.server_login_form);
                serverLoginView.setVisibility(View.VISIBLE);
                settingsButton.setVisibility(View.VISIBLE);
                logoutButton.setVisibility(View.VISIBLE);
                appState = AppState.ENTER_SERVER_NAME_PW;
            }
        }
    }
    private boolean attemptServerLogin() {
        if (mAuthTask != null) {
            return false;
        }

        // Reset errors.
        mServerNameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String serverName = mServerNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(serverName)) {
            mServerNameView.setError(getString(R.string.error_field_required));
            focusView = mServerNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
            return false;
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new ServerLoginTask(serverName, password);
            mAuthTask.execute((Void) null);
        }

        return true;
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (show) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                mProgressView.showNow();
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                mProgressView.showNow();
            }
        } else {
            mProgressView.hideNow();
        }
    }
    /**
     * Logs the user out
     * @param v
     */
    public void onClickLogout(View v) {
        mAuth.signOut();
        Intent loginMenuIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginMenuIntent);
    }

    /**
     * Prevents going back to previous page (most likely the login page)
     */
    @Override
    public void onBackPressed() {
    }

    public class ServerLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mServerName;
        private final String mPassword;
        private Server s;

        ServerLoginTask(String serverName, String password) {
            mServerName = serverName;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

//            try {
//                Thread.sleep(1);
//            } catch (Exception e) {
//
//            }
            //TODO: login to server

            Log.i("SERVERS", servers.toString());
            for (int i = 0; i<servers.size(); i++){
                s = servers.get(i);
                if(mServerName.equals(s.getName()) && mPassword.equals(s.getPassword())) {
                    DatabaseReference myRef = firebaseDatabase.getReference("User");
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    myRef.setValue(user.getProviderId(), s.getName());
                    return true;
                }
            }
            return false;
        }

        @Override
        // change this to
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                goNextState();
            } else {
                goPreviousState();
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
