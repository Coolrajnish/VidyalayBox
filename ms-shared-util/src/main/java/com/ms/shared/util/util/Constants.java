package com.ms.shared.util.util;

import com.ms.shared.api.generic.GenericDTO;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public final class Constants {

    private Constants(){
        
    }
    public static final String WRONG_CREDENDIALS_CODE = "701";
    public static final String WRONG_CREDENDIALS_DESC = "User id with Password does not match. Please verify and re-enter";

    public static final String GET_REVENUE_SQL = "Select SJ.SYear as sales_year,SJ.SMonth as sales_month,SJ.sales_channel,Count(SJ.id) as order_count, Sum(SJ.amount) as amount from ((Select id, sales_channel,EXTRACT(YEAR FROM Pur_Date) as SYear, EXTRACT(MONTH FROM Pur_Date) as SMonth from			(Select  id, sales_channel, (purchase_date AT TIME ZONE 'UTC' AT TIME ZONE 'America/Los_Angeles') as Pur_Date from report_all_orders where client_id =1 and order_status != 'Cancelled') AS X) AS A Inner Join (Select Y.order_id, (Y.Amount * (CASE WHEN Z.usd_conv_rate IS NULL THEN 1 ELSE Z.usd_conv_rate END) )			 as amount From				(Select order_id,(item_price * quantity) as amount,currency from all_order_items) AS Y 			 Left Outer JOIN				(Select currency_code,usd_conv_rate as usd_conv_rate from Currency) AS Z 			 ON Y.currency=Z.currency_code) AS B	 		On A.id=B.order_id	) As SJ	Group By 	 SJ.SYear,SJ.SMonth,SJ.sales_channel";

    
    //FILTER Constant
    public static final String FILTER_YEAR = "FILTER_YEAR";
    public static final String FILTER_QUARTER = "FILTER_QUARTER";
    public static final String FILTER_MONTH = "FILTER_MONTH";
    
    public static final String FILTER_SALES_CHANNEL = "FILTER_SALES_CHANNEL";

    public static final String FILTER_PRODUCT_COLLECTION = "FILTER_PRODUCT_COLLECTION";
    public static final String FILTER_PRODUCT_CATEGORY = "FILTER_PRODUCT_CATEGORY";
    public static final String FILTER_PRODUCT_SUB_CATEGORY = "FILTER_PRODUCT_SUB_CATEGORY";
    public static final String FILTER_SKU = "FILTER_SKU";
    public static final String FILTER_REGION = "FILTER_REGION";
    public static final String FILTER_SUB_REGION = "FILTER_SUB_REGION";

	public static final String FILTER_COUNTRY = "FILTER_COUNTRY";
    public static final String FILTER_ORDER_STATUS = "FILTER_ORDER_STATUS";
	public static final String[] CURRENT_ENABLE_COUNTRY = { "US","CA","UK","DE","FR","IT","SP"};
    public static final String FILTER_POS = "FILTER_POS";
    
    @Getter
    public static Map<String, GenericDTO> filterMAP = new HashMap<>();
    		
}
