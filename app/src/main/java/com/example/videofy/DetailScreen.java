package com.example.videofy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import com.example.videofy.databinding.ActivityDetailScreenBinding;

import app.futured.hauler.DragDirection;
import app.futured.hauler.HaulerView;
import app.futured.hauler.OnDragDismissedListener;

public class DetailScreen extends AppCompatActivity {
    HaulerView hv;
    ActivityDetailScreenBinding binding;
    private KeyListener originalKeyListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hv = findViewById(R.id.haulerView);
        hv.setOnDragDismissedListener(new OnDragDismissedListener() {
                                                          @Override
                                                          public void onDismissed(DragDirection dragDirection) {
                                                             finish();
                                                          }
                                                    }
        );

  binding.commentButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          binding.commentButton.setVisibility(View.GONE);
          binding.editTextLayout.setVisibility(View.VISIBLE);



          originalKeyListener = binding.editText.getKeyListener();
          // Set it to null - this will make the field non-editable
          binding.editText.setKeyListener(null);

          binding.editText.setKeyListener(originalKeyListener);
          // Focus the field.
          binding.editText.requestFocus();
          // Show soft keyboard for the user to enter the value.
          InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
          imm.showSoftInput(binding.editText, InputMethodManager.SHOW_IMPLICIT);

          binding.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
              @Override
              public void onFocusChange(View v, boolean hasFocus) {
                  // If it loses focus...
                  if (!hasFocus) {
                      // Hide soft keyboard.
                      InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                      imm.hideSoftInputFromWindow(binding.editText.getWindowToken(), 0);
                      // Make it non-editable again.
                      binding.editText.setKeyListener(null);



                  }
              }
          });


          binding.editText.addTextChangedListener(new TextWatcher() {

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
                  binding.send.setVisibility(View.VISIBLE);
                  binding.editText.setError(null);
                  binding.profilePic.setVisibility(View.GONE);


              }

              @Override
              public void beforeTextChanged(CharSequence s, int start, int count,
                                            int after) {

              }

              @Override
              public void afterTextChanged(Editable s) {
                  binding.editText.setError(null);
                  if(s.toString().isEmpty())
                  {
                      binding.send.setVisibility(View.GONE);
                      binding.profilePic.setVisibility(View.VISIBLE);
                  }

              }
          });


      }
  });
    }
}