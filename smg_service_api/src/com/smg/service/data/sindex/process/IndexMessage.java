package com.smg.service.data.sindex.process;

import java.util.ArrayList;
import java.util.List;


/**
 * 该类用于给定一个舒适度值返回该值下的message
 * 86—88，4级 
 * 人体感觉很热，极不适应，希注意防暑降温，以防中暑；
 * 80—85，3级
 * 人体感觉炎热，很不舒适，希注意防暑降温；
 * 76—79，2级
 * 人体感觉偏热，不舒适，可适当降温；
 * 71—75，1级
 * 人体感觉偏暖，较为舒适；
 * 59—70，0级
 * 人体感觉最为舒适，最可接受；
 * 51—58，-1级
 * 人体感觉略偏凉，较为舒适；
 * 39—50，-2级
 * 人体感觉较冷(清凉)，不舒适，请注意保暖；
 * 26—38，-3级
 * 人体感觉很冷，很不舒适，希注意保暖防寒；
 * <25，-4级
 * 人体感觉寒冷，极不适应，希注意保暖防寒，防止冻伤。
 * 
 * */
public class IndexMessage {
	private double ssd;//舒适度
	
	List<String> messageList = new ArrayList<String>();
	public IndexMessage(){
		this.messageList.add("人体感觉寒冷，极不适应，希注意保暖防寒，防止冻伤。");
		this.messageList.add("人体感觉很冷，很不舒适，希注意保暖防寒.");
		this.messageList.add("人体感觉较冷(清凉)，不舒适，请注意保暖.");
		this.messageList.add("人体感觉略偏凉，较为舒适；");
		this.messageList.add("人体感觉最为舒适，最可接受；");
		this.messageList.add("人体感觉偏暖，较为舒适；");
		this.messageList.add("人体感觉偏热，不舒适，可适当降温；");
		this.messageList.add("人体感觉炎热，很不舒适，希注意防暑降温；");
		this.messageList.add("人体感觉很热，极不适应，希注意防暑降温，以防中暑；");
		
	}
	
	public String getMessage(double ssd){
		this.ssd = ssd;
		if(ssd<=25)
			return "-4级，"+messageList.get(0);
		else if(ssd<=38)
			return "-3级，"+messageList.get(1);
		else if(ssd<=50)
			return "-2级，"+messageList.get(2);
		else if(ssd<=58)
			return "-1级，"+messageList.get(3);
		else if(ssd<=70)
			return "0级，"+messageList.get(4);
		else if(ssd<=75)
			return "1级，"+messageList.get(5);
		else if(ssd<=79)
			return "2级，"+messageList.get(6);
		else if(ssd<=85)
			return "3级，"+messageList.get(7);
		else
			return "4级，"+messageList.get(8);
		
	}

}
