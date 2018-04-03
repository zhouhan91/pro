package com.wemeCity.web.catering.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.wemeCity.common.controller.BaseControllerTester;
import com.wemeCity.web.catering.dto.CateringPayDTO;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhouhan
 * @create 2018-03-06 15:11
 * @desc
 **/
@RunWith(SpringJUnit4ClassRunner.class)
public class CateringControllerTester extends BaseControllerTester{

    @Test
    @Ignore
    public void testQueryCateringContactsList() throws Exception{
        testRestFul("/catering/contacts/queryCateringContactsList/15dfb1acfa0d4f02bfaf82312427236a/1", "get");
    }

    @Test
    @Ignore
    public void testInsertOrder() throws Exception{
        testRestFul("/catering/order/insertOrder", "post");
    }

    @Test
    public void testPay() throws Exception{
        CateringPayDTO payDTO = new CateringPayDTO();
        payDTO.setContactsId(115);
        payDTO.setLocationId(0);
        payDTO.setRestaurantId(88);
        payDTO.setUserKey("275a4b5a8e0c50bab3b8fe976975d248");
        payDTO.setPayType("offLine");
        payDTO.setGoodsIdStr("352");
        payDTO.setCountStr("2");
        payDTO.setOrderSource("program");
        payDTO.setDistributionNotes("9:01");
        payDTO.setRemark("hello");
        Gson gson = new Gson();
        testBody("/catering/order/pay", gson.toJson(payDTO));
    }

    @Test
    @Ignore
    public void testRestaurantInfo() throws Exception{
        testRestFul("/catering/restaurant/readCateringRestaurantInfo/34","get");
    }

}
