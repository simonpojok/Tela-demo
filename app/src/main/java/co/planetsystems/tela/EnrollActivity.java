package co.planetsystems.tela;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
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
import co.planetsystems.tela.enroll.SupplementaryFragment;

public class EnrollActivity extends AppCompatActivity implements BasicFragment.OnNextClick {
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
    private Button third;
    private Button second;
    private Button first;
    private EnrollActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);


        save = findViewById(R.id.save);
        viewModel = new ViewModelProvider(this).get(EnrollActivityViewModel.class);

        if (getSupportFragmentManager().findFragmentById(R.id.enroll_fragment) == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.enroll_fragment, new BasicFragment())
                    .commit();
        }
    }

    @Override
    public void clickNext(String firstName, String lastName, String phoneNumber) {
        Toast.makeText(this, firstName, Toast.LENGTH_SHORT).show();
    }
}
