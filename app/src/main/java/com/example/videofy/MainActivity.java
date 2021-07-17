package com.example.videofy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.videofy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    boolean beenThereFromTop = false;
    boolean beenThereFromBottom = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonPopUpWindow(v);
            }
        });

        binding.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startnewActivity(v);
            }
        });


        binding.scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {

                ImageView whereToStop = (ImageView) findViewById(R.id.detail_image);
                final int y = whereToStop.getBottom();

                int scrollY = binding.scrollview.getScrollY();

// manage scrolling from top to bottom

                if (scrollY > y) {
                    if (!beenThereFromTop) {
                        beenThereFromTop = true;
                        binding.scrollview.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.scrollview.smoothScrollTo(0, y);
                            }
                        });
                    }
                }
                if (scrollY < y && beenThereFromTop) {
                    beenThereFromTop = false;
                }

// manage scrolling from bottom to top
/*
                if (scrollY < y) {
                    if (!beenThereFromBottom) {
                        beenThereFromBottom = true;
                        binding.scrollview.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.scrollview.smoothScrollTo(0, y);
                            }
                        });
                    }
                }
                if (scrollY > y && beenThereFromBottom) {
                    beenThereFromBottom = false;
                }
            */
            }
        });
/*
        binding.scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {

                ImageView whereToStop = (ImageView) findViewById(R.id.rose_img);
                final int y = whereToStop.getBottom();

                int scrollY = binding.scrollview.getScrollY();

// manage scrolling from top to bottom

                if (scrollY > y) {
                    if (!beenThereFromTop) {
                        beenThereFromTop = true;
                        binding.scrollview.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.scrollview.smoothScrollTo(0, y);
                            }
                        });
                    }
                }
                if (scrollY < y && beenThereFromTop) {
                    beenThereFromTop = false;
                }

// manage scrolling from bottom to top

                if (scrollY < y) {
                    if (!beenThereFromBottom) {
                        beenThereFromBottom = true;
                        binding.scrollview.post(new Runnable() {
                            @Override
                            public void run() {
                                binding.scrollview.smoothScrollTo(0, y);
                            }
                        });
                    }
                }
                if (scrollY > y && beenThereFromBottom) {
                    beenThereFromBottom = false;
                }
            }

        });
        */

    }

   public void startnewActivity(View view) {


                startActivity(new Intent(MainActivity.this,DetailScreen.class));

    }

    public void buttonPopUpWindow(View view)
    {

        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        View viewPopupWindow=layoutInflater.inflate(R.layout.popupwindow_layout,null);

       PopupWindow popupWindow=new PopupWindow(viewPopupWindow,1000,600,true);

        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        popupWindow.showAtLocation(view, Gravity.TOP,0,10);
       // viewPopupWindow.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   popupWindow.dismiss();
               // return;
            //}
        //});
    }
}