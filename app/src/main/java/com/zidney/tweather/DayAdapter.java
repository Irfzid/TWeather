package com.zidney.tweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zidney.tweather.model.Forecastday;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {

    List<Forecastday> Dlist;
    private OnDayListener mOnDayListener;

    public DayAdapter(List<Forecastday> DayList, OnDayListener onDayListener)  {
        this.Dlist = DayList;
        this.mOnDayListener = onDayListener;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        DayViewHolder dayViewHolder = new DayViewHolder(v, mOnDayListener);
        return dayViewHolder;
    }

    @Override
    public void onBindViewHolder(DayAdapter.DayViewHolder holder, int position) {

        String daydetail = Dlist.get(position).getDate();
        SimpleDateFormat fromAPId = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myformatd = new SimpleDateFormat("dd MMM yyyy");
        SimpleDateFormat dayformatd= new SimpleDateFormat("EEEE");
        String reformatd = null;
        String reformatd2 = null;

        try {
            reformatd = dayformatd.format(fromAPId.parse(daydetail));
            reformatd2 = myformatd.format(fromAPId.parse(daydetail));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvCondition.setText(Dlist.get(position).getDay().getCondition().getText());
        holder.tvAvgtemp.setText(Double.toString(Dlist.get(position).getDay().getAvgtemp_c())+ " ℃");
        holder.tvDate.setText(reformatd2);
        holder.tvDay.setText(reformatd);
        Glide.with(holder.itemView.getContext())
                .load("http:" + Dlist.get(position).getDay().getCondition().getIcon())
                .into(holder.Vimg);
    }

    @Override
    public int getItemCount() {
        return Dlist.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tvDate, tvCondition, tvAvgtemp, tvDay;
        public ImageView Vimg;
        OnDayListener onDayListener;

        public DayViewHolder(View itemView, OnDayListener onDayListener) {
            super(itemView);
            tvDate = (TextView) itemView.findViewById(R.id.row_date);
            tvAvgtemp = (TextView) itemView.findViewById(R.id.row_avgtemp);
            tvCondition = (TextView) itemView.findViewById(R.id.row_condi);
            tvDay = (TextView) itemView.findViewById(R.id.row_day);
            Vimg = itemView.findViewById(R.id.row_image);
            this.onDayListener = onDayListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onDayListener.onDayClick(getAdapterPosition());

        }
    }
    public interface OnDayListener{
        void onDayClick(int position);
    }

    public void Clear(){
        Dlist.clear();
    }
}
