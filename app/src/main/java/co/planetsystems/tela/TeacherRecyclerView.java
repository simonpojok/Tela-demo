package co.planetsystems.tela;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeacherRecyclerView extends RecyclerView.Adapter<TeacherRecyclerView.TeacherHolder> {

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

        public TeacherHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            role = itemView.findViewById(R.id.role);
            district = itemView.findViewById(R.id.district);
        }
    }
}
