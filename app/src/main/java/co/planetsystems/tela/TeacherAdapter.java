package co.planetsystems.tela;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherHolder> {
    private final LayoutInflater layoutInflater;
    private List<Teacher> teachers;
    private OnTeacherClickListener listener;

    @NonNull
    @Override
    public TeacherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TeacherHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView role;
        TextView district;
        View itemHolder;

        public TeacherHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            role = itemView.findViewById(R.id.role);
            district = itemView.findViewById(R.id.district);
            this.itemHolder = itemView;
        }

        public void bindTeacher(Teacher teacher, final OnTeacherClickListener listener) {
            firstName.setText(teacher.getFirstName());
            lastName.setText(teacher.getLastName());
            role.setText(teacher.getRole());
            district.setText(teacher.getDistrict());
            itemHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onTeacherClick(teacher);
                }
            });

        }
    }

    public interface OnTeacherClickListener {
        void onTeacherClick(Teacher teacher);
    }
}
