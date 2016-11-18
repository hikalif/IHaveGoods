package cn.edu.xmu.nongge.ihavegoods.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.xmu.nongge.ihavegoods.R;
import cn.edu.xmu.nongge.ihavegoods.utils.AMapUtil;


public class LocationActivity extends AppCompatActivity {
    MapView mMapView;
    AMapUtil aMapUtil;
    ImageView mLocationIv;

    //地图页的List
    ListView mMapLv;
    //搜索页的List
    ListView mSearchLv;
    EditText mSearchEt;
    TextView mSearchTv;
    ImageView mBackIv;

    LinearLayout mMapLl;
    RelativeLayout mSearchRl;
    int pageTag = 1; //虚拟页数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        mMapLl = (LinearLayout) findViewById(R.id.container_map_ll);
        mSearchRl = (RelativeLayout) findViewById(R.id.container_search_rl);
        mMapLv = (ListView) findViewById(R.id.map_lv);
        mSearchLv = (ListView) findViewById(R.id.search_lv);
        mSearchEt = (EditText) findViewById(R.id.loc_search_et);
        mSearchTv = (TextView) findViewById(R.id.loc_search_tv);
        mBackIv = (ImageView) findViewById(R.id.loc_back_iv);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，实现地图生命周期管理
        mMapView.onCreate(savedInstanceState);
        aMapUtil = AMapUtil.getInstance();
        mLocationIv = (ImageView) this.findViewById(R.id.mylocation_iv);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        //初始化地图
        aMapUtil.initMap(mMapView);
        aMapUtil.initLocation(this);
        aMapUtil.locationClient.setLocationListener(mLocationListener);
        aMapUtil.startLocation();

        aMapUtil.aMap.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                Log.i("AMap", "Center lat:"+cameraPosition.target.latitude+"  lon:"+cameraPosition.target.longitude);
                searchByPostion(cameraPosition.target);
            }
        });

        mLocationIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aMapUtil.startLocation();
            }
        });

        //初始化其他
        mSearchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    pageTag = 2;
                    changePage();
                    mSearchTv.setClickable(true);
                }
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchByKeyWord(editable.toString());
            }
        };
        mSearchEt.addTextChangedListener(textWatcher);

        mBackIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationActivity.this.finish();
            }
        });

        mSearchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = mSearchEt.getText().toString().trim();
                if (!word.equals("")) {
                    searchByKeyWord(word);
                }
            }
        });

        //点击map页的list
        mMapLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Map map = (Map)(adapterView.getAdapter().getItem(i));
                Intent intent = new Intent();
                intent.putExtra("id", (String)map.get("id"));
                intent.putExtra("address", (String)map.get("address"));
                setResult(100, intent);
                finish();
            }
        });

        mSearchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Map map = (Map)(adapterView.getAdapter().getItem(i));
                Intent intent = new Intent();
                intent.putExtra("id", (String)map.get("id"));
                intent.putExtra("address", (String)map.get("address"));
                setResult(100, intent);
                finish();
            }
        });
    }

    /**
     * 根据位置搜索POI
     */
    private void searchByPostion(LatLng latLng) {
        //地名地址信息|商务住宅|购物服务|公司企业|公共设施|道路附属设施
        PoiSearch.Query query = new PoiSearch.Query("小区|学校|写字楼","","");
        query.setPageSize(15);
        query.setPageNum(1);
        PoiSearch poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                if (i == 1000 && poiResult!=null) {
                    List<PoiItem> poiItems = poiResult.getPois();
                    if (poiItems!=null && poiItems.size()>0) {
                        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
                        for (PoiItem item: poiItems) {
                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map.put("id", item.getPoiId());
                            map.put("name", item.getTitle());
                            map.put("address", item.getSnippet());
                            data.add(map);
                        }
                        SimpleAdapter adapter = new SimpleAdapter(LocationActivity.this, data, R.layout.layout_poi_item,
                                new String[] { "name", "address"}, new int[]{R.id.item_poiname, R.id.item_address});
                        mMapLv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(latLng.latitude, latLng.longitude), 1000));
        poiSearch.searchPOIAsyn();
    }

    /**
     * 根据关键字搜索
     * @param word
     */
    private void searchByKeyWord(String word) {
        InputtipsQuery query = new InputtipsQuery(word, "");
        Inputtips inputtips = new Inputtips(this, query);
        inputtips.setInputtipsListener(new Inputtips.InputtipsListener() {
            @Override
            public void onGetInputtips(List<Tip> list, int i) {
                Log.i("AMap", "size:"+list.size());
                if (i == 1000) {
                    if (list!=null && list.size()>0) {
                        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
                        for (Tip item: list) {
                            HashMap<String, Object> map = new HashMap<String, Object>();
                            map.put("id", item.getPoiID());
                            map.put("name", item.getName());
                            map.put("address", item.getAddress());
                            Log.i("AMap", "name:"+item.getName());
                            data.add(map);
                        }
                        SimpleAdapter adapter = new SimpleAdapter(LocationActivity.this, data, R.layout.layout_poi_item,
                                new String[] { "name", "address"}, new int[]{R.id.item_poiname, R.id.item_address});
                        mSearchLv.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                }
            }
        });
        inputtips.requestInputtipsAsyn();
    }

    private void changePage() {
        if (pageTag == 1) {
            mMapLl.setVisibility(View.VISIBLE);
            mSearchRl.setVisibility(View.INVISIBLE);
            mSearchEt.setText(null);
            mSearchLv.setAdapter(null);
        } else if (pageTag == 2){
            mMapLl.setVisibility(View.INVISIBLE);
            mSearchRl.setVisibility(View.VISIBLE);
        }
    }


    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    double lat = aMapLocation.getLatitude();
                    double lon = aMapLocation.getLongitude();
                    Log.i("AMap", "lat:"+lat+"  lon:"+lon);
                    LatLng latLng = new LatLng(lat, lon);
                    aMapUtil.moveToLocation(latLng);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError","location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    /**
     * 拦截返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pageTag == 2) {
                mSearchEt.clearFocus();
                mSearchTv.setClickable(false);
                pageTag = 1;
                changePage();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }

}
