package cn.edu.xmu.nongge.ihavegoods.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.xmu.nongge.ihavegoods.R;


/**
 * Created by asus1 on 2016/10/22.
 */
public class GoodsReceiverDialog extends Dialog{
    //接口回调
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String name, String phone, String address);
    }

    private ICustomDialogEventListener onCustomDialogEventListener;

    Context mContext;
    public GoodsReceiverDialog (Context context, ICustomDialogEventListener onCustomDialogEventListener){
        super(context);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }
    public GoodsReceiverDialog (Context context, int theme, ICustomDialogEventListener onCustomDialogEventListener) {
        super(context, theme);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }

    private EditText etGoodsReceiverName;
    private EditText etGoodsReceiverPhone;
    private EditText etGoodsReceiverAddress;
    private Button btnCancel;
    private Button btnSubmit;

    private String name = "";
    private String phone = "";
    private String address = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_goods_receiver, null);
        this.setContentView(layout);

        etGoodsReceiverName = (EditText) findViewById(R.id.goods_receiver_name);
        etGoodsReceiverPhone = (EditText) findViewById(R.id.goods_receiver_phone);
        etGoodsReceiverPhone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etGoodsReceiverAddress = (EditText) findViewById(R.id.goods_receiver_address);
        btnCancel = (Button) findViewById(R.id.dialog_goods_receiver_cancel);
        btnSubmit = (Button) findViewById(R.id.dialog_goods_receiver_submit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etGoodsReceiverName.getText().toString();
                phone = etGoodsReceiverPhone.getText().toString();
                address = etGoodsReceiverAddress.getText().toString();
                onCustomDialogEventListener.customDialogEvent(name, phone, address);
                dismiss();
            }
        });
    }
}
