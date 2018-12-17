package com.smg.service.data.sindex.process;

import java.util.ArrayList;
import java.util.List;


/**
 * �������ڸ���һ�����ʶ�ֵ���ظ�ֵ�µ�message
 * 86��88��4�� 
 * ����о����ȣ�������Ӧ��ϣע�����£��Է����
 * 80��85��3��
 * ����о����ȣ��ܲ����ʣ�ϣע�����£�
 * 76��79��2��
 * ����о�ƫ�ȣ������ʣ����ʵ����£�
 * 71��75��1��
 * ����о�ƫů����Ϊ���ʣ�
 * 59��70��0��
 * ����о���Ϊ���ʣ���ɽ��ܣ�
 * 51��58��-1��
 * ����о���ƫ������Ϊ���ʣ�
 * 39��50��-2��
 * ����о�����(����)�������ʣ���ע�Ᵽů��
 * 26��38��-3��
 * ����о����䣬�ܲ����ʣ�ϣע�Ᵽů������
 * <25��-4��
 * ����о����䣬������Ӧ��ϣע�Ᵽů��������ֹ���ˡ�
 * 
 * */
public class IndexMessage {
	private double ssd;//���ʶ�
	
	List<String> messageList = new ArrayList<String>();
	public IndexMessage(){
		this.messageList.add("����о����䣬������Ӧ��ϣע�Ᵽů��������ֹ���ˡ�");
		this.messageList.add("����о����䣬�ܲ����ʣ�ϣע�Ᵽů����.");
		this.messageList.add("����о�����(����)�������ʣ���ע�Ᵽů.");
		this.messageList.add("����о���ƫ������Ϊ���ʣ�");
		this.messageList.add("����о���Ϊ���ʣ���ɽ��ܣ�");
		this.messageList.add("����о�ƫů����Ϊ���ʣ�");
		this.messageList.add("����о�ƫ�ȣ������ʣ����ʵ����£�");
		this.messageList.add("����о����ȣ��ܲ����ʣ�ϣע�����£�");
		this.messageList.add("����о����ȣ�������Ӧ��ϣע�����£��Է����");
		
	}
	
	public String getMessage(double ssd){
		this.ssd = ssd;
		if(ssd<=25)
			return "-4����"+messageList.get(0);
		else if(ssd<=38)
			return "-3����"+messageList.get(1);
		else if(ssd<=50)
			return "-2����"+messageList.get(2);
		else if(ssd<=58)
			return "-1����"+messageList.get(3);
		else if(ssd<=70)
			return "0����"+messageList.get(4);
		else if(ssd<=75)
			return "1����"+messageList.get(5);
		else if(ssd<=79)
			return "2����"+messageList.get(6);
		else if(ssd<=85)
			return "3����"+messageList.get(7);
		else
			return "4����"+messageList.get(8);
		
	}

}
