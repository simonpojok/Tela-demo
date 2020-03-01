package co.planetsystems.tela.enroll;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import co.planetsystems.tela.R;


public class BasicFragment extends Fragment implements View.OnClickListener, Validator.ValidationListener {
    private static final String FIRST_NAME = "co.planetsystems.tela.BasicFragment.FIRST_NAME";
    private static final String LAST_NAME = "co.planetsystems.tela.BasicFragment.LAST_NAME";
    private static final String PHONE_NUMBER = "co.planetsystems.tela.BasicFragment.PHONE_NUMBER";
    OnNextBasicClick onNextBasicClick;

    @NotEmpty
    @Length(min = 3, max = 20)
    private EditText firstName;

    @NotEmpty
    @Length(min = 3, max = 20)
    private EditText lastName;

    @NotEmpty
    @Length(max = 10, min = 10)
    private EditText phoneNumber;
    private Button buttonNext;
    private Button buttonPrevious;

    private Validator validator;



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
        validator = new Validator(this);
        validator.setValidationListener(this);
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
        validator.validate();
        if (v.getId() == R.id.basic_next) {
            onNextBasicClick.clickNextBasic(
                    firstName.getText().toString(),
                    lastName.getText().toString(),
                    phoneNumber.getText().toString()
            );
        }
    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(getActivity(), "We got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors ) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(getActivity());
            if (view instanceof EditText) {
                ((EditText)view).setError(message);
            } else {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public interface OnNextBasicClick {
        void clickNextBasic(String firstName, String lastName, String phoneNumber);
    }

}
