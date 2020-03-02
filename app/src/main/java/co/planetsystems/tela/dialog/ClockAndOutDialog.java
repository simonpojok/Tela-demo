package co.planetsystems.tela.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import co.planetsystems.tela.R;

public class ClockAndOutDialog extends DialogFragment {
    OnClockInAndOutDialogListener listener;
    public static final String DIALOG_ACTION = "co.planetsystems.tela.dialog.DialogFragment.DIALOG_ACTION";
    public static final String DIALOG_TIME = "co.planetsystems.tela.dialog.DialogFragment.DIALOG_TIME";
    public static final String DIALOG_TEACHER_NAME = "co.planetsystems.tela.dialog.DialogFragment.DIALOG_TEACHER_NAME";
    TextView teacherName;
    TextView action;
    TextView time;

    public static ClockAndOutDialog newInstance(String teacherName, String action, String time) {
        ClockAndOutDialog dialog = new ClockAndOutDialog();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_TEACHER_NAME, teacherName);
        bundle.putString(DIALOG_ACTION, action);
        bundle.putString(DIALOG_TIME, time);
        return dialog;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.clock_in_and_out, null))
                .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we are safe
                    }
                }).setTitle(R.string.clock_in_and_out);

        return builder.create();
    }

    public interface  OnClockInAndOutDialogListener {
        void onClockInAndOutPositiveClick(DialogFragment dialogFragment);
        void onClockInAndOutNegativeClick(DialogFragment dialogFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnClockInAndOutDialogListener) context;
        } catch (ClassCastException e) {
//            throw new ClassCastException(getActivity().toString(), "Must implement This Interface");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teacherName = view.findViewById(R.id.dialog_teacher_name);
        action = view.findViewById(R.id.dialog_action);
        time = view.findViewById(R.id.dialog_clock);
        if (getArguments() != null) {
            teacherName.setText(getArguments().getString(DIALOG_TEACHER_NAME));
            action.setText(DIALOG_ACTION);
            time.setText(DIALOG_TIME);
        }

    }
}
