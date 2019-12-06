package com.wx.service;

import java.util.List;

import org.springframework.util.StringUtils;

import com.wx.dao.OrderDao;
import com.wx.dao.OrderDaoImpl;
import com.wx.dao.OrderDetailDao;
import com.wx.dao.OrderDetailDaoImpl;
import com.wx.pojo.Order;
import com.wx.pojo.OrderDetail;

public class OrderDetailService {
	OrderDetailDao odd = new OrderDetailDaoImpl();
	OrderDao od = new OrderDaoImpl();

	//�õ����鶩�� �� ��͵�����
	public List<OrderDetail> GetOrderDetails(String orderNumber) {
		Integer orderId = null;
		//ͨ��������� ��ȡ ��ǰ������ id
		Order order = od.findOrderNumber(orderNumber);
		
		if(StringUtils.isEmpty(order)) {
			return null;
		}else {
			orderId = order.getId();
		}
		return odd.GetOrderDetails(orderId);
	}
}
