package com.wx.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.wx.dao.OrderDao;
import com.wx.dao.OrderDaoImpl;
import com.wx.dao.OrderDetailDao;
import com.wx.dao.OrderDetailDaoImpl;
import com.wx.pojo.Order;
import com.wx.pojo.OrderDetail;
import com.wx.vo.OrderVo;
import com.wx.vo.Product;
import com.wx.vo.UserProductVo;


public class OrderService {

	OrderDao od = new OrderDaoImpl();
	OrderDetailDao odd = new OrderDetailDaoImpl();

	//��Ӷ��� �� ���鶩��
	public String addOrderAndOrderDetail(OrderVo vo) {
		//�õ������ı��
		String orderNumber = vo.getOrderNumber();
		//�ж�Order ���Ƿ���ڸö��� 
		Order order = od.findOrderNumber(orderNumber);
		
		if(StringUtils.isEmpty(order)) {									//����ö���������   �ʹ����ö���
			Order o = new Order();
			o.setOrderNumber(orderNumber);
			o.setOrderState("δ����");
			o.setTotalCount(1);
			o.setTotalPrice(vo.getProductPrice());
			o.setOrderDeskNumber(vo.getOrderDeskNumber());				//   ����Ŀǰ ʼ��Ϊ 1����
			o.setOrderConsignee(vo.getOrderConsignee());
			
			//���ö��� ��� �� ���ݿ�,�����ظ������ݵģ�����id��
			 od.addOrder(o);
			//ͨ���ղ���ӵ����ݣ��ٴβ��Ҷ�����id
			Order findorder = od.findOrderNumber(orderNumber);
			int orderId = findorder.getId();
			
			addOrderDetail(vo,orderId);
		}else {																
			//ͨ���ղ���ӵ����ݣ��ٴβ��Ҷ�����id
			Order findorder = od.findOrderNumber(orderNumber);
			int orderId = findorder.getId();
			//����ö�������,���޸Ķ����е� һЩ����
			int newTotalCount = order.getTotalCount()+1;
			double newTotalPrice = order.getTotalPrice() + vo.getProductPrice();
			
			od.updateOrder(newTotalCount,newTotalPrice,orderId);
			
			//ͬʱ Ҳ������������� �������
			addOrderDetail(vo,orderId);
		}
		return null;
	}
	
	//������鶩���ķ���
	private void addOrderDetail(OrderVo vo, int orderId) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrderNumber(vo.getOrderNumber());
		orderDetail.setOrderId(orderId);
		orderDetail.setProductId(vo.getProductId());
		orderDetail.setProductName(vo.getProductName());
		orderDetail.setProductPrice(vo.getProductPrice());
		orderDetail.setProductCount(1);
		orderDetail.setProductDiscount(0);
		orderDetail.setTotalPrice(vo.getProductPrice());
		orderDetail.setProductPic(vo.getProductPic());
		//������鶩��
		odd.addOrderDetail(orderDetail);
	}



	//ɾ������
	public void delOrder(OrderVo vo) {
		//ͨ��������� �ҵ�����
		Order order = od.findOrderNumber(vo.getOrderNumber());
		int orderId = order.getId();
		//�õ����� ��  ��������(productCount)
		int productCount = order.getTotalCount();
		//�õ�   ���鶩���и���Ʒ��   ����
		int detailProductCount = odd.getDetailProductCount(vo.getOrderNumber(),orderId,vo.getProductId());
		
		//�ж� �ö���  ������ �Ƿ� <= 1 �ǵĻ� ��ֱ��ɾ���ö���  		 ����  �޸�����
//		if(productCount <= 1) {
//			//ɾ���ö���  ����ɾ��  ���鶩��
//			//od.delOrder(vo.getOrderNumber());
//			//odd.delOrderDetail(orderId,vo.getProductId());
//		}else {
			int newTotalCount = order.getTotalCount() - detailProductCount;
			double newTotalPrice = order.getTotalPrice() - vo.getProductPrice();
			
			od.updateOrder(newTotalCount, newTotalPrice, orderId);
			odd.delOrderDetail(orderId,vo.getProductId());
//		}
		
	}

	
	
	
	//������Ʒ������
	public void addProductCount(OrderVo orderVo) {
		
		String orderNumber  = orderVo.getOrderNumber();
		//���ݶ������ �ҵ� �ö���
		Order order = od.findOrderNumber(orderNumber);
		int orderId = order.getId();
		//�ҵ� ���鶩���е�  ���� ��Ʒ
		OrderDetail orderDetail = odd.findOrderDetail(orderId,orderVo.getProductId());
		//�ж϶����Ƿ����
		if(StringUtils.isEmpty(order)) {		//�������������
			
		}else {						//��������  ----->  ���޸�  ���鶩���еĵ������
			int newProductCount = orderDetail.getProductCount() + 1;
			double newTotalPrice = newProductCount * orderDetail.getProductPrice();
			
			//�������鶩��
			odd.updateOrderDetailCountAndTotalPrice(orderId,orderVo.getProductId(),newProductCount,newTotalPrice);
			
			int newOrderCount = order.getTotalCount() + 1;
			double newOrderPrice = order.getTotalPrice() + orderVo.getProductPrice();
			
			//���¶������� �� ���
			od.updateOrderCountAndTotalPrice(orderVo.getOrderNumber(),newOrderCount,newOrderPrice);
		}
	}

	
	//������Ʒ������
	public void subProductCount(OrderVo orderVo) {
		String orderNumber  = orderVo.getOrderNumber();
		//���ݶ������ �ҵ� �ö���
		Order order = od.findOrderNumber(orderNumber);
		int orderId = order.getId();
		//�ҵ� ���鶩���е�  ���� ��Ʒ
		OrderDetail orderDetail = odd.findOrderDetail(orderId,orderVo.getProductId());
		//�ж϶��� �Ƿ����
		if(StringUtils.isEmpty(order)) {				//���������
			
		}else {			//��������  ----->  ���޸�  ���鶩���еĵ������
			int newProductCount = orderDetail.getProductCount() - 1;
			double newTotalPrice = newProductCount * orderDetail.getProductPrice();
			
			//�������鶩��
			odd.updateOrderDetailCountAndTotalPrice(orderId,orderVo.getProductId(),newProductCount,newTotalPrice);
			
			int newOrderCount = order.getTotalCount() - 1;
			double newOrderPrice = order.getTotalPrice() - orderVo.getProductPrice();
			
			//���¶������� �� ���
			od.updateOrderCountAndTotalPrice(orderVo.getOrderNumber(),newOrderCount,newOrderPrice);
		}
	}


	
	/**
	 * 1.���ݶ�������ҵ��ö���
	 * 2.ɾ�����鶩���е���Ʒ�������¶����е�����
	 * 3.����ʣ��� ��Ʒ ���ҷ��ظ�ǰ̨
	 * */
	//ɾ����Ʒ �� ����  ʣ��� ��Ʒ��ǰ̨
	public List<OrderDetail> DelProductCount(OrderVo orderVo) {
		List<OrderDetail> list = null;
		String orderNumber = orderVo.getOrderNumber();
		//���ݶ������ �ҵ� �ö���
		Order order = od.findOrderNumber(orderNumber);
		//�õ�������Id
		int orderId = order.getId();
		//�ҵ� ���鶩���е�  ���� ��Ʒ
		OrderDetail orderDetail = odd.findOrderDetail(orderId,orderVo.getProductId());
		
		//�ж϶����Ƿ����
		if(StringUtils.isEmpty(order)) {
			/*���������*/
			
		}else {				//������ڣ��Ͷ����鶩�� ����
			/*�õ�  �ò�Ʒ  ������ ��  �ܼ۸�  ������  �ܶ����� ����*/
			//ɾ�� �� ��Ʒ
			int productCount = orderDetail.getProductCount();
			double productTotalPrice = orderDetail.getTotalPrice();
			odd.delProductCount(orderId,orderVo.getProductId());
			
			//���ܶ���������
			int newOrderCount = order.getTotalCount() - productCount;
			double newOrderPrice = order.getTotalPrice() - productTotalPrice;
			od.updateOrderCountAndTotalPrice(orderNumber, newOrderCount, newOrderPrice);
			
			//���ܶ����������ж��Ƿ������ݣ����û�о�ɾ��
//			if(order.getTotalCount() <= 0) {
//				od.delOrder(orderNumber);
//			}
			
			//�õ� ɾ��ĳ��Ʒ��ʣ����Ʒ�� ����
			list = odd.GetOrderDetails(orderId);
		}
		return list;
	}

	
	public List<Product> findDelProduct(String delArray) {
		return od.findDelProduct(delArray);
	}

	
	//֧������
	public String PayOrderServlet(String orderNumber,String remark,String address) {
		String result = "";
		//�ҵ��ö���
		Order order = od.findOrderNumber(orderNumber);
		int orderId = order.getId();
		
		try {
			result = od.paySuccess(orderId,remark,address);
			order = od.findOrderNumber(orderNumber);
			if(result.equals("ok")) {
				//���֧���ɹ�.����ʱ�����У�������ݣ�Ϊ���������Ķ�ȡ
				od.insertTempOrder(order);
			}
		} catch (Exception e) {
			System.out.println("֧������ʧ��");
		}
		
		return result ;
	}

	public List<UserProductVo> findUserOrder(String username) {
		 List<UserProductVo> userProductVoList = new ArrayList<UserProductVo>();
		 //�����ܶ���
		 List<Order> orderList =  od.findUserOrder(username);
		
		 if(StringUtils.isEmpty(orderList)) {
			 //������û���û�У�֧�������Ļ�������һ��null
			 return null;
		 }else {
			 for(int i=0;i<orderList.size();i++) {
				 UserProductVo userProductVo = new UserProductVo();
				 //�������鶩��,ͨ��������id
				 int orderId = orderList.get(i).getId();
				 List<OrderDetail> orderDetailList = odd.findUserOrderDetail(orderId);
				 
				 //ÿ����һ�����鶩�����ͷ�װһ������
				 userProductVo.setOrderNumber(orderList.get(i).getOrderNumber());
				 userProductVo.setOrderDeskNumber(orderList.get(i).getOrderDeskNumber());
				 userProductVo.setAddress(orderList.get(i).getAddress());
				 userProductVo.setOrderDetail(orderDetailList);
				 userProductVo.setTotalCount(orderList.get(i).getTotalCount());
				 userProductVo.setTotalPrice(orderList.get(i).getTotalPrice());
				 userProductVo.setRemark(orderList.get(i).getRemark());
				 userProductVo.setIsAcept(orderList.get(i).getIsAcept());
				 userProductVo.setIsFinish(orderList.get(i).getIsFinish());
				 userProductVo.setCreateTime(orderList.get(i).getCreateOrderTime());
				 userProductVoList.add(userProductVo);
			 }
			 return userProductVoList;
		 }
	}

	
	public List<UserProductVo> findNoPayOrder(String username) {
		List<UserProductVo> userProductVoList = new ArrayList<UserProductVo>();
		
		//ͨ�����ֲ��ҵ����û���δ�����
		List<Order> orderList = od.findNoPayOrderByUsername(username);
		
		//�ж�
		if(StringUtils.isEmpty(orderList)) {
			//���û��δ����Ķ������ͷ���һ��Null
			return null;
		}else {
			for(int i=0;i<orderList.size();i++) {
				UserProductVo userProductVo = new UserProductVo();
				 //�������鶩��,ͨ��������id
				 int orderId = orderList.get(i).getId();
				 List<OrderDetail> orderDetailList = odd.findUserOrderDetail(orderId);
				 
				 //ÿ����һ�����鶩�����ͷ�װһ������
				 userProductVo.setOrderNumber(orderList.get(i).getOrderNumber());
				 userProductVo.setOrderDeskNumber(orderList.get(i).getOrderDeskNumber());
				 userProductVo.setOrderDetail(orderDetailList);
				 userProductVo.setTotalCount(orderList.get(i).getTotalCount());
				 userProductVo.setTotalPrice(orderList.get(i).getTotalPrice());
				 userProductVo.setRemark(orderList.get(i).getRemark());
				 userProductVo.setCreateTime(orderList.get(i).getCreateOrderTime());
				 userProductVoList.add(userProductVo);
			 }
			 return userProductVoList;
			}
		
	}

	//ɾ���Ѿ�֧���Ķ���
	public String delOrderByOrderNumber(String orderNumber) {
		String result = "";
		//ɾ��֧������ǰ�����жϸö��� ����ʦ�Ƿ��Ѿ���ɡ�(0---δ��ɡ�1---���)
		int isFinish = od.findOrderIsFinish(orderNumber);
		//��ɲ˵��󣬲ſ���ɾ����
		if(isFinish == 1) {
			try {
				//ɾ������
				od.delOrderByOrderNumber(orderNumber);
				//ɾ�����鶩��
				odd.delOrderDetailByOrderNumber(orderNumber);
				result = "ok";
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			result = "error";
		}
		return result;
	}

	//ɾ��δ֧���Ķ���
	public String delNoPayOrderByOrderNumber(String orderNumber) {
		String result = "";
		try {
			//ɾ������
			od.delOrderByOrderNumber(orderNumber);
			//ɾ�����鶩��
			odd.delOrderDetailByOrderNumber(orderNumber);
			result = "ok";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}


	
	
	

	
	
}
