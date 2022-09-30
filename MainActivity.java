package com.nogravity.internship1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Debug;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MeowBottomNavigation bottomNavigation;
    ViewPager pager;
    List<Integer> list;
    Timer timer;
    Handler handler;
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Objects.requireNonNull(getSupportActionBar()).hide();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        pager = findViewById(R.id.viewPager);
        gridLayout = findViewById(R.id.grid);
        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.home_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.user_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.logo_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.list_icon));
        bottomNavigation.add(new MeowBottomNavigation.Model(5,R.drawable.chat_icon));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment f = null;

                switch (item.getId()){

                    case 1:

                        pager.setVisibility(View.VISIBLE);
                        gridLayout.setVisibility(View.VISIBLE);
                        f = new Home();
                        break;

                    case 2:

                        pager.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);

                        f = new profile();
                        break;

                    case 3:

                        pager.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);


                        f = new article();
                        break;

                    case 4:
                        pager.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);



                        f = new follow();
                        break;

                    case 5:
                        pager.setVisibility(View.INVISIBLE);
                        gridLayout.setVisibility(View.INVISIBLE);



                        f = new chat();
                        break;
                }

                loadFragment(f);

            }

        });

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(1,true);




        list = new ArrayList<>();

        list.add(R.drawable.one_poster);
        list.add(R.drawable.two_poster);
        list.add(R.drawable.three_poster);


        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(list);
        int i = viewPagerAdapter.getCount();

        pager.setAdapter(viewPagerAdapter);

        handler  = new Handler();
        timer  = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i = pager.getCurrentItem();
                        if(i==list.size()-1){
                            i = 0;
                            pager.setCurrentItem(i,true);

                        }else{
                            i++;
                            pager.setCurrentItem(i,true);

                        }

                    }
                });
            }
        },2000,4000);





    }

    private void loadFragment(Fragment f) {

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.frame_layout,f).
                commit();
    }
}