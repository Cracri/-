package com.wx.dao;

import java.util.List;

import com.wx.pojo.Order;
import com.wx.vo.Product;

public interface OrderDao {
	
	//�鿴�ö����Ƿ����
	public Order findOrderNumber(String orderNumber);
	//��Ӷ���
	public void addOrder(Order o);
	//�޸Ķ����������ͽ��
	public void updateOrder(int newTotalCount, double newTotalPrice, int orderId);
	//ɾ������
	public void delOrder(String orderNumber);
	//���¶������� �� ���
	public void updateOrderCountAndTotalPrice(String orderNumber, int newOrderCount, double newOrderPrice);
	//
	public List<Product> findDelProduct(String delArray);
	//֧���ɹ�
	public String paySuccess(int orderId, String remark, String address);
	//����ʱOrder���������
	public void insertTempOrder(Order order);
	//��ѯ�û����Ѿ�����Ķ���
	public List<Order> findUserOrder(String username);
	//��ѯ�û���δ����Ķ���
	public List<Order> findNoPayOrderByUsername(String username);
	//ɾ������ͨ������
	public void delOrderByOrderNumber(String orderNumber);
	//��ѯ֧�������Ƿ����
	public int findOrderIsFinish(String orderNumber);
	

}
