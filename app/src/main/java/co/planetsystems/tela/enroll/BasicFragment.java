package co.planetsystems.tela.enroll;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.planetsystems.tela.R;


public class BasicFragment extends Fragment {
    private static final String FIRST_NAME = "co.planetsystems.tela.BasicFragment.FIRST_NAME";
    private static final String LAST_NAME = "co.planetsystems.tela.BasicFragment.LAST_NAME";
    private static final String PHONE_NUMBER = "co.planetsystems.tela.BasicFragment.PHONE_NUMBER";



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
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basic, container, false);
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
