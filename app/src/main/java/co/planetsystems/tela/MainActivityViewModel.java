package co.planetsystems.tela;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private TeacherRespository respository;
    private LiveData<List<Teacher>> teachers;
    private LiveData<List<Attendance>> attendances;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }
}
