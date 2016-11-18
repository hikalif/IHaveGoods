package cn.edu.xmu.nongge.ihavegoods.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;
import cn.edu.xmu.nongge.ihavegoods.mvp.presenter.WaybillPresenter;
import cn.edu.xmu.nongge.ihavegoods.mvp.view.IQuotationDetailPreview;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * Created by Yui on 2016/11/18.
 */
public class QuotationDetailsActivity extends AppCompatActivity implements IQuotationDetailPreview{

    private WaybillPresenter mWaybillPresenter;

    private Quotation quotation = new Quotation();

    private MaterialRatingBar materialRatingBar;
    private TextView truckerName;
    private TextView truckerCredit;
    private TextView truckerOrderCount;
    private TextView quotationMoney;
    private TextView quotationTruckType;
    private TextView truckerPhone;
    private TextView quotationRemark;
    private Button btn_quotation_submit;
    private Button btn_quotation_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotation_details);

        mWaybillPresenter = new WaybillPresenter(this);

        Intent intent = getIntent();
        final long waybillid = intent.getLongExtra("waybillid", 0);
        requestDetailPreview(waybillid);

        btn_quotation_submit = (Button) findViewById(R.id.btn_quotation_submit);
        btn_quotation_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestReceiptPreview(waybillid);
                finish();
            }
        });
        btn_quotation_cancel = (Button) findViewById(R.id.btn_quotation_cancel);
        btn_quotation_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void requestReceiptPreview(long waybillid){
        JSONObject request = new JSONObject();
        request.put("waybillid", waybillid);
        mWaybillPresenter.agreeQuotation(request.toString());
    }

    private void requestDetailPreview(long waybillid)
    {
        JSONObject request = new JSONObject();
        request.put("waybillid", waybillid);
        mWaybillPresenter.getQuotationDetail(request.toString());
    }

    @Override
    public void getQuotationDetail(Quotation quotation) {
        this.quotation = quotation;
        materialRatingBar = (MaterialRatingBar) findViewById(R.id.m_ratingbar);
        truckerName = (TextView) findViewById(R.id.trucker_name);
        truckerCredit = (TextView) findViewById(R.id.trucker_credit);
        truckerOrderCount = (TextView) findViewById(R.id.trucker_order_count);
        quotationMoney = (TextView) findViewById(R.id.quotation_money);
        quotationTruckType = (TextView) findViewById(R.id.quotation_trucker_type);
        truckerPhone = (TextView) findViewById(R.id.quotation_trucker_phone);
        quotationRemark = (TextView) findViewById(R.id.quotation_trucker_remark);

        truckerName.setText(quotation.getTruckerName());
        truckerCredit.setText(quotation.getTruckerCredit()+"");
        materialRatingBar.setRating((float)quotation.getTruckerCredit());
        truckerOrderCount.setText(quotation.getTruckerOrdercount()+"单");
        quotationMoney.setText("¥" + quotation.getWaybillPrice());
        quotationTruckType.setText(quotation.getTruckCarplate() + " / " + quotation.getTruckType() + " / " + quotation.getTruckLoad() + "吨");
        truckerPhone.setText(quotation.getTruckerTelephone());
        quotationRemark.setText(quotation.getWaybillRemark());
    }

    @Override
    public void agreeQuotation() {
        Log.d("cgf", "quotation receipted");
    }
}
