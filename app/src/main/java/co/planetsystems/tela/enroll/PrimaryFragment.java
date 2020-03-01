package co.planetsystems.tela.enroll;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import co.planetsystems.tela.R;

public class PrimaryFragment extends Fragment {
    private static final String EMAIL_ADDRESS = "co.planetsystems.tela.enroll.PrimaryFragment.EMAIL_ADDRESS";
    private static final String GENDER = "co.planetsystems.tela.enroll.PrimaryFragment.GENDER";
    private static final String NATIONAL_ID = "co.planetsystems.tela.enroll.PrimaryFragment.NATIONAL_ID";
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
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
