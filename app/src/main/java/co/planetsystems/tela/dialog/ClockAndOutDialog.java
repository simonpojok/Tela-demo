package co.planetsystems.tela.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import co.planetsystems.tela.R;

public class ClockAndOutDialog extends DialogFragment {
    OnClockInAndOutDialogListener listener;
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
                });

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
}
