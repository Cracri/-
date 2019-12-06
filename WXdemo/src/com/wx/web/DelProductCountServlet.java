package com.wx.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wx.pojo.OrderDetail;
import com.wx.service.OrderService;
import com.wx.vo.OrderVo;

import net.sf.json.JSON;
import net.sf.json.JSONArray;

public class DelProductCountServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/* ������Ӧͷ����ajax������� */  
		response.setHeader("Access-Control-Allow-Origin", "*");  
		/* �Ǻű�ʾ���е��������󶼿��Խ��ܣ� */  
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");  
		request.setCharacterEncoding("UTF-8");
		PrintWriter print = response.getWriter();
		
		
		String orderNumber = request.getParameter("orderNumber");
		String productId = request.getParameter("productId");
		String productPrice = request.getParameter("productPrice");
		
		OrderVo orderVo = new OrderVo();
		orderVo.setOrderNumber(orderNumber);
		orderVo.setProductId(Integer.parseInt(productId));
		orderVo.setProductPrice(Double.parseDouble(productPrice));
		
		OrderService orderService = new OrderService();
		
		//ɾ���� �� ��Ʒ��  ���� ʣ�µ���Ʒ ��ǰ̨
		List<OrderDetail> list =   orderService.DelProductCount(orderVo);
		
		JSONArray json = JSONArray.fromObject(list);
		print.println(json);
		
		
		
	}
}

