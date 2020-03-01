package co.planetsystems.tela.enroll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import co.planetsystems.tela.R;

public class PrimaryFragment extends Fragment implements View.OnClickListener{
    private static final String EMAIL_ADDRESS = "co.planetsystems.tela.enroll.PrimaryFragment.EMAIL_ADDRESS";
    private static final String GENDER = "co.planetsystems.tela.enroll.PrimaryFragment.GENDER";
    private static final String NATIONAL_ID = "co.planetsystems.tela.enroll.PrimaryFragment.NATIONAL_ID";
    private TextInputEditText email;
    private TextInputEditText gender;
    private TextInputEditText nationalId;
    private Button nextButton;
    private Button previousButton;
    private OnNextPrimaryClickListener clickNextListener;
    private OnPreviousPrimaryClickListener clickPreviousListener;
    public PrimaryFragment() {
        // Required empty public constructor
    }

    public static PrimaryFragment newInstance(String email, String gender, String nationalId) {
        PrimaryFragment fragment = new PrimaryFragment();
        Bundle args = new Bundle();
        args.putString(EMAIL_ADDRESS, email);
        args.putString(GENDER, gender);
        args.putString(NATIONAL_ID, nationalId);
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
        return inflater.inflate(R.layout.fragment_primary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email = view.findViewById(R.id.primary_email);
        gender = view.findViewById(R.id.primary_gender);
        nationalId = view.findViewById(R.id.primary_nin);
        nextButton = view.findViewById(R.id.primary_next);
        previousButton = view.findViewById(R.id.primary_previous);

        if (getArguments() != null) {
            email.setText(getArguments().getString(EMAIL_ADDRESS));
            gender.setText(getArguments().getString(GENDER));
            nationalId.setText(getArguments().getString(NATIONAL_ID));
        }

        nextButton.setOnClickListener(this);
        previousButton.setOnClickListener(this);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickNextListener = (OnNextPrimaryClickListener) context;
        clickPreviousListener = (OnPreviousPrimaryClickListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.primary_next) {
            clickNextListener.onNextPrimaryClick(
                    email.getText().toString(),
                    gender.getText().toString(),
                    nationalId.getText().toString()
            );
        }

        if (v.getId() == R.id.primary_previous) {
            clickPreviousListener.onPreviousPrimaryClick(
                    email.getText().toString(),
                    gender.getText().toString(),
                    nationalId.getText().toString()
            );
        }
    }

    public interface OnNextPrimaryClickListener {
        void onNextPrimaryClick(String email, String gender, String nationId);
    }

    public interface  OnPreviousPrimaryClickListener {
        void onPreviousPrimaryClick(String email, String gender, String nationId);
    }
}
