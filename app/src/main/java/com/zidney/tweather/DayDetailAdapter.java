package com.zidney.tweather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zidney.tweather.model.Hour;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class DayDetailAdapter extends RecyclerView.Adapter<DayDetailAdapter.HourViewHolder> {
    List<Hour> Hlist;

    public DayDetailAdapter(List<Hour> HourList) { this.Hlist = HourList; }


    @NonNull
    @Override
    public DayDetailAdapter.HourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_detail, parent, false);
        DayDetailAdapter.HourViewHolder hourViewHolder = new DayDetailAdapter.HourViewHolder(v);
        return hourViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayDetailAdapter.HourViewHolder holder, int position) {

        String hourd = Hlist.get(position).getTime();
        SimpleDateFormat fromAPIh = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat myformath = new SimpleDateFormat("HH:mm");
        String reformath = null;

        try {
            reformath = myformath.format(fromAPIh.parse(hourd));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.tvdtemp.setText(Double.toString(Hlist.get(position).getTemp_c())+" ℃");
        holder.tvdwind.setText(Double.toString(Hlist.get(position).getWind_kph())+" kph");
        holder.tvdcondi.setText(Hlist.get(position).getCondition().getText());
        holder.tvdprecip.setText(Double.toString(Hlist.get(position).getPrecip_mm())+" mm");
        holder.tvhour.setText(reformath);
        Glide.with(holder.itemView.getContext())
                .load("http:" + Hlist.get(position).getCondition().getIcon())
                .into(holder.imagedetail);
    }

    @Override
    public int getItemCount() {
        return Hlist.size();
    }

    public class HourViewHolder extends RecyclerView.ViewHolder {
        public TextView tvhour, tvdtemp, tvdprecip, tvdwind, tvdcondi;
        public ImageView imagedetail;

        public HourViewHolder(View itemView){
            super(itemView);
            tvhour = (TextView) itemView.findViewById(R.id.tv_hours);
            tvdtemp = (TextView) itemView.findViewById(R.id.tv_dtempc2);
            tvdcondi = (TextView) itemView.findViewById(R.id.tv_dcondi);
            tvdprecip = (TextView) itemView.findViewById(R.id.tv_dprecip2);
            tvdwind = (TextView) itemView.findViewById(R.id.tv_dwind2);
            imagedetail = itemView.findViewById(R.id.detail_image);
        }
    }
}
