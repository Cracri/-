package com.wx.service;

import java.util.List;

import com.wx.pojo.OrderDetail;
import com.wx.vo.PageBean;

public interface OrderService {

	PageBean getOrderList(Integer page, Integer pageSize);
	//�޸Ķ����Ƿ���յ�״̬
	String updateOrderIsAccpet(Integer id, int  isAcpet);
	//ͬʱ������ʾ���µĶ�����֧��
	void GetNewPayOrder();
	//ͨ��ID�鿴�ö����������Ʒ
	List<OrderDetail> returnGetOrderDetails(Integer orderId);

}
