package com.generalmobi.smart.helmet.ui;

import android.accounts.OperationCanceledException;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.generalmobi.smart.helmet.BootstrapApplication;
import com.generalmobi.smart.helmet.BootstrapServiceProvider;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;


/**

 */
public class MainActivity extends BootstrapActivity {

    @Inject
    BootstrapServiceProvider serviceProvider;

    private boolean userHasAuthenticated = false;
    private static final String FORCE_REFRESH = "forceRefresh";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence drawerTitle;
    private CharSequence title;
     final Context ctx = this;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

        super.onCreate(savedInstanceState);
        BootstrapApplication.component().inject(this);



        // View injection with Butterknife
        ButterKnife.bind(this);






    }


    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    private void initScreen() {


    }





}
