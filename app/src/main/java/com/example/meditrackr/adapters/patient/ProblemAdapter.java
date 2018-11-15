package com.example.meditrackr.adapters.patient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.meditrackr.R;

import com.example.meditrackr.controllers.ProfileManager;
import com.example.meditrackr.models.Patient;
import com.example.meditrackr.models.ProblemList;
import com.example.meditrackr.ui.patient.EditProblemFragment;
import com.example.meditrackr.ui.patient.RecordsFragment;

import net.steamcrafted.materialiconlib.MaterialIconView;


public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ViewHolder>{
    private FragmentActivity activity;
    private Patient patient = ProfileManager.getPatient();
    private ProblemList problems = patient.getProblems();

    // constructor
    public ProblemAdapter(FragmentActivity activity) {
        this.activity = activity;
    }

    // display the view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View problemView = inflater.inflate(R.layout.problem_entry, parent, false);
        return new ViewHolder(problemView, this);
    }



    // set the data into each viewHolder (ie. place what each emotion has into the view)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(problems.getProblem(position).getTitle());
        holder.date.setText(problems.getProblem(position).getDate());
        holder.description.setText(problems.getProblem(position).getDescription());
        holder.totalRecords.setText("Number of records: "+problems.getProblem(position).getRecords().getSize());
    }

    @Override
    public int getItemCount() {
        return problems.getSize();
    }


    // place each problem into its corresponding view
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProblemAdapter adapter;
        public TextView title;
        public TextView date;
        public TextView description;
        public TextView totalRecords;
        public MaterialIconView deleteProblem;
        public MaterialIconView editProblem;

        public ViewHolder(View itemView, final ProblemAdapter adapter){
            super(itemView);
            title = itemView.findViewById(R.id.problem_title);
            date = itemView.findViewById(R.id.problem_date);
            description = itemView.findViewById(R.id.problem_description);
            totalRecords = itemView.findViewById(R.id.number_records_title);
            deleteProblem = itemView.findViewById(R.id.problem_delete_button);
            editProblem = itemView.findViewById(R.id.problem_edit_button);
            itemView.setOnClickListener(this);
            this.adapter = adapter;


            // onclick listener for delete problem
            deleteProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final int position = getAdapterPosition();
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(adapter.activity, R.style.AlertDialogStyle);
                    builder1.setMessage("Are you sure you want to delete the problem?");
                    builder1.setCancelable(true);
                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    adapter.problems.removeProblem(position);
                                    adapter.notifyItemRemoved(position);
                                    adapter.notifyItemRangeChanged(position,adapter.problems.getSize());
                                    Log.d("DeleteProblem", "Position: " + position);
                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();
                }
            });

            // onclick listener for edit a problem
            editProblem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    FragmentManager manager = adapter.activity.getSupportFragmentManager();
                    FragmentTransaction transaction =  manager.beginTransaction();
                    EditProblemFragment fragment = EditProblemFragment.newInstance(position);
                    transaction.replace(R.id.content, fragment);
                    transaction.commit();

                }
            });
        }



        // set onClick listener for each problem, so they can be edited
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            FragmentManager manager = adapter.activity.getSupportFragmentManager();
            FragmentTransaction transaction =  manager.beginTransaction();
            Log.d("ProblemAdapter", "we are on index: " + position);
            RecordsFragment fragment = RecordsFragment.newInstance(position);
            transaction.addToBackStack(null);
            transaction.replace(R.id.content, fragment);
            transaction.commit();
        }
    }
}

