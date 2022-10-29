package com.example.newapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    Context context;
    ArrayList id , title, ex1 , ex2 , ex3 , ex4;


    CustomAdapter(Context context , ArrayList id ,ArrayList title ,ArrayList ex1 ,ArrayList ex2 ,ArrayList ex3 ,ArrayList ex4){
        this.context = context;
        this.id = id;
        this.title = title;
        this.ex1 = ex1;
        this.ex2 = ex2;
        this.ex3 = ex3;
        this.ex4 = ex4;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view =  layoutInflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(String.valueOf(title.get(position)));
        //holder.color.setText(String.valueOf(color.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , Update_Activity.class);
                intent.putExtra("id" , String.valueOf(id.get(position)));
                intent.putExtra("title" , String.valueOf(title.get(position)));
                intent.putExtra("ex1" , String.valueOf(ex1.get(position)));
                intent.putExtra("ex2" , String.valueOf(ex2.get(position)));
                intent.putExtra("ex3" , String.valueOf(ex3.get(position)));
                intent.putExtra("ex4" , String.valueOf(ex4.get(position)));

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title ,color;

        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.PlanTitle);
            //color = itemView.findViewById(R.id.Color);

            mainLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
