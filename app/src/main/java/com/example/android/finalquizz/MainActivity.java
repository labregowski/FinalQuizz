package com.example.android.finalquizz;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //UI components
    private Spinner ageSpinner;
    private Button btnSubmit;


    //Variables




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Innitiate UI components
        ageSpinner = (Spinner) findViewById(R.id.id_ageSpinner);
        btnSubmit = (Button) findViewById(R.id.id_submit);

        //Innitiate variables
        String kidAge="";

        // Calling Add listeners' Methods-
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


    /**
     * Adds Listener for Submit Button
     */
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

    /**
     * Spinner: Responding User Selections
     */
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

    /**
     * Controling Radio Button Groups
     */


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
//            case R.id.radio_pirates:
//                if (checked)
//                    // Pirates are the best
//                    break;
//            case R.id.radio_ninjas:
//                if (checked)
//                    // Ninjas rule
//                    break;
        }
    }



}//End of MainActivity