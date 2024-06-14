package com.enola.sharedpreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.enola.sharedpreference.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sp = getSharedPreferences("databese", MODE_PRIVATE);
        initUi();
        readDataFromIntent();

    }

    private void initUi() {
        binding.etValue.setText(readValueFromSp());
        binding.btSave.setOnClickListener((v -> {
            String value = binding.etValue.getText().toString();
            if(!value.isEmpty()){
                writeValueToSp(value);
                Toast.makeText(this,"Saved Value Success!",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Please put some value in edit text", Toast.LENGTH_SHORT).show();
            }
        }));
        binding.btClear.setOnClickListener(v->{
            binding.etValue.getText().clear();
            clearSP();
        });
    }

    private void clearSP() {
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    private void writeValueToSp(String value) {
        SharedPreferences.Editor editor =sp.edit();
        editor.putString("value",value);
        editor.apply();
    }

    private String readValueFromSp() {
        String value = sp.getString("value","ABC");
        return value;
    }

    private void readDataFromIntent() {
        Intent intent = getIntent();
        if(intent != null){
            String key = intent.getStringExtra("key");
            binding.etValue.setText(key);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("SPS", "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("SPS", "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("SPS", "On Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("SPS", "On Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SPS", "On Destroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("SPS", "On Restart");
    }

}