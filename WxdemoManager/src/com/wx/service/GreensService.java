package com.wx.service;

import com.wx.vo.PageBean;
import com.wx.vo.Product;

public interface GreensService {
	
	public PageBean findGreens(Integer page, Integer pageSize, String title);
	
	public String judgeProductIdWithData(String productId);
	//�����Ʒ
	public String saveProductInfo(Product productInfo);
	//ͨ��Id���Ҳ�Ʒ
	public Product findProductById(Integer id);
	//ͨ��ID�޸Ĳ�Ʒ��Ϣ
	public String updateProductInfoById(Product productInfo);
	//ͨ��productId ɾ����Ʒ
	public String delProductByProductId(Integer productId, String type);
	
	//�޸��ܲ˵����¼�
	public void updateProductIsFinish(Integer id, int isFinish);

	


}
