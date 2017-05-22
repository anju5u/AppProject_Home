package admin.ats.com.focaloidproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import admin.ats.com.focaloidproject.Activities.MainActivity;
import admin.ats.com.focaloidproject.Activities.MapsActivity;
import admin.ats.com.focaloidproject.Fragments.Menu_Camera;
import admin.ats.com.focaloidproject.Fragments.Menu_Event;
import admin.ats.com.focaloidproject.Fragments.Menu_Home;

//import admin.ats.com.focaloidproject.Fragment.Menu_Camera;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Button cam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


/////   setting logged user name and email id in navigation drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);

        TextView nav_username = (TextView)hView.findViewById(R.id.pflname);
        TextView nav_useremail = (TextView)hView.findViewById(R.id.pflemail);
        SharedPreferences sp=getSharedPreferences("my_pref",MODE_PRIVATE);
        String pflname=sp.getString("name",null);
        String pflemail=sp.getString("email",null);
        nav_username.setText(pflname);
        nav_useremail.setText(pflemail);
        navigationView.setNavigationItemSelectedListener(this);

        // Home page designing code

        //Get menuItem index 0
        if (savedInstanceState == null) {
            MenuItem item =  navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }







    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menu
        displaySelectedScreen(item.getItemId());

        return true;
    }
// code from simplified coding site
    private void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.home1:
                fragment = new Menu_Home();
                break;
            case R.id.camera:
                fragment = new Menu_Camera();
                break;
            case R.id.event:
                fragment = new Menu_Event();
                break;
            case R.id.map:
              //  fragment = new Menu_Map();
                Intent i1=new Intent(Home.this,MapsActivity.class);
                startActivity(i1);
                break;
            case R.id.logout:
                Intent i=new Intent(Home.this,MainActivity.class);
                startActivity(i);

        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
