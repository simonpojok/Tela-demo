package co.planetsystems.tela;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EnrollActivity extends AppCompatActivity {
    public static final String ENROLL_ACTION = "co.planetsystems.tela.ACTION_ENROLL";
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText emailAddress;
    private EditText gender;
    private EditText nationalID;
    private EditText schoolName;
    private EditText district;
    private EditText role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        firstName = findViewById(R.id.tr_firstName);
        lastName = findViewById(R.id.tr_lastNames);
        phoneNumber = findViewById(R.id.tr_telephone);
        emailAddress = findViewById(R.id.tr_email);
        gender = findViewById(R.id.tr_gender);
        nationalID = findViewById(R.id.tr_school);
        district = findViewById(R.id.tr_district);
        role = findViewById(R.id.tr_role);
    }
}
