package com.wx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wx.mapper.OrderMapper;
import com.wx.pojo.Order;
import com.wx.pojo.OrderDetail;
import com.wx.service.OrderService;
import com.wx.util.SpeachUtil;
import com.wx.vo.PageBean;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderMapper orderMapper;
	
	@Override
	public PageBean getOrderList(Integer page, Integer pageSize) {
		//��ѯ����
		int totalCount = orderMapper.getTotalCount();
		//���²�ѯ������
		page = (page - 1) * pageSize;
		//��ѯҪ��ʾ������
		List<Order> orderList = orderMapper.getOrderList(page,pageSize);
		System.out.println(orderList);
		//��װ����
		PageBean pb = new PageBean();
		pb.setMsg("ok");
		pb.setCode(0);
		pb.setCount(totalCount);
		pb.setData(orderList);
		return pb;
	}

	
	@Override
	public String updateOrderIsAccpet(Integer id, int  isAcpet) {
		String result = "";
		try {
			orderMapper.updateOrderIsAccpet(id,isAcpet);
			
			result = "ok";
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}

	
	
	//ͬʱ������ʾ���µĶ�����֧��
	public void GetNewPayOrder() {
		//�õ������Ķ���
		List<Order> list =  orderMapper.GetNewPayOrder();
		//���list��Ϊ�գ�������ʾ�����Ķ���
		if(!StringUtils.isEmpty(list)) {
			for(int i=0;i<list.size();i++) {
				int orderDeskNumber = list.get(i).getOrderDeskNumber();		//����
				double orderTotalPrice = list.get(i).getTotalPrice();				//�ܼ�
				String remark = list.get(i).getRemark();
				SpeachUtil.speakMessage(orderDeskNumber+"����֧��"+orderTotalPrice+"��Ǯ����ע:"+remark, 80, 2);
			}
			
			//�Ķ�����֮��ɾ����ʱ�����еĶ�����Ϊ�´�ˢ�µ�ʱ���Ķ��µĶ���
			orderMapper.delTempOrder();
		}
	}


	@Override
	public List<OrderDetail> returnGetOrderDetails(Integer orderId) {
		
		List<OrderDetail> list = orderMapper.returnGetOrderDetails(orderId);
		
		return list;
	}

}
