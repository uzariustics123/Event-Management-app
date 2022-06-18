package com.macas.nmsc.sict;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.moeidbannerlibrary.banner.BannerLayout;
import java.util.List;
import android.view.View;


public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.MyViewHolder>{
    
    
    private final Context context;
    
    private final List<String> urlList;
    private BannerLayout.OnBannerItemClickListener onBannerItemClickListener;
    public BannerAdapter(Context context, List<String> urlList){
        this.context = context;
        this.urlList = urlList;
        
    }
    public void setOnBannerItemClickListener(BannerLayout.OnBannerItemClickListener onBannerItemClickListener) {
        this.onBannerItemClickListener = onBannerItemClickListener;
    }
    
        @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_list_item, parent, false));
    }
        @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if (urlList == null || urlList.isEmpty())
            return;
        final int P = position % urlList.size();
        String url = urlList.get(P);
        ImageView img = holder.imageView;
        Glide.with(context).load(url)
        .into(img);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onBannerItemClickListener != null) {
                    onBannerItemClickListener.onItemClick(P);
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        if (urlList != null) {
           return urlList.size();
        }
       return 0;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}



