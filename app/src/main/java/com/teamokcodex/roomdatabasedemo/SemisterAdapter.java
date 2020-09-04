package com.teamokcodex.roomdatabasedemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SemisterAdapter extends RecyclerView.Adapter<SemisterAdapter.SemisterHolder> {

    private List<Semister> semisters=new ArrayList<>();
    private OnItemClickListner listner;

    class SemisterHolder extends RecyclerView.ViewHolder
    {
        private TextView item1;
        private TextView item2;
        private TextView item3;



        public SemisterHolder(@NonNull View itemView)
        {
            super(itemView);
            item1=itemView.findViewById(R.id.txt1);
            item2=itemView.findViewById(R.id.txt2);
            item3=itemView.findViewById(R.id.txt3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int positon=getAdapterPosition();
                    if(listner!=null && positon!=RecyclerView.NO_POSITION)
                    {
                        listner.OnItemClick(semisters.get(positon));
                    }

                }
            });
        }
    }


    @Override
    public int getItemCount()
    {

        return semisters.size();
    }
    public void setSemisters(List<Semister>semisters)
    {
        this.semisters=semisters;
        notifyDataSetChanged();
    }

    public Semister getSemisterAt(int position)
    {
        return semisters.get(position);
    }


    @NonNull
    @Override
    public SemisterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new SemisterHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull SemisterHolder holder, int position)
    {

        Semister cuurextSemister=semisters.get(position);
        holder.item1.setText(cuurextSemister.semister_name);
        holder.item2.setText(cuurextSemister.semister_credit);
        holder.item3.setText(cuurextSemister.semister_cgpa);





    }
    public  interface OnItemClickListner
    {

        void OnItemClick(Semister semister);
    }
    public void setOnItemClickListner(OnItemClickListner listner)
    {
        this.listner=listner;
    }


}
