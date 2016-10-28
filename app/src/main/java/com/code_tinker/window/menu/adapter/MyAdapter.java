package com.code_tinker.window.menu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.code_tinker.window.menu.R;
import com.code_tinker.window.menu.info.ListEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by mj
 * on 2016/10/28.
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int IMAGE = 1;
    private final int LIST = 2;

    private Context context;
    private List<ListEntity> data;
    public LayoutInflater inflater;

    public MyAdapter(Context context, List<ListEntity> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).layoutType == 0) {
            return IMAGE;
        } else {
            return LIST;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == IMAGE) {
            View itemView = inflater.inflate(R.layout.item_image, null);
            return new ItemImageViewHolder(itemView);
        } else {
            View itemView = inflater.inflate(R.layout.item_list, null);
            return new ItemListViewHolder(itemView);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemListViewHolder) {
            ItemListViewHolder itemListViewHolder = (ItemListViewHolder) holder;
            ListEntity entity = data.get(position);
            itemListViewHolder.tvName.setText(entity.name);
            itemListViewHolder.tvDate.setText(entity.date);
            itemListViewHolder.tvDesc.setText(entity.content);

            itemListViewHolder.avatar.setImageURI(Uri.parse(entity.avatarUrl));
            itemListViewHolder.descImage.setImageURI(Uri.parse(entity.descUrl));

        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class ItemImageViewHolder extends RecyclerView.ViewHolder {
        public ItemImageViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class ItemListViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView avatar, descImage;
        TextView tvName, tvDate, tvDesc;

        public ItemListViewHolder(View itemView) {
            super(itemView);
            avatar = (SimpleDraweeView) itemView.findViewById(R.id.avatar_image);
            descImage = (SimpleDraweeView) itemView.findViewById(R.id.desc_image);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvDate = (TextView) itemView.findViewById(R.id.date);
            tvDesc = (TextView) itemView.findViewById(R.id.desc);
        }
    }


}
