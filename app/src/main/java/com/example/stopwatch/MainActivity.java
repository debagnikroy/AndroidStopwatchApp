package com.example.stopwatch;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
    static volatile int c=0,c2=0,l=0,i=0,min=0,sec=0;
    static TextView tv,lp;
    static Button btn1,btn2;
    static ScrollView sview;
    static LinearLayout sll;
    static String str="";
    static ScrollView sv;
    static int lapCount=1;
    static int count(int n)
    {
        String sss=n+"";
        return sss.length();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.t1);
        tv.setText("min: "+"00"+" sec: "+"00"+" ms: "+"000");
        lp=(TextView) findViewById(R.id.lapTV);
        lp.setText("");
        btn1=(Button) findViewById(R.id.b1);
        btn2=(Button) findViewById(R.id.b2);
        sll=(LinearLayout) findViewById(R.id.scroll_linear_layout);
        btn1.setTextColor(Color.GREEN);
        sv=(ScrollView) findViewById(R.id.sc);


        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    if (c == 1)
                    {
                        try {Thread.sleep(1);} catch (InterruptedException ex){}
                        tv.setText(String.format("min: %02d sec: %02d ms: %03d",(min%60),(sec%60),(i%1000)));
                        ++i;
                    }
                }
            }
        });
        t.start();


        Thread t2=new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (true)
                {
                    if (c == 1)
                    {
                        try {Thread.sleep(1000);} catch (InterruptedException ex){}
                        ++sec;
                    }
                }
            }
        });
        t2.start();

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run()
            {
                while (true)
                {
                    if (c == 1)
                    {
                        try {Thread.sleep(60000);} catch (InterruptedException ex){}
                        ++min;
                    }
                }
            }
        });
        t3.start();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(c==0)
                {
                    c=1;
                    btn1.setText("stop");
                    btn2.setText("lap");
                }
                else
                {
                    c=0;
                    btn1.setText("start");
                    btn2.setText("reset");
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(c==1)
                {
                    str=String.format("Lap %02d-   %02d : %02d : %03d",(lapCount++),(min%60),(sec%60),(i%1000));
                    lp.append("\n"+str+"\n");
                    /*sv.post(new Runnable() {
                        @Override
                        public void run() {
                            sv.smoothScrollTo(0,lp.getBottom());
                        }
                    });*/
                }

                else if(c==0)
                {
                    c2=0;
                    i=0;
                    min=0;sec=0;
                    tv.setText("min: "+"00"+" sec: "+"00"+" ms: "+"000");
                }
            }
        });

    }
}
