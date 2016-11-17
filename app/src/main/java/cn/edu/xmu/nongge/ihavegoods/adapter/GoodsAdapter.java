package cn.edu.xmu.nongge.ihavegoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.entity.Goods;

/**
 * Created by asus1 on 2016/10/27.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Goods> goodsList;

    public GoodsAdapter(Context context, List<Goods> goodsList)
    {
        this.inflater = LayoutInflater.from(context);
        this.goodsList = goodsList;
    }

    public void setGoodsList(List<Goods> goodsList){
        this.goodsList = goodsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_goods, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTruckerNum.setText("2");
        holder.tvGoodsDescription.setText(goodsList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvGoodsDescription;
        public TextView tvTruckerNum;
        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.goods_pic);
            tvGoodsDescription = (TextView) view.findViewById(R.id.goods_description);
            //tvTruckerNum = (TextView) view.findViewById(R.id.trucker_num);
        }
    }
}
