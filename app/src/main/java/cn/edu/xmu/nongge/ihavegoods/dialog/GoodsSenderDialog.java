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
public class GoodsSenderDialog extends Dialog {

    //接口回调
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String name, String phone, String address);
    }

    private ICustomDialogEventListener onCustomDialogEventListener;

    Context mContext;
    public GoodsSenderDialog (Context context, ICustomDialogEventListener onCustomDialogEventListener){
        super(context);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }
    public GoodsSenderDialog (Context context, int theme, ICustomDialogEventListener onCustomDialogEventListener) {
        super(context, theme);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }

    private EditText etGoodsSenderName;
    private EditText etGoodsSenderPhone;
    private EditText etGoodsSenderAddress;
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
        View layout = inflater.inflate(R.layout.dialog_goods_sender, null);
        this.setContentView(layout);

        etGoodsSenderName = (EditText) findViewById(R.id.goods_sender_name);
        etGoodsSenderPhone = (EditText) findViewById(R.id.goods_sender_phone);
        etGoodsSenderPhone.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        etGoodsSenderAddress = (EditText) findViewById(R.id.goods_sender_address);
        btnCancel = (Button) findViewById(R.id.dialog_goods_sender_cancel);
        btnSubmit = (Button) findViewById(R.id.dialog_goods_sender_submit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etGoodsSenderName.getText().toString();
                phone = etGoodsSenderPhone.getText().toString();
                address = etGoodsSenderAddress.getText().toString();
                onCustomDialogEventListener.customDialogEvent(name, phone, address);
                dismiss();
            }
        });
    }
}
