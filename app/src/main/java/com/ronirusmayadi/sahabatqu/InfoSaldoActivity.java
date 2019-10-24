package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ronirusmayadi.sahabatqu.Adapter.MainFragmentPagerAdapter;
import com.ronirusmayadi.sahabatqu.Fragment.AkunFragment;
import com.ronirusmayadi.sahabatqu.Fragment.ArtikelFragment;
import com.ronirusmayadi.sahabatqu.Fragment.HistoryFragment;
import com.ronirusmayadi.sahabatqu.Fragment.SejarahFragment;
import com.ronirusmayadi.sahabatqu.Fragment.TeknologiFragment;

public class InfoSaldoActivity extends AppCompatActivity {

    private FirebaseUser fuser;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_saldo);

        setTitle("Akun Saya");
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (fuser != null) {

        } else {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        initViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_saldo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_profil:
                Intent intent = new Intent(InfoSaldoActivity.this, EditProfilActivity.class);
                startActivity(intent);
                break;

            case R.id.info:
                Intent i = new Intent(InfoSaldoActivity.this, InfoProgramActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }

    private void initViews() {
        // setting toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(InfoSaldoActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    overridePendingTransition(R.anim.slide_up, R.anim.slide_down);
                }
            });
        }

        // setting view pager
        ViewPager viewPager = findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        // setting tabLayout
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        MainFragmentPagerAdapter mainFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
        mainFragmentPagerAdapter.addFragment(new AkunFragment(), getString(R.string.akun));
        mainFragmentPagerAdapter.addFragment(new HistoryFragment(), getString(R.string.histori));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }
}
