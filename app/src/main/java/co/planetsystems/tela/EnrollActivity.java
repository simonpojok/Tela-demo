package co.planetsystems.tela;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import co.planetsystems.tela.enroll.BasicFragment;
import co.planetsystems.tela.enroll.PrimaryFragment;

public class EnrollActivity extends AppCompatActivity {
    public static final String ACTION_ENROLL = "co.planetsystems.tela.ACTION_ENROLL";
    public static final String ACTION_VERIFY = "co.planetsystems.tela.ACTION_VERIFY";
    public static final String FIRST_NAME = "co.planetsystems.tela.FIRST_NAME";
    public static final String LAST_NAME = "co.planetsystems.tela.LAST_NAME";
    public static final String PHONE_NUMBER = "co.planetsystems.tela.PHONE_NUMBER";
    public static final String EMAIL_ADDRESS = "co.planetsystems.tela.EMAIL_ADDRESS";
    public static final String GENDER = "co.planetsystems.tela.GENDER";
    public static final String NATIONAL_ID = "co.planetsystems.tela.NATIONAL_ID";
    public static final String SCHOOL_NAME = "co.planetsystems.tela.SCHOOL_NAME";
    public static final String DISTRICT = "co.planetsystems.tela.DISTRICT";
    public static final String ROLE = "co.planetsystems.tela.ROLE";
    private EditText firstName;
    private EditText lastName;
    private EditText phoneNumber;
    private EditText emailAddress;
    private EditText gender;
    private EditText nationalID;
    private EditText schoolName;
    private EditText district;
    private EditText role;
    private Button save;
    private ViewPager mPager;
    private Button previous;
    private Button next;

    int nLayouts[] = {
            R.layout.basic_information,
            R.layout.primary_information,
            R.layout.suplementary_information
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        previous = findViewById(R.id.previous);
        next = findViewById(R.id.next);
        save = findViewById(R.id.save);

//
//        firstName = findViewById(R.id.tr_firstName);
//        lastName = findViewById(R.id.tr_lastNames);
//        phoneNumber = findViewById(R.id.tr_telephone);
//        emailAddress = findViewById(R.id.tr_email);
//        gender = findViewById(R.id.tr_gender);
//        schoolName = findViewById(R.id.tr_school);
//        nationalID = findViewById(R.id.tr_nin);
//        district = findViewById(R.id.tr_district);
//        role = findViewById(R.id.tr_role);
//        saveButton = findViewById(R.id.button_save);

//        if (Objects.equals(getIntent().getAction(), ACTION_ENROLL)) {
//            enrollTeacher();
//        } else {
//            Intent intent = getIntent();
//            ((TextView)findViewById(R.id.action_title)).setText("Verify Teacher");
//            firstName.setText(intent.getStringExtra(FIRST_NAME));
//            lastName.setText(intent.getStringExtra(LAST_NAME));
//            phoneNumber.setText(intent.getStringExtra(PHONE_NUMBER));
//            emailAddress.setText(intent.getStringExtra(EMAIL_ADDRESS));
//            gender.setText(intent.getStringExtra(GENDER));
//            nationalID.setText(intent.getStringExtra(NATIONAL_ID));
//            schoolName.setText(intent.getStringExtra(SCHOOL_NAME));
//            district.setText(intent.getStringExtra(DISTRICT));
//            role.setText(intent.getStringExtra(ROLE));
//
//            firstName.setEnabled(false);
//            lastName.setEnabled(false);
//            phoneNumber.setEnabled(false);
//            emailAddress.setEnabled(false);
//            gender.setEnabled(false);
//            nationalID.setEnabled(false);
//            schoolName.setEnabled(false);
//            district.setEnabled(false);
//            role.setEnabled(false);
//            saveButton.setVisibility(View.INVISIBLE);
//        }

        if (getSupportFragmentManager().findFragmentById(R.id.enroll_fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.enroll_fragment, new BasicFragment())
                    .commit();
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (getSupportFragmentManager().findFragmentById(R.id.enroll_fragment) != null) {
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.enroll_fragment, new PrimaryFragment()
                    ).commitNow();
//                }
            }
        });
    }

    private void enrollTeacher() {
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(firstName.getText())) {
//                    firstName.setBackgroundColor(getResources().getColor(R.color.colorRed));
//                }
//
//                if (TextUtils.isEmpty(lastName.getText())) {
//
//                }
//            }
//        });
    }

    class EnrollPagerAdapter extends PagerAdapter {
        LayoutInflater inflater;

        public EnrollPagerAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @NonNull
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(nLayouts[position], null);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return nLayouts.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    // fragment
}
