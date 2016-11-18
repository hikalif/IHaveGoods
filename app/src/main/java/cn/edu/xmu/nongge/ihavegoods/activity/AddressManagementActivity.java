package cn.edu.xmu.nongge.ihavegoods.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.adapter.AddressAdapter;
import cn.edu.xmu.nongge.ihavegoods.decoration.RecyclerViewDivider;
import cn.edu.xmu.nongge.ihavegoods.entity.Address;
import cn.edu.xmu.nongge.ihavegoods.listener.OnItemClickListener;
import cn.edu.xmu.nongge.ihavegoods.mvp.presenter.AddressPresenter;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IAddressPreview;

/**
 * Created by asus1 on 2016/10/27.
 */
public class AddressManagementActivity extends AppCompatActivity implements IAddressPreview {

    private List<Address> addressList = new ArrayList<>();

    private AddressPresenter mAddressPresenter;

    private RecyclerView recyclerView;
    private AddressAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    private Button btn_new;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addressmanagement);

        mAddressPresenter = new AddressPresenter(this);

        requestPreview();
        initRecyclerView();

        btn_new = (Button) findViewById(R.id.tbn_address_add);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddressManagementActivity.this, NewAddressActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initRecyclerView(){
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView = (RecyclerView) findViewById(R.id.address_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AddressAdapter(AddressManagementActivity.this, addressList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.HORIZONTAL));
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Address tempAddress = addressList.get(postion);
                Intent intent = new Intent();
                intent.putExtra("addressId", tempAddress.getId());
                intent.putExtra("addressName", tempAddress.getName());
                intent.putExtra("addressPhone", tempAddress.getTelephone());
                intent.putExtra("addressAddress", tempAddress.getAddress());
                setResult(0, intent);
                finish();
            }
        });
    }

    private void requestPreview(){
        JSONObject request = new JSONObject();
        request.put("employerid", 1);
        mAddressPresenter.getMyAddressList(request.toString());
    }

    @Override
    public void getMyAddressList(List<Address> addressList){
        Log.i("Http", "个数" + addressList.size() + "");
        this.addressList = addressList;
        adapter.setAddressList(addressList);
        adapter.notifyDataSetChanged();
    }



    //测试用
    /*private List<Address> getAddressList()
    {
        Address address1 = new Address();
        address1.setName("陈光凡");
        address1.setPhone("12312341234");
        address1.setAddress1("厦门市思明区学生公寓");
        address1.setAddress2("海韵2");

        Address address2 = new Address();
        address2.setName("王建国");
        address2.setPhone("31245675678");
        address2.setAddress1("厦门市思明区海韵教学楼");
        address2.setAddress2("科研1");

        addressList = new ArrayList<Address>();
        addressList.add(address1);
        addressList.add(address2);

        return addressList;
    }*/
}
