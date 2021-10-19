package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.*;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private EditText principal;
    public EditText interest;
    private EditText time;
    private Button calc;
    private TextView r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        principal = (EditText)findViewById(R.id.e1);
        interest = (EditText)findViewById(R.id.e2);
        time = (EditText)findViewById(R.id.e3);
        calc = (Button) findViewById(R.id.calculator);
        r = (TextView) findViewById(R.id.result);
        calc.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                double p = Integer.parseInt(principal.getText().toString());
                double i = Integer.parseInt(interest.getText().toString());
                double n = Integer.parseInt(time.getText().toString());
                i=i/100; //interest as percentage
                n=n*12; //total number of months

                //formula = P [ i(1 + i)^n ] / [ (1 + i)^n – 1]
                double payments = p *((i*(Math.pow(1+i,n)) ) / (Math.pow(1+i, n)-1) );
                int payments_final = (int)payments;

                r.setText("Your minimum monthly payments are $" + payments_final);

                Intent in = new Intent(MainActivity.this, SecondFragment.class);
                in.putExtra("message" , r.getText().toString());
                startActivity(in);

            }
        });



        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void disable(View v){
        v.setEnabled(false);
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



}