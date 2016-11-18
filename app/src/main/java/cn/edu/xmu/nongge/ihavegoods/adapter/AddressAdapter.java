package cn.edu.xmu.nongge.ihavegoods.adapter;

import android.content.Context;
import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.listener.OnItemClickListener;

/**
 * Created by asus1 on 2016/10/27.
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Address> addresses;

    private OnItemClickListener mOnItemClickListener = null;


    public AddressAdapter(Context context, List<Address> addresses)
    {
        this.inflater = LayoutInflater.from(context);
        this.addresses = addresses;
    }

    public void setAddressList(List<Address> addressList){
        this.addresses = addressList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_address, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAddressName.setText(addresses.get(position).getName());
        holder.tvAddressPhone.setText(addresses.get(position).getTelephone());
        holder.tvAddress1.setText(addresses.get(position).getAddress());

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return addresses.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClickListener onItemClickListener;

        public TextView tvAddressName;
        public TextView tvAddressPhone;
        public TextView tvAddress1;

        public ViewHolder(View view, OnItemClickListener listener){
            super(view);
            tvAddressName = (TextView)view.findViewById(R.id.tv_address_name);
            tvAddressPhone = (TextView)view.findViewById(R.id.tv_address_phone);
            tvAddress1 = (TextView)view.findViewById(R.id.tv_address_first);

            onItemClickListener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getLayoutPosition());
            }
        }
    }
}
