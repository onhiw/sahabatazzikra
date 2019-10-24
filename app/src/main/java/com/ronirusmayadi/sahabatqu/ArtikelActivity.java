package com.ronirusmayadi.sahabatqu;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ronirusmayadi.sahabatqu.Adapter.MainFragmentPagerAdapter;
import com.ronirusmayadi.sahabatqu.Fragment.ArtikelFragment;
import com.ronirusmayadi.sahabatqu.Fragment.SejarahFragment;
import com.ronirusmayadi.sahabatqu.Fragment.TeknologiFragment;

public class ArtikelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel);

        setTitle("Berita & Media");

        initViews();
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
                    startActivity(new Intent(ArtikelActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
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
        mainFragmentPagerAdapter.addFragment(new ArtikelFragment(), getString(R.string.artikel));
        mainFragmentPagerAdapter.addFragment(new SejarahFragment(), getString(R.string.hajiumrah));
        mainFragmentPagerAdapter.addFragment(new TeknologiFragment(), getString(R.string.teknologi));
        viewPager.setAdapter(mainFragmentPagerAdapter);
    }

}
