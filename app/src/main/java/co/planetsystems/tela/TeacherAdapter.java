package co.planetsystems.tela;

import android.content.Context;
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

    TeacherAdapter(Context context, OnTeacherClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public TeacherHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recycler_view, parent, false);
        return new TeacherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherHolder holder, int position) {
        Teacher teacher = teachers.get(position);
        holder.bindTeacher(teacher, listener);
    }

    @Override
    public int getItemCount() {
        if ( teachers != null ) {
            return teachers.size();
        } else {
            return 0;
        }
    }

    public Teacher getTeacherAtPosition(int position) {
        return teachers.get(position);
    }

    void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    class TeacherHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView role;
        TextView district;
        View itemHolder;

        TeacherHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            role = itemView.findViewById(R.id.role);
            district = itemView.findViewById(R.id.district);
            this.itemHolder = itemView;
        }

        void bindTeacher(Teacher teacher, final OnTeacherClickListener listener) {
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
