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

public class SupplementaryFragment extends Fragment
implements View.OnClickListener {
    private static final String SCHOOL_NAME = "co.planetsystems.tela.enroll.SupplementaryFragment.SCHOOL_NAME";
    private static final String DISTRICT = "co.planetsystems.tela.enroll.SupplementaryFragment.DISTRICT";
    private static final String ROLE = "co.planetsystems.tela.enroll.SupplementaryFragment.ROLE";

    private TextInputEditText school;
    private TextInputEditText district;
    private TextInputEditText role;
    private Button previous;
    private Button save;
    private OnPreviousClickSupplementaryListener supplementaryListener;
    private OnSaveEveryDataAndCloseListener saveEveryDataAndCloseListener;

    public SupplementaryFragment() {
        // Required empty public constructor
    }

    public static SupplementaryFragment newInstance(String schoolName, String district, String role) {
        SupplementaryFragment fragment = new SupplementaryFragment();
        Bundle args = new Bundle();
        args.putString(SCHOOL_NAME, schoolName);
        args.putString(DISTRICT, district);
        args.putString(ROLE, role);
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
        return inflater.inflate(R.layout.fragment_supplementary, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        supplementaryListener = (OnPreviousClickSupplementaryListener) context;
        saveEveryDataAndCloseListener = (OnSaveEveryDataAndCloseListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        school = view.findViewById(R.id.sup_school);
        district = view.findViewById(R.id.sup_district);
        role = view.findViewById(R.id.sup_role);
        previous = view.findViewById(R.id.sup_previous);
        save = view.findViewById(R.id.sup_save);

        if (getArguments() != null) {
            school.setText(getArguments().getString(SCHOOL_NAME));
            district.setText(getArguments().getString(DISTRICT));
            role.setText(getArguments().getString(ROLE));
        }

        previous.setOnClickListener(this);
        save.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sup_previous) {
            supplementaryListener.onPreviousSupplementaryClick(
                    school.getText().toString(),
                    district.getText().toString(),
                    role.getText().toString()
            );
        } else if (v.getId() == R.id.sup_save) {
            saveEveryDataAndCloseListener.saveEveryData(
                    school.getText().toString(),
                    district.getText().toString(),
                    role.getText().toString()
            );
        }
    }

    public interface OnPreviousClickSupplementaryListener {
        void onPreviousSupplementaryClick(String school, String district, String role);
    }

    public interface  OnSaveEveryDataAndCloseListener {
        void saveEveryData(String school, String district, String role);
    }
}
