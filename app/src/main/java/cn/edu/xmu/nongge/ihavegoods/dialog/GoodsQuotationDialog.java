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
public class GoodsQuotationDialog extends Dialog {

    //接口回调
    public interface ICustomDialogEventListener {
        public void customDialogEvent(String value);
    }

    private ICustomDialogEventListener onCustomDialogEventListener;

    Context mContext;
    public GoodsQuotationDialog (Context context, ICustomDialogEventListener onCustomDialogEventListener){
        super(context);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }
    public GoodsQuotationDialog (Context context, int theme, ICustomDialogEventListener onCustomDialogEventListener) {
        super(context, theme);
        mContext = context;
        this.onCustomDialogEventListener = onCustomDialogEventListener;
    }

    private EditText etGoodsQuotation;
    private Button btnCancel;
    private Button btnSubmit;

    private String size = "点击选择>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_goods_quotation, null);
        this.setContentView(layout);

        etGoodsQuotation = (EditText) findViewById(R.id.dialog_goods_quotation);
        etGoodsQuotation.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        btnCancel = (Button) findViewById(R.id.dialog_goods_quotation_cancel);
        btnSubmit = (Button) findViewById(R.id.dialog_goods_quotation_submit);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCustomDialogEventListener.customDialogEvent("点击选择>");
                dismiss();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                size = etGoodsQuotation.getText().toString();
                onCustomDialogEventListener.customDialogEvent(size);
                dismiss();
            }
        });
    }
}
