package cn.edu.xmu.nongge.ihavegoods.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;


import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.activity.NewGoodsActivity;
import cn.edu.xmu.nongge.ihavegoods.activity.QuotationDetailsActivity;
import cn.edu.xmu.nongge.ihavegoods.adapter.GoodsAdapter;
import cn.edu.xmu.nongge.ihavegoods.adapter.ItemAdapter;
import cn.edu.xmu.nongge.ihavegoods.decoration.RecyclerViewDivider;
import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.entity.ItemContent;
import cn.edu.xmu.nongge.ihavegoods.entity.ItemGoods;
import cn.edu.xmu.nongge.ihavegoods.entity.ItemQuotation;
import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.listener.OnItemClickListener;
import cn.edu.xmu.nongge.ihavegoods.mvp.presenter.GoodsPresenter;
import cn.edu.xmu.nongge.ihavegoods.mvp.presenter.WaybillPresenter;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IGoodsPreview;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IQuotationDetailPreview;

/**
 * Created by asus1 on 2016/10/26.
 */
public class GoodsFragment extends Fragment implements IGoodsPreview {

    private GoodsPresenter mGoodsPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Goods> goodsList = new ArrayList();
    private List<Item> itemList = new ArrayList<Item>();
    private List<ItemContent> itemContentList = new ArrayList<ItemContent>();

    private RecyclerView recyclerView;
    private GoodsAdapter adapter;
    private ItemAdapter itemAdapter;
    private LinearLayoutManager linearLayoutManager;

    private FloatingActionButton fab;

    public GoodsFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods, container, false);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), NewGoodsActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        requestPreview();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 6000);
            }
        });

        mGoodsPresenter = new GoodsPresenter(this);

        //initRecyclerView(view);
        initRecyclerViewItem(view);
        requestPreview();

        return view;
    }

    private void initRecyclerView(View view){
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.goods_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new GoodsAdapter(getActivity(), goodsList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
    }

    private void initRecyclerViewItem(View view){
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView = (RecyclerView) view.findViewById(R.id.goods_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(getActivity(), itemContentList);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(getActivity(), LinearLayoutManager.HORIZONTAL));
        itemAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                //operate each item
                if(itemContentList.get(postion).getLayout() == R.layout.item_goods){
                    Toast.makeText(getActivity(), "goods", Toast.LENGTH_SHORT).show();
                }
                else if(itemContentList.get(postion).getLayout() == R.layout.item_quotation){
                    //Toast.makeText(getActivity(), "quotation", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), QuotationDetailsActivity.class);
                    intent.putExtra("waybillid", itemContentList.get(postion).getId());
                    Log.d("cgf", "waybillid"+itemContentList.get(postion).getId());
                    startActivity(intent);
                }
            }
        });
    }

    private void requestPreview(){
        JSONObject request = new JSONObject();
        request.put("employerid", 1);
        //mGoodsPresenter.getGoodsList(request.toString());
        mGoodsPresenter.getItemList(request.toString());
    }

    @Override
    public void publishGoods() {

    }

    @Override
    public void getGoodsList(List<Goods> goodsList) {
        Log.i("Http", "个数" + goodsList.size() + "");
        adapter.setGoodsList(goodsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void getItemList(List<Item> itemList) {
        itemContentList.removeAll(itemContentList);
        Log.i("Http", "个数" + itemList.size() + "");
        for(int i = 0; i < itemList.size(); i++)
        {
            Goods tempGoods = new Goods();
            tempGoods.setDescription(itemList.get(i).getDescription());
            tempGoods.setId(itemList.get(i).getId());
            tempGoods.setType(itemList.get(i).getType());
            tempGoods.setQuotationCount(itemList.get(i).getQuotation_count());
            ItemGoods itemGoods = new ItemGoods(tempGoods);
            itemContentList.add(itemGoods);

            Log.d("cgf", "quotation size:" + itemList.get(i).getQuotationList().size());
            if(itemList.get(i).getQuotationList() != null){
                for(int j = 0; j < itemList.get(i).getQuotationList().size(); j++)
                {
                    ItemQuotation itemQuotation = new ItemQuotation(itemList.get(i).getQuotationList().get(j));
                    itemContentList.add(itemQuotation);
                }
            }
            else{
                Log.d("cgf", "quotation is none");
            }
        }
        itemAdapter.setItemList(itemContentList);
        itemAdapter.notifyDataSetChanged();
    }


    /*private List<Goods> getGoodsList()
    {
        Goods goods1 = new Goods();
        goods1.setDescription("平和红柚，今早刚刚采摘下来。");
        Goods goods2 = new Goods();
        goods2.setDescription("东北大米，产自黑龙江。");

        goodsList = new ArrayList<Goods>();
        goodsList.add(goods1);
        goodsList.add(goods2);

        return goodsList;
    }*/
}
