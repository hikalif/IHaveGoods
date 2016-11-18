package cn.edu.xmu.nongge.ihavegoods.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.edu.xmu.nongge.ihavegoods.R;


/**
 * Created by asus1 on 2016/10/27.
 */
public class NewAddressActivity extends AppCompatActivity {

    private EditText etAddressName;
    private EditText etAddressPhone;
    private EditText etAddress1;
    private EditText etAddress2;
    private Button btn_submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaddress);

        etAddressName = (EditText) findViewById(R.id.address_name);
        etAddressPhone = (EditText) findViewById(R.id.address_phone);
        etAddress1 = (EditText) findViewById(R.id.address_first);
        etAddress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewAddressActivity.this, LocationActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        etAddress2 = (EditText) findViewById(R.id.address_second);

        btn_submit = (Button) findViewById(R.id.btn_address_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String address1 = data.getStringExtra("address");
        etAddress1.setText(address1);
    }
}
