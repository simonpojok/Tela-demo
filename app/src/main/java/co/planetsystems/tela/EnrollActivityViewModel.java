package co.planetsystems.tela;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class EnrollActivityViewModel extends AndroidViewModel {
    private String firstName;
    private String lastName;
    private String phone_number;
    private String email_address;
    private String gender;
    private String nationalId;
    private String school;
    private String district;
    private String role;

    public EnrollActivityViewModel(@NonNull Application application) {
        super(application);
    }

}
