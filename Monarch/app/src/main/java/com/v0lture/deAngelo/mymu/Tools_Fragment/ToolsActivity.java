package com.v0lture.deAngelo.mymu.tools_fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.v0lture.deAngelo.mymu.anouncement_folder.AnnounceActivity;
import com.v0lture.deAngelo.mymu.bugs_folder.Bugs;
import com.v0lture.deAngelo.mymu.credentials_folder.SignOut;
import com.v0lture.deAngelo.mymu.home_tabs.NavActivity;
import com.v0lture.deAngelo.mymu.R;
import com.v0lture.deAngelo.mymu.schedule_folder.ScheduleActivity;

/**
 * Created by De'Angelo on 12/20/2016.
 */

public class ToolsActivity extends AppCompatActivity {
    private DrawerLayout nDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        nDrawerLayout = (DrawerLayout)findViewById(R.id.tool_drawer_layout);
        NavigationView nNavigationView = (NavigationView)findViewById(R.id.tool_view);

        FragmentManager nFragmentManager = getSupportFragmentManager();
        FragmentTransaction nFragmentTransaction = nFragmentManager.beginTransaction();
        nFragmentTransaction.replace(R.id.toolContainerView, new ToolTabFragment()).commit();

        nNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem){
                nDrawerLayout.closeDrawers();
                if (menuItem.getItemId() == R.id.home){
                    startActivity(new Intent(ToolsActivity.this, NavActivity.class));
                }
                if (menuItem.getItemId() == R.id.nav_announce) {
                    startActivity(new Intent(ToolsActivity.this, AnnounceActivity.class));
                }
                if (menuItem.getItemId() == R.id.nav_schedule) {
                    startActivity(new Intent(ToolsActivity.this, ScheduleActivity.class));
                }
                if (menuItem.getItemId() == R.id.bugs) {
                    startActivity(new Intent(ToolsActivity.this, Bugs.class));
                }

                if (menuItem.getItemId() == R.id.out) {
                    new AlertDialog.Builder(ToolsActivity.this)
                            .setIcon(android.R.drawable.ic_lock_power_off)
                            .setTitle("Logging Out")
                            .setMessage("Are you sure you want to log out?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    startActivity(new Intent(ToolsActivity.this, SignOut.class));
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                }
                return false;
            }

        });
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_toolbar);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, nDrawerLayout, toolbar, R.string.app_name,
                R.string.app_name);

        nDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }
}
