package cn.edu.xmu.nongge.ihavegoods.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Yui on 2016/11/15.
 */
public interface ItemContent {
    long getId();

    int getLayout();

    boolean isClickAble();

    View getView(Context mContext, View convertView, LayoutInflater inflater);
}
