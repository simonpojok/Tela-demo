package co.planetsystems.tela.enroll;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import co.planetsystems.tela.R;


public class BasicFragment extends Fragment implements View.OnClickListener{
    private static final String FIRST_NAME = "co.planetsystems.tela.BasicFragment.FIRST_NAME";
    private static final String LAST_NAME = "co.planetsystems.tela.BasicFragment.LAST_NAME";
    private static final String PHONE_NUMBER = "co.planetsystems.tela.BasicFragment.PHONE_NUMBER";
    OnNextBasicClick onNextBasicClick;

    private TextInputEditText firstName;

    private TextInputEditText lastName;

    private TextInputEditText phoneNumber;
    private Button buttonNext;
    private Button buttonPrevious;



    public BasicFragment() {
        // Required empty public constructor
    }

    public static BasicFragment newInstance(String firstName, String lastName, String phoneNumber) {
        BasicFragment fragment = new BasicFragment();
        Bundle args = new Bundle();
        args.putString(FIRST_NAME, firstName);
        args.putString(LAST_NAME, lastName);
        args.putString(PHONE_NUMBER, phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onNextBasicClick = (OnNextBasicClick) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstName = view.findViewById(R.id.basic_firstName);
        lastName = view.findViewById(R.id.basic_lastName);
        phoneNumber = view.findViewById(R.id.basic_telephone);
        buttonNext = view.findViewById(R.id.basic_next);

        buttonNext.setOnClickListener(this);
        if (getArguments() != null) {
            firstName.setText(getArguments().getString(FIRST_NAME));
            lastName.setText(getArguments().getString(LAST_NAME));
            phoneNumber.setText(getArguments().getString(PHONE_NUMBER));
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.basic_next) {
            onNextBasicClick.clickNextBasic(
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    phoneNumber.getText().toString()
            );
        }
    }

    public interface OnNextBasicClick {
        void clickNextBasic(String firstName, String lastName, String phoneNumber);
    }



}
