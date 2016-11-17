package cn.edu.xmu.nongge.ihavegoods.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.edu.xmu.nongge.ihavegoods.R;


/**
 * Created by Yui on 2016/11/15.
 */
public class ItemGoods implements ItemContent {

    private Goods goods;

    public ItemGoods(Goods goods){
        this.goods = goods;
    }

    @Override
    public int getLayout() {
        return R.layout.item_goods;
    }

    @Override
    public boolean isClickAble() {
        return true;
    }

    @Override
    public View getView(Context mContext, View convertView, LayoutInflater inflater) {
        inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(getLayout(), null);

        //展示goods内容
        TextView tvGoodsDescription = (TextView) convertView.findViewById(R.id.goods_description);
        TextView tvGoodsQuotationCount = (TextView) convertView.findViewById(R.id.trucker_num);
        tvGoodsDescription.setText(goods.getDescription());
        tvGoodsQuotationCount.setText(goods.getQuotationCount()+"人");

        return convertView;
    }
}
