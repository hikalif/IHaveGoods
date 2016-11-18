package cn.edu.xmu.nongge.ihavegoods.mvp.view;

import cn.edu.xmu.nongge.ihavegoods.entity.Quotation;

/**
 * Created by Yui on 2016/11/18.
 */
public interface IQuotationDetailPreview {
    void getQuotationDetail(Quotation quotation);
    void agreeQuotation();
}
