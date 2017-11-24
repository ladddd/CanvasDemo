package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by 陈伟达 on 2017/11/23.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.MyViewHolder> {

    String[] urls = new String[] {
            "https://www.hitehranhostel.com/wp-content/uploads/2017/02/3-1.jpg",
            "http://blog.making-pictures.co.uk/wp-content/uploads/2013/08/iran-air_cover.jpg",
            "https://s-media-cache-ak0.pinimg.com/originals/bb/a1/50/bba150244b140991714c17886e89fd2a.jpg",
            "https://images.fineartamerica.com/images-medium-large-5/go-iran-lion-sun-semmick-photo.jpg",
            "https://images.fineartamerica.com/images-medium-large-5/dasht-e-kavir-desert-at-night-iran-science-photo-library.jpg",
            "https://ae01.alicdn.com/kf/HTB1B.x_SVXXXXb7XpXXq6xXFXXX1/-font-b-World-b-font-Famous-City-Map-TEHRAN-font-b-Iran-b-font-Print.jpg",
            "http://www.worldfortravel.com/wp-content/uploads/2014/04/Beautiful-Mosque-of-Iran.jpg",
            "http://www.wonderiran.com/wp-content/uploads/2016/06/Kish-island-southern-Iran-the-great-pier.jpg",
            "https://cdn-3b20.kxcdn.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/f/r/front_1060_2.jpg"
    };

    private RecyclerInfoDelegate mDelegate;

    public TestAdapter(RecyclerInfoDelegate delegate) {
        mDelegate = delegate;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mDelegate.getContext()).inflate(R.layout.test_item, null);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Glide.with(mDelegate.getContext().getApplicationContext()).load(urls[position]).asBitmap()
                .placeholder(R.drawable.image_placeholder).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        View contentView;
        ParallelImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            contentView = itemView;
            imageView = contentView.findViewById(R.id.image);
            imageView.setRecyclerInfoDelegate(new ParallelImageView.RecyclerInfoDelegate() {
                @Override
                public Rect getLocation() {
                    return mDelegate.getLocation();
                }
            });
        }
    }

    public interface RecyclerInfoDelegate {
        Rect getLocation();
        Context getContext();
    }
}
