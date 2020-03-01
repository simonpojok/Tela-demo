package co.planetsystems.tela.enroll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import co.planetsystems.tela.R;

public class SupplementaryFragment extends Fragment {
    private static final String SCHOOL_NAME = "co.planetsystems.tela.enroll.SupplementaryFragment.SCHOOL_NAME";
    private static final String DISTRICT = "co.planetsystems.tela.enroll.SupplementaryFragment.DISTRICT";
    private static final String ROLE = "co.planetsystems.tela.enroll.SupplementaryFragment.ROLE";

    private EditText school;
    private EditText district;
    private EditText role;

    public SupplementaryFragment() {
        // Required empty public constructor
    }

    public static BasicFragment newInstance() {
        return new BasicFragment();
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
