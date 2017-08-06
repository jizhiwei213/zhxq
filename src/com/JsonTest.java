package com;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTest
{
	public static void main(String[] args)
	{
		String sql ="{\"responseHeader\":{\"status\":0,\"QTime\":1},\"terms\":{\"hotword\":[\"蔬菜\",2,\"其他\",1,\"卤肉\",1,\"味精\",1]}}";
		//String sql ="{\"responseHeader\":{\"status\":0,\"QTime\":17,\"params\":{\"q\":\"*:*\",\"facet.field\":\"brand_names\",\"indent\":\"true\",\"rows\":\"2\",\"wt\":\"json\",\"facet\":\"true\",\"_\":\"1441089247234\"}},\"response\":{\"numFound\":19,\"start\":0,\"docs\":[{\"sku_detail_id\":\"00000000000000000000000000000000009\",\"category_id\":\"00000000000000000000000000000000009\",\"category_name\":\"面粉\",\"category_desc\":\"荞麦粉、燕麦片、黑麦粉、黑小麦粉、小米粉、高粱粉、小麦粉、大麦粉、青稞粉、玉米糁、玉米粉、燕麦粉、杂面粉、大米粉、糯米粉、绿豆粉\",\"brand_ids\":[\"00000000000000000000000000000000002\",\"00000000000000000000000000000000003\",\"00000000000000000000000000000000004\",\"00000000000000000000000000000000005\",\"00000000000000000000000000000000019\",\"00000000000000000000000000000000021\",\"00000000000000000000000000000000023\"],\"brand_names\":[\"金龙鱼\",\"中粮\",\"五丰\",\"北大荒\",\"龙升源\",\"臻味\",\"香雪\"],\"brand_descs\":[\"金龙鱼\",\"中粮集团\",\"华润五丰\",\"北大荒\",\"龙升食品\",\"臻味坊食品\",\"中粮香雪\"],\"brand_logos\":[\"jinlongyu.png\",\"cofco.png\",\"wufeng.png\",\"beidahuang.png\",\"loamsun.png\",\"zhenwei.png\",\"xiangxue.png\"],\"attr_ids\":[\"00000000000000000000000000000000004\"],\"attr_names\":[\"重量\"],\"_version_\":1511006144112361500},{\"sku_detail_id\":\"00000000000000000000000000000000010\",\"category_id\":\"00000000000000000000000000000000010\",\"category_name\":\"大米\",\"category_desc\":\"大米、高寒米、五常米、香米、梗米\",\"brand_ids\":[\"00000000000000000000000000000000002\",\"00000000000000000000000000000000003\",\"00000000000000000000000000000000004\",\"00000000000000000000000000000000005\",\"00000000000000000000000000000000019\",\"00000000000000000000000000000000022\"],\"brand_names\":[\"金龙鱼\",\"中粮\",\"五丰\",\"北大荒\",\"龙升源\",\"福临门\"],\"brand_descs\":[\"金龙鱼\",\"中粮集团\",\"华润五丰\",\"北大荒\",\"龙升食品\",\"中粮福临门\"],\"brand_logos\":[\"jinlongyu.png\",\"cofco.png\",\"wufeng.png\",\"beidahuang.png\",\"loamsun.png\",\"fulinmen.png\"],\"attr_ids\":[\"00000000000000000000000000000000012\"],\"attr_names\":[\"重量\"],\"_version_\":1511006144144867300}]},\"facet_counts\":{\"facet_queries\":{},\"facet_fields\":{\"brand_names\":[\"北大荒\",7,\"厨邦\",7,\"太太乐\",6,\"福临门\",5,\"美味鲜\",5,\"龙升源\",5,\"中粮\",4,\"恒顺\",4,\"春城老百家\",4,\"海天\",4,\"臻味\",4,\"莲花\",4,\"金龙鱼\",4,\"双汇\",3,\"李锦记\",3,\"王守义\",3,\"自然鲜\",3,\"鲁花\",3,\"五丰\",2,\"老干妈\",2,\"香雪\",2,\"中盐\",1,\"五谷道场\",1,\"甘汁园\",1,\"统一\",1,\"陈克明\",1]},\"facet_dates\":{},\"facet_ranges\":{},\"facet_intervals\":{},\"facet_heatmaps\":{}}}";
		JSONObject objec = JSONObject.fromObject(sql);
		JSONObject facetObj = JSONObject.fromObject(objec.get("facet_counts").toString());
		JSONArray js = JSONArray.fromObject(((JSONObject)((JSONObject)objec.get("facet_counts")).get("facet_fields")).get("brand_names"));
		Object[] obj = js.toArray();
		for(int i=0;i<obj.length;i++)
		{
			System.out.println(obj[i]);
		}
		System.out.println("::::::::::::::::::");
	}
}
