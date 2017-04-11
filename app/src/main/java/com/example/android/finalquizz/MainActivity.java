 package com.example.android.finalquizz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

     //UI components
     private Spinner ageSpinner;
     private Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Innitiate UI components
        ageSpinner = (Spinner) findViewById(R.id.id_ageSpinner);

        //Innitiate variables
            String kidAge="";

        // Add listeners
        addListenerSubmitButton();
        // Spinner item selection Listener  
        addListenerOnSpinnerItemSelection();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_ages, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ageSpinner.setAdapter(dataAdapter);

        kidAge =  String.valueOf(ageSpinner.getSelectedItem());

    }

     private void addListenerOnSpinnerItemSelection() {
         ageSpinner.setOnItemSelectedListener(new SpinnerActivity());
     }

     public void addListenerSubmitButton() {
         btnSubmit.setOnClickListener(new View.OnClickListener() {
                 public void onClick (View v){
                     Toast.makeText(MainActivity.this,
                             "Spinner Test : " +
                                     "\n : " + String.valueOf(ageSpinner.getSelectedItem()),
                             Toast.LENGTH_SHORT).show();
                 }
             });
     }

     public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


         public void onItemSelected(AdapterView<?> parent, View view,
                                    int pos, long id) {
             // An item was selected. You can retrieve the selected item using
             // parent.getItemAtPosition(pos)
         }

         public void onNothingSelected(AdapterView<?> parent) {
             // Another interface callback
         }
     }

}
