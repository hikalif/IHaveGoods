package cn.edu.xmu.nongge.ihavegoods.activity;

import android.app.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;
import cn.edu.xmu.nongge.ihavegoods.mvp.presenter.GoodsPresenter;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IGoodsPreview;

public class NewGoodsActivity extends AppCompatActivity implements IGoodsPreview {

    private GoodsPresenter mGoodsPresenter;

    private Goods goods = new Goods();
    private String carType = "厢型车";

    private TextView tvHide;
    private EditText etType;
    private EditText etDescription;
    private EditText etSize;
    private EditText etQuotation;
    private Spinner spCarType;
    private TextView tvOrderDate;
    private TextView tvDeliveryDate;
    private TextView tvSenderSet;
    private TextView tvSenderName;
    private TextView tvSenderPhone;
    private TextView tvSenderAddress;
    private TextView tvReceiverSet;
    private TextView tvReceiverName;
    private TextView tvReceiverPhone;
    private TextView tvReceiverAddress;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goods);

        mGoodsPresenter = new GoodsPresenter(this);

        tvHide = (TextView) findViewById(R.id.config_hidden);
        tvHide.requestFocus();

        etType = (EditText) findViewById(R.id.goods_type);
        etDescription = (EditText) findViewById(R.id.goods_description);
        etSize = (EditText) findViewById(R.id.goods_size);
        etQuotation = (EditText) findViewById(R.id.goods_quotation);

        spCarType = (Spinner) findViewById(R.id.goods_carType);
        spCarType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                carType = (String) spCarType.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tvOrderDate = (TextView) findViewById(R.id.form_date);
        tvOrderDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(NewGoodsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvOrderDate.setText(year+"-"+ (monthOfYear+1) +"-"+dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tvDeliveryDate = (TextView) findViewById(R.id.form_date2);
        tvDeliveryDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                new DatePickerDialog(NewGoodsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDeliveryDate.setText(year+"-"+ (monthOfYear+1) +"-"+dayOfMonth);
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tvSenderSet = (TextView) findViewById(R.id.sender_set);
        tvSenderName = (TextView) findViewById(R.id.sender_name);
        tvSenderPhone = (TextView) findViewById(R.id.sender_phone);
        tvSenderAddress = (TextView) findViewById(R.id.sender_address);
        tvSenderSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewGoodsActivity.this, AddressManagementActivity.class);
                startActivity(intent);
            }
        });

        tvReceiverSet = (TextView) findViewById(R.id.receiver_set);
        tvReceiverName = (TextView) findViewById(R.id.receiver_name);
        tvReceiverPhone = (TextView) findViewById(R.id.receiver_phone);
        tvReceiverAddress = (TextView) findViewById(R.id.receiver_address);
        tvReceiverSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewGoodsActivity.this, AddressManagementActivity.class);
                startActivity(intent);
            }
        });

        button = (Button) findViewById(R.id.btn_publish_goods);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods.setType(etType.getText().toString());
                goods.setDescription(etDescription.getText().toString());
                goods.setWeight(etSize.getText().toString());
                goods.setQuotation(etQuotation.getText().toString());
                goods.setTrucktypedemand(carType);
                try {
                    goods.setOrderdeadline(stringToDate(tvOrderDate.getText().toString()));
                    goods.setDeliverydeadline(stringToDate(tvDeliveryDate.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                goods.setShippingAddressid(1);
                goods.setReceiptAddressid(2);

                requestPreview();

                Intent intent = new Intent(NewGoodsActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void requestPreview(){
        JSONObject request = new JSONObject();
        request.put("type", goods.getType());
        request.put("description", goods.getDescription());
        request.put("weight", goods.getWeight());
        request.put("orderdeadline", goods.getOrderdeadline());
        request.put("deliverydeadline", goods.getDeliverydeadline());
        request.put("quotation", goods.getQuotation());
        request.put("trucktypedemand", goods.getTrucktypedemand());
        request.put("shippingAddressid", goods.getShippingAddressid());
        request.put("receiptAddressid", goods.getReceiptAddressid());
        request.put("employerid", 1);
        mGoodsPresenter.publishGoods(request.toString());
    }

    @Override
    public void publishGoods() {
        Toast.makeText(getApplicationContext(), "货源发布成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getGoodsList(List<Goods> goodsList) {

    }

    @Override
    public void getItemList(List<Item> itemList) {

    }

    public static Date stringToDate(String strDate) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(strDate);
        return date;
    }
}
