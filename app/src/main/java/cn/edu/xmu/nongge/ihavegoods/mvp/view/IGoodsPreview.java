package cn.edu.xmu.nongge.ihavegoods.mvp.view;


import java.util.List;

import cn.edu.xmu.nongge.ihavegoods.entity.Goods;
import cn.edu.xmu.nongge.ihavegoods.entity.Item;

/**
 * Created by Yui on 2016/11/7.
 */
public interface IGoodsPreview {
    void publishGoods();
    void getGoodsList(List<Goods> goodsList);
    void getItemList(List<Item> itemList);
}
