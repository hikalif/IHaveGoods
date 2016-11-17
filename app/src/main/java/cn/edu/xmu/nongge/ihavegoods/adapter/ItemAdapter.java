package cn.edu.xmu.nongge.ihavegoods.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.ItemContent;

/**
 * Created by Yui on 2016/11/15.
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private Context mContext;
    private List<ItemContent> list;
    private LayoutInflater mIflater;


    public ItemAdapter(Context mContext, List<ItemContent> list) {
        this.mContext = mContext;
        this.list = list;
    }
    int times =0;

    public void setItemList(List<ItemContent> list){
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder =
                new ViewHolder(list.get(viewType).getView(mContext, parent, mIflater));
        times++;
        Log.d("MyAdapter", "times:" + times);
        return holder;
    }

    /**
     * 每一个位置的item都作为单独一项来设置
     * viewType 设置为position
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

//        OrderContent content = list.get(position);
//        if(content instanceof ItemOrderTop){
//            return 0;
//        }
//        if(content instanceof ItemOrderBottom){
//            return 1;
//        }
//        return 2;
        return position;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 更新数据
     * @param orderContents
     */
    public void updateList(List<ItemContent> orderContents){
        this.list = orderContents;
        this.notifyDataSetChanged();
    }

    /**
     * 清除数据
     */
    public void clearList(){
        this.list.clear();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
