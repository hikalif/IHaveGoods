package cn.edu.xmu.nongge.ihavegoods.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import cn.edu.xmu.nongge.ihavegoods.R;

/**
 * Created by Yui on 2016/11/15.
 */
public class ItemQuotation implements ItemContent {

    private Quotation quotation;

    public ItemQuotation(Quotation quotation){
        this.quotation = quotation;
    }

    @Override
    public int getLayout() {
        return R.layout.item_quotation;
    }

    @Override
    public boolean isClickAble() {
        return true;
    }

    @Override
    public View getView(Context mContext, View convertView, LayoutInflater inflater) {
        inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(getLayout(), null);

        //展示quotation内容
        TextView tvTruckerName = (TextView) convertView.findViewById(R.id.quotation_trucker_name);
        TextView tvTruckType = (TextView) convertView.findViewById(R.id.quotation_trucker_type);
        TextView tvTruckQuotation = (TextView) convertView.findViewById(R.id.quotation_money);
        tvTruckerName.setText(quotation.getTruckerName());
        tvTruckType.setText(quotation.getTruckType());
        tvTruckQuotation.setText("¥"+quotation.getWaybillPrice());

        return convertView;
    }
}
