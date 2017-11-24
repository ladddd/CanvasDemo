package com.ladddd.canvasdemo.parallax;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 陈伟达 on 2017/11/24.
 */

public abstract class ParallaxViewHolder extends RecyclerView.ViewHolder implements ParallaxImageView.ParallaxImageListener{

    private ParallaxImageView targetImageView;

    public abstract int getParallaxImageId();

    public ParallaxViewHolder(View itemView) {
        super(itemView);

        targetImageView = itemView.findViewById(getParallaxImageId());
        if (targetImageView != null) {
            targetImageView.setListener(this);
        }
    }

    @Override
    public TranslationInfo requireValuesForTranslate() {
        return null;
    }
}
