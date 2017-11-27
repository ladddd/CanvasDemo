package com.ladddd.canvasdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ladddd.canvasdemo.parallax.ParallaxViewHolder;

/**
 * Created by 陈伟达 on 2017/11/24.
 */

public class ParallaxLibAdapter extends RecyclerView.Adapter<ParallaxLibAdapter.MyViewHolder> {

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

    private Context mContext;

    public ParallaxLibAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ParallaxLibAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.parallax_item, null);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Glide.with(mContext.getApplicationContext()).load(urls[position]).asBitmap().listener(new RequestListener<String, Bitmap>() {
            @Override
            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                holder.getImageView().reuse();
                return false;
            }
        }).placeholder(R.drawable.image_placeholder).fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    class MyViewHolder extends ParallaxViewHolder {
        ImageView image;
        MyViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
        }

        @Override
        public int getParallaxImageId() {
            return R.id.image;
        }
    }
}
