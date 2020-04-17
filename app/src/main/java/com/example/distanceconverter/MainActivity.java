package com.example.distanceconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.view.View;
import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    public static double MILES_TO_KILO = 1.60934;
    public static double KILO_TO_MILES = 0.621371;
    private EditText miles;
    private TextView result;

    private EditText kilo;
    private TextView kilo_result;

    private TextView mile_text;
    private TextView kilo_text;

    private EditText input;
    private TextView output;

    private EditText kilo_input;
    private TextView kilo_output;

    private TextView record_history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         miles = findViewById(R.id.userInput);
         result = findViewById(R.id.resultline);

         kilo = findViewById(R.id.userInput);
         kilo_result = findViewById(R.id.resultline);


         mile_text = findViewById(R.id.mileVal); // get the instance of the mile textfile
         kilo_text = findViewById(R.id.kiloVal);

         input = findViewById(R.id.userInput);
        output = findViewById(R.id.resultline);


         kilo_input = findViewById(R.id.userInput);
         kilo_output = findViewById(R.id.resultline);


         record_history = findViewById(R.id.historyText);
        record_history.setMovementMethod(new ScrollingMovementMethod());
    }

    public void kiloConvert(View v) {

        double milesValue = Double.parseDouble(miles.getText().toString());

        double kilo = milesValue * MILES_TO_KILO;

        result.setText(String.format("%.1f", kilo));


    }

    public void milesConvert(View v) {

        double kiloValue = Double.parseDouble(kilo.getText().toString());

        double miles = kiloValue * KILO_TO_MILES;

        kilo_result.setText(String.format("%.1f", miles));
    }



    public void convert_methods(View v){
        RadioGroup rg = findViewById(R.id.conversion_Group);

        int id = rg.getCheckedRadioButtonId();


        switch(id){
            case R.id.miles_convert:
                kiloConvert(v);
                mile_text.setText("Miles Value:");
                kilo_text.setText("Kilometers Value:");
                record_history_MilestoKilo(v);

                break;

            case R.id.kilo_convert:
                milesConvert(v);
                mile_text.setText("Kilometers Value:");
                kilo_text.setText("Miles Value:");
                record_history_KilotoMiles(v);
                break;
        }
    }


    public void record_history_MilestoKilo(View v){

            record_history.setMovementMethod(new ScrollingMovementMethod());
            String final_result = "";


            String new_input = input.getText().toString();
            String new_result = output.getText().toString();


            final_result = new_input + "Mi ==> " + new_result + "Km"+ System.getProperty("line.separator");

          StringBuilder stringBuilder = new StringBuilder(final_result);
          stringBuilder.append(record_history.getText().toString());

          record_history.setText((stringBuilder));

          input.setText(""); // uncomment this to get rid of input after conversion
    }




    public void record_history_KilotoMiles(View v){

        record_history.setMovementMethod(new ScrollingMovementMethod());
        String final_result = "";
        String new_input = kilo_input.getText().toString();
        String new_result = kilo_output.getText().toString();

        final_result = new_input + "Km ==>" + new_result + "Mi" + System.getProperty("line.separator");

        StringBuilder stringBuilder = new StringBuilder(final_result);
        stringBuilder.append(record_history.getText().toString());

        record_history.setText((stringBuilder));

        kilo_input.setText(""); // uncomment this to get rid  of input after conversion  hi

    }



    public void clear_Records(View v){

        record_history.setText("");
    }


    @Override
    protected void onSaveInstanceState(@NonNull  Bundle outState) {

        outState.putString("Input for MilestoKilo", input.getText().toString()); // saving input for miles to kilo conversion
        outState.putString("Output for MilestoKilo", output.getText().toString()); // saving output for miles to kilo conversion

        outState.putString("Input for KilotoMiles", kilo_input.getText().toString()); // saving input for kilo to miles conversion
        outState.putString("Output for KilotoMiles", kilo_output.getText().toString()); // saving output for kilo to miles conversion

        outState.putString("Conversion History", record_history.getText().toString()); // saving the conversion history for both conversions

        // Call this last
        super.onSaveInstanceState(outState);
    }



    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        input.setText(savedInstanceState.getString("Input for MilestoKilo"));
        output.setText(savedInstanceState.getString("Output for MilestoKilo"));

        kilo_input.setText(savedInstanceState.getString("Input for KilotoMiles"));
        kilo_output.setText(savedInstanceState.getString("Output for KilotoMiles"));

        record_history.setText(savedInstanceState.getString("Conversion History"));
        // Call this last
        super.onRestoreInstanceState(savedInstanceState);
    }
}
