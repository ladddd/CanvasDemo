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
        TranslationInfo translationInfo = new TranslationInfo();
        if (itemView.getParent() != null) {
            int[] itemPosition = new int[2];
            itemView.getLocationOnScreen(itemPosition);

            int[] recyclerPosition = new int[2];
            ((RecyclerView) itemView.getParent()).getLocationOnScreen(recyclerPosition);

            translationInfo.setItemTop(itemPosition[1]);
            translationInfo.setRecyclerViewTop(itemPosition[1]);
            translationInfo.setRecyclerViewHeight(((RecyclerView) itemView.getParent()).getMeasuredHeight());
        }

        return translationInfo;
    }

    public ParallaxImageView getImageView() {
        return targetImageView;
    }
}
