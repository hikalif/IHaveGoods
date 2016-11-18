package cn.edu.xmu.nongge.ihavegoods.utils;

import android.content.Context;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.poisearch.PoiSearch;

/**
 * Created by ZY on 2016/11/14.
 */

public class AMapUtil {
    //地图模块
    public MapView mapView;
    public AMap aMap;
    public UiSettings uiSettings;

    //定位模块
    //声明AMapLocationClient类对象
    public AMapLocationClient locationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption locationOption = null;
    //定位图标
    public Marker locationMarker = null;
    public int zoomSize = 30;


    public static AMapUtil getInstance() {
        return new AMapUtil();
    }

    /**
     * 初始化地图
     * @param mapView
     */
    public void initMap(MapView mapView) {
        this.mapView = mapView;
        aMap = mapView.getMap();
        uiSettings = aMap.getUiSettings();

        //缩放按钮
        uiSettings.setZoomControlsEnabled(false);
        //倾斜手势
        uiSettings.setTiltGesturesEnabled(false);
        //旋转手势
        uiSettings.setRotateGesturesEnabled(false);
        //实时路况图
        aMap.setTrafficEnabled(false);

        aMap.moveCamera(CameraUpdateFactory.zoomTo(zoomSize));
    }

    /**
     * 初始化定位
     * @param context
     */
    public void initLocation(Context context) {
        //初始化定位
        locationClient = new AMapLocationClient(context);
        locationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        //该方法默认为false。
        locationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        locationOption.setOnceLocationLatest(true);
        locationClient.setLocationOption(locationOption);
    }

    /**
     * 开始定位
     */
    public void startLocation() {
        if (locationClient != null) {
            locationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    public void stopLocation() {
        if (locationClient != null && locationClient.isStarted()) {
            locationClient.stopLocation();
        }
    }

    /**
     * 更新地图中心点
     * @param latLng
     */
    public void moveToLocation(LatLng latLng) {
//        //添加Marker显示定位位置
//        if (locationMarker == null) {
//            //如果是空的添加一个新的,icon方法就是设置定位图标，可以自定义
//            locationMarker = aMap.addMarker(new MarkerOptions()
//                    .position(latLng)
//                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker)));
//        } else {
//            //已经添加过了，修改位置即可
//            locationMarker.setPosition(latLng);
//        }

        //然后可以移动到定位点,使用animateCamera就有动画效果
        aMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomSize));
    }
}
