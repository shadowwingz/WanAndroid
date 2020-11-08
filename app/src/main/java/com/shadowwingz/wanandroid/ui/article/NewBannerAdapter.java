package com.shadowwingz.wanandroid.ui.article;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadowwingz.wanandroid.R;

class NewBannerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  public NewBannerAdapter() {
  }

  @NonNull
  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner, parent, false));
  }

  @Override
  public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

  }

  static class MyViewHolder extends RecyclerView.ViewHolder {

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      View viewById = itemView.findViewById(R.id.banner);
    }
  }

  @Override
  public int getItemCount() {
    return 5;
  }
}
