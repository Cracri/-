package com.wx.dao;

import java.util.List;

import com.wx.pojo.OrderDetail;

public interface OrderDetailDao {

	//������鶩��
	void addOrderDetail(OrderDetail orderDetail);
	//�õ����鶩�� �� ��͵�����
	List<OrderDetail> GetOrderDetails(int orderId);
	//ɾ������
	void delOrderDetail(int orderId, int productId);
	//�õ�   ���鶩���и���Ʒ��   ����
	int getDetailProductCount(String orderNumber, int orderId, int productId);
	//�ҵ� ���鶩���е�  ���� ��Ʒ
	OrderDetail findOrderDetail(int orderId, int productId);
	//���¶����е�  ���� �� ��Ǯ
	void updateOrderDetailCountAndTotalPrice(int orderId, int productId, int newProductCount, double newTotalPrice);
	//ɾ�� �� ��Ʒ
	void delProductCount(int orderId, int productId);
	//�����û������鶩��
	List<OrderDetail> findUserOrderDetail(int orderId);
	//ɾ�����鶩��
	void delOrderDetailByOrderNumber(String orderNumber);
	

}
