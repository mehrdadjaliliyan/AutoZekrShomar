package jsm.autozekrcounter.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import jsm.autozekrcounter.R;
import jsm.autozekrcounter.model.RequestZekrPackage;
import jsm.autozekrcounter.model.ZekrModel;
import jsm.autozekrcounter.view.fragment.CounterFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        showfragmentSalavat();

        Intent intent = new Intent(MainActivity.this, SpeechToTextActivity.class);
        startActivity(intent);

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
        getMenuInflater().inflate(R.menu.main_activity_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_salavat) {
            showfragmentSalavat();
        } else if (id == R.id.nav_Tasbihat_H_Z) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.alaho_akbar), 34);
            ZekrModel zekr2 = new ZekrModel(getString(R.string.al_hamd_), 33);
            ZekrModel zekr3 = new ZekrModel(getString(R.string.sob_han_alah), 33);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            zekrPackage.addZekr(zekr2);
            zekrPackage.addZekr(zekr3);
            startFragment(zekrPackage, R.id.nav_Tasbihat_H_Z);
        } else if (id == R.id.nav_Saturday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Saturday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Saturday);
        } else if (id == R.id.nav_Sunday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Sunday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Sunday);
        } else if (id == R.id.nav_Monday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Monday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Monday);
        } else if (id == R.id.nav_Tuesday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Tuesday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Tuesday);
        } else if (id == R.id.nav_Wednesday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Wednesday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Wednesday);
        } else if (id == R.id.nav_Thursday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Thursday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Thursday);
        } else if (id == R.id.nav_Friday) {
            ZekrModel zekr1 = new ZekrModel(getString(R.string.zekr_Friday), 100);
            RequestZekrPackage zekrPackage = new RequestZekrPackage();
            zekrPackage.addZekr(zekr1);
            startFragment(zekrPackage, R.id.nav_Friday);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //------
    private void startFragment(RequestZekrPackage requestZekrPackage, int id) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CounterFragment.ARG_PARAM1, requestZekrPackage);
        bundle.putInt(CounterFragment.ARG_PARAM1_ID, id);
        CounterFragment counterFragment = new CounterFragment();
        counterFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main_activity_, counterFragment).commit();
    }

    private void showfragmentSalavat() {
        ZekrModel zekr1 = new ZekrModel(getString(R.string.salavat), ZekrModel.END_LESS);
        RequestZekrPackage zekrPackage = new RequestZekrPackage();
        zekrPackage.addZekr(zekr1);
        startFragment(zekrPackage, R.id.nav_salavat);
    }

}
