package com.example.paynav;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    ViewPager viewPager;
    LinearLayout sliderDotsPanel;
    private int dotsCount;
    private ImageView[] dots;
    private ImageView notifyIcon, redDot, barScanner;
    private RelativeLayout harsh, hearty, transaction, split;
    private ImageButton home, dues, requests, profile;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        InitializeFields();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPager.setAdapter(viewPagerAdapter);

        dotsCount = viewPagerAdapter.getCount();
        dots = new ImageView[dotsCount];

        for(int i = 0; i < dotsCount; i++){

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotsPanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotsCount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nonactive_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 3000, 5000);

        barScanner.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if ( checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED )
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(intent);
                }
                else
                {
                    String[] request = {Manifest.permission.CAMERA};
                    requestPermissions(request, 69);
                }
            }
        });

        notifyIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You've new notifications!", Toast.LENGTH_LONG).show();
            }
        });

        harsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent harshIntent = new Intent(MainActivity.this, PaymentActivity.class);
                harshIntent.putExtra("details", "Amount of 4200 is to be paid to Harshada Nikam");
                startActivity(harshIntent);
            }
        });

        hearty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent heartyIntent = new Intent(MainActivity.this, PaymentActivity.class);
                heartyIntent.putExtra("details", "Amount of 200 is to be paid to Hearty the Hero");
                startActivity(heartyIntent);
            }
        });

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transIntent = new Intent(MainActivity.this, MakeTransactionActivity.class);
                startActivity(transIntent);
            }
        });

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent splitIntent = new Intent(MainActivity.this, SplitBillActivity.class);
                startActivity(splitIntent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You're already in the Home Menu...", Toast.LENGTH_LONG).show();
            }
        });

        dues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent duesIntent = new Intent(MainActivity.this, DuesActivity.class);
                startActivity(duesIntent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(addIntent);
            }
        });

        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent requestIntent = new Intent(MainActivity.this, RequestsActivity.class);
                startActivity(requestIntent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profIntent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(profIntent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 69)
        {
            if(grantResults[0]  == PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this, "Camera Permission not granted...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void InitializeFields()
    {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        sliderDotsPanel = (LinearLayout) findViewById(R.id.SliderDots);
        notifyIcon = (ImageView) findViewById(R.id.notifications);
        redDot = (ImageView) findViewById(R.id.red_circle);
        barScanner = (ImageView) findViewById(R.id.bar_scanner);
        notifyIcon = (ImageView) findViewById(R.id.notifications);
        harsh = (RelativeLayout) findViewById(R.id.harshada_layout);
        hearty = (RelativeLayout) findViewById(R.id.hearty_layout);
        transaction = (RelativeLayout) findViewById(R.id.transaction_layout);
        split = (RelativeLayout) findViewById(R.id.bill_layout);
        home = (ImageButton) findViewById(R.id.home_button);
        dues = (ImageButton) findViewById(R.id.dues_button);
        requests = (ImageButton) findViewById(R.id.requests_button);
        profile = (ImageButton) findViewById(R.id.profile_button);
        add = (FloatingActionButton) findViewById(R.id.add_button);

        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.OVAL);
        gd.setColor(Color.rgb(248, 79,49));
        gd.setCornerRadius(3);
        redDot.setImageDrawable(gd);
    }


    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if(viewPager.getCurrentItem() == 0){
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem() == 1){
                        viewPager.setCurrentItem(2);
                    }else if(viewPager.getCurrentItem() == 2){
                        viewPager.setCurrentItem(3);
                    }
                    else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }
}