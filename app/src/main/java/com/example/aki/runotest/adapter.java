package com.example.aki.runotest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.userHolder> {
    private final List<user> Users;
    public adapter(List<user> Users) {
        this.Users=Users;
    }

    @NonNull
    @Override
    public userHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new userHolder(itemView);    }

    @Override
    public void onBindViewHolder(@NonNull userHolder holder, int position) {
        user u=Users.get(position);
        holder.type.setText(u.getType());
        holder.date.setText(u.getDate());
        String time="min:"+(Integer.parseInt(u.getTime()))/60+"Sec:"+(Integer.parseInt(u.getTime()))%60;
        holder.time.setText(time);
        holder.number.setText(u.getNumber());
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public class userHolder extends RecyclerView.ViewHolder {
        private TextView number,time,date,type;
        public userHolder(View itemView) {
            super(itemView);
            number=itemView.findViewById(R.id.number);
            time=itemView.findViewById(R.id.time);
            date=itemView.findViewById(R.id.date);
            type=itemView.findViewById(R.id.type);

        }
    }
}
