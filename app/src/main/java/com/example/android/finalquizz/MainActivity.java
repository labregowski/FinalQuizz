package com.example.android.finalquizz;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    //UI components
    private Spinner     ageSpinner;
    private Button      btnSubmit,
                        btnEmail,
                        btnReset;
                        //these TextViews will receive focus at beginning and after buttons are pushed
    private TextView    summaryArea,
                        firstQuestion;
    private RadioGroup  radioSexGroup,
                        radioDailyUsageGroup,
                        radioGroupInterestedInJournalismClub,
                        radioGroupInterestedInDIYRobotic;

    //Declaring Variables
    private String
                    name,
                    wantsToLearnOther,
                    parentsEmail,
                    // these next, come from Radio or Check boxes
                    kidsSex,
                    usagePerDay,
                    usage,

                    wantsJournalistClub,
                    wantsDIYRobots,
                    kidsAge,
                    //to colect labels frm selected checkboxes
                    mobileOwnership,
                    ownMobile,
                    fromFamily,
                    fromFriend,
                    fromOther,
                    playGames,
                    chat,
                    callsOrVideo,
                    socialNetworks,
                    surfWeb,
                    schoolWork,
                    // these next, are auxiiary variables to make text more natural
                    joinJournalClub,
                    joinDIYRobots,
                    otherInterests,
                    newParentsMobile;

    private int
                    parentsMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize variables - Read in a blog that it is good preceureto innitialize in onCreate.
                    name="";
                    wantsToLearnOther="";
                    parentsEmail="";
                    // these next, will come from Radio or Check boxes
                    kidsSex="";
                    usagePerDay="";

                    wantsJournalistClub ="";
                    wantsDIYRobots="";
                    kidsAge="";
                    parentsMobile=0;
                    joinJournalClub ="";
                    joinDIYRobots="";
                    otherInterests="";
                    newParentsMobile="";

                    mobileOwnership="";
                    ownMobile ="";
                    fromFamily ="";
                    fromFriend ="";
                    fromOther ="";

                    usage="";
                    playGames ="";
                    chat ="";
                    callsOrVideo ="";
                    socialNetworks ="";
                    surfWeb ="";
                    schoolWork ="";

        // Initiate UI components
        ageSpinner = (Spinner) findViewById(R.id.id_ageSpinner);

        radioSexGroup = (RadioGroup) findViewById(R.id.id_RG_boyOrGirl);
        radioDailyUsageGroup = (RadioGroup) findViewById(R.id.id_RG_hoursDay);
        radioGroupInterestedInJournalismClub = (RadioGroup) findViewById(R.id.id_RG_journalism);
        radioGroupInterestedInDIYRobotic = (RadioGroup) findViewById(R.id.id_RG_DIYRobotic);

        btnSubmit = (Button) findViewById(R.id.id_submit);
        btnEmail = (Button) findViewById(R.id.id_eMailData);
        btnReset = (Button) findViewById(R.id.id_reset);

        summaryArea= (TextView)findViewById(R.id.id_quizSummary);
        firstQuestion = (TextView)findViewById(R.id.id_firstQuestion);

        //Setting focu on firstQuestion TextView, not to open the tyepad
        firstQuestion.requestFocus();

        // Calling Add listeners' Methods
        addListenerEmailButton();
        addListenerSubmitButton();


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> dataAdapter = ArrayAdapter.createFromResource(this,R.array.spinner_ages, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        ageSpinner.setAdapter(dataAdapter);

    }//EO OnCreate

    /**
     * Returns Labels of Selected CheckBoxes
     * @param checkBoxRId R.id of chckBox
     * @return Text Label
     */
    private String getTextfromCheckedBoxes(int checkBoxRId) {
        // Figure out if the user wants choclate topping
        CheckBox chckBox = (CheckBox) findViewById(checkBoxRId);
        if (chckBox.isChecked()){
            return chckBox.getText().toString();
        }else{
            return "";
        }
    }

    /**
     * Reads contents ofEdt TExt fields
     * @param EditTextId id of the field
     * @return the contents of the field
     */
    private String getFromEditText(int EditTextId){
        EditText field = (EditText) findViewById(EditTextId);
        Editable contentEditable = field.getText();
        return contentEditable.toString();
    }

    /**
     * Reads RadioButton Selected Element
     * @param m_radioGroup any radioGroup
     * @return The chosen radio in that group
     */
    private String getRadioGroupResults (RadioGroup m_radioGroup){
        if(m_radioGroup.getCheckedRadioButtonId() != -1) {
            int selectId = m_radioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(selectId);

            return radioButton.getText().toString();
        }
        return "did not reply";
    }

    /**
     *
     * @param message summary of collected data
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.id_quizSummary);
        orderSummaryTextView.setText(message);
    }

    /**
     * Adds Listener to submission Button and displays summary
     */
    public void addListenerSubmitButton(){
       btnSubmit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               displayQuizSummary (createQuizSummary());
               summaryArea.requestFocus();

           }
       });

       }
    /**
     * Adds Listener to submission Button and displays summary
     */
    public void addListenerEmailButton() {
    btnEmail.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            emailSummary (createQuizSummary());

        }
    });

    }


    /**
     * retrieves the variables (user inserted) from the UI.
     * The variables are global. So, no need to return - they are available to all methods
     */

    private void getCurrentVariableValues(){
        //From EditText
        name=getFromEditText(R.id.id_name);
        wantsToLearnOther=getFromEditText(R.id.id_knowOther);
        parentsEmail=getFromEditText(R.id.id_email);

        //From Number TextBoxes - Crashed if empty
        String number = getFromEditText(R.id.id_telephone);
        if (!number.equals("")){
            parentsMobile=parseInt(number);
        };

        //From RadioButtons
        kidsSex=getRadioGroupResults(radioSexGroup);
        usagePerDay=getRadioGroupResults(radioDailyUsageGroup);
        wantsJournalistClub=getRadioGroupResults(radioGroupInterestedInJournalismClub);
        wantsDIYRobots=getRadioGroupResults(radioGroupInterestedInDIYRobotic);

        //From CheckBoxes - Whose Mobile
        ownMobile=getTextfromCheckedBoxes(R.id.id_ownMobile);
        fromFamily=getTextfromCheckedBoxes(R.id.id_familyMobile);
        fromFriend=getTextfromCheckedBoxes(R.id.id_friendMobile);
        fromOther=getTextfromCheckedBoxes(R.id.id_fromSomeonElse);

        //From CheckBoxes - Usage
        playGames=getTextfromCheckedBoxes(R.id.id_playGames);
        chat=getTextfromCheckedBoxes(R.id.id_chat);
        callsOrVideo=getTextfromCheckedBoxes(R.id.id_talk);
        socialNetworks=getTextfromCheckedBoxes(R.id.id_socialNetworks);
        surfWeb=getTextfromCheckedBoxes(R.id.id_surfWeb);
        schoolWork=getTextfromCheckedBoxes(R.id.id_schoolWork);

        //From Spinner
        kidsAge=String.valueOf(ageSpinner.getSelectedItem());
    }

    /**
     * Creates the summary of the user input
     * @return the summary
     */
    private String createQuizSummary() {
        getCurrentVariableValues(); //variables are global, so they will refresh here too :D
        // Preparing nicer text for "Wants to Learn"

            if (name.equals("")){
                name+="not provided";
            };

            if (kidsAge.equals("(your age)")){
                kidsAge = "did not reply";
            };

           if (wantsJournalistClub.equals("Yes")){
               joinJournalClub = "\n" + "Wants to join e-Journalists Club";
           };

           if (wantsDIYRobots.equals("Yes")){
               joinDIYRobots = "\n" + "Wants to join DIY Robots Club";
            };

           if (!wantsToLearnOther.equals("")){
               otherInterests = "\n" + "Is interested in "+ wantsToLearnOther;
            };

            if (parentsEmail.equals("")){
                parentsEmail="not provided";
            };

            if (parentsMobile==0){
                newParentsMobile="not provided";
            }else{
                newParentsMobile+= parentsMobile;
            };

            //Mobile Ownership
            mobileOwnership = "Usual Mobile: ";
            String mobileOwnershipStatic = mobileOwnership;
            if (!ownMobile.equals("")){
                mobileOwnership += "\n         "+ownMobile;
            };

            if (!fromFamily.equals("")){
                mobileOwnership += "\n         "+fromFamily;
            };

            if (!fromFriend.equals("")){
                mobileOwnership += "\n         "+fromFriend;
            };

            if (!fromOther.equals("")){
                mobileOwnership += "\n         "+fromOther;
            };
            // DEPRECATED if (ownMobile.equals("")&&fromFamily.equals("")&&fromFriend.equals("")&&fromOther.equals(""))
            if (mobileOwnership.equals(mobileOwnershipStatic))
            {
                mobileOwnership += "did not reply";
            };


            //Usage

            usage="Uses smartphone to: ";
            String usageStatic = usage;
            if (!playGames.equals("")){
            usage += "\n         "+playGames;
            };
            if (!chat.equals("")){
                usage += "\n         "+chat;
            };
            if (!callsOrVideo.equals("")){
                usage += "\n         "+callsOrVideo;
            };
            if (!socialNetworks.equals("")){
                usage += "\n         "+socialNetworks;
            };
            if (!surfWeb.equals("")){
                usage += "\n         "+surfWeb;
            };
            if (!schoolWork.equals("")){
                usage += "\n         "+schoolWork;
            };

           // DEPRECATED   if (playGames.equals("")&&chat.equals("")&&callsOrVideo.equals("")&&socialNetworks.equals("")
//                    &&surfWeb.equals("")&&schoolWork.equals(""))
            if (usage.equals(usageStatic))
            {
                usage += "did not reply";
            };

            String quizSummary = "Name: " + name;
            quizSummary += "\n" + "Sex: " + kidsSex;
            quizSummary += "\n" + "Age: " + kidsAge;
            quizSummary += "\n";
            quizSummary += "\n" + "Average Mobile Usage: " + usagePerDay;
            quizSummary += "\n" + mobileOwnership;
            quizSummary += "\n" + usage;
            quizSummary += "\n";
            quizSummary += joinJournalClub;
            quizSummary += joinDIYRobots;
            quizSummary += otherInterests;
            quizSummary += "\n";
            quizSummary += "\n" + "Parents' email: " + parentsEmail;
            quizSummary += "\n" +  "Parents' telephone: " + newParentsMobile;

            return quizSummary;
    }

    /**
     * displays quizSummary on screen
     * @param message is the message to be sent to office, containing data
     */
    private void displayQuizSummary(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.id_quizSummary);
        orderSummaryTextView.setText(message);
    }

    /**
     *TODO email not launching
     * @param summary is the message to be sent to office, containing data
     */

    private void emailSummary(String summary) {
        Intent intent = new Intent (Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getResources().getString(R.string.sendTo)});
//        intent.putExtra(Intent.EXTRA_EMAIL, getResources().getString(R.string.sendTo));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.subject));
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

}//End of MainActivity
