package co.planetsystems.tela;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class EnrollActivityViewModel extends AndroidViewModel {
    private int fragmentPosition = 1;

    public EnrollActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public int getFragmentPosition() {
        return fragmentPosition;
    }

    public void setFragmentPostion(int fragmentPosition) {
        this.fragmentPosition = fragmentPostion;
    }
}
