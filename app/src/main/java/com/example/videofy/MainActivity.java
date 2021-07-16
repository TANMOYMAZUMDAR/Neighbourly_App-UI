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
import android.view.Window;
import android.widget.PopupWindow;

import com.example.videofy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
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