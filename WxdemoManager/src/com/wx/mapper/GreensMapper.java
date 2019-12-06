package com.wx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wx.vo.Product;

public interface GreensMapper {

	//�õ���ѯ��Ʒ����������Ŀ
	public int getTotalCount(@Param("productName")String productName);
	//��ѯҪ��ʾ������
	public List<Product> findGreens(@Param("page")Integer newPage, @Param("pageSize")Integer pageSize,
															@Param("productName")String productName);
	//�õ�������ƷId
	public List<Product> getAllProductId();
	//�����Ʒ�� �ܲ˵�
	public void saveProductInfo(Product productInfo);
	//�����Ʒ �� ����
	public void saveChunCaiProductInfo(Product productInfo);
	//�����Ʒ �� ����
	public void saveYueCaiProductInfo(Product productInfo);
	//�����Ʒ �� �ղ�
	public void saveSuCaiProductInfo(Product productInfo);
	//�����Ʒ �� ³��
	public void saveLuCaiProductInfo(Product productInfo);
	
	//ͨ��Id���Ҳ�Ʒ
	public Product findProductById(Integer id);
	
	//ͨ��ID�޸Ĳ�Ʒ
	public void updateProductInfoById(Product productInfo);
	//���´���
	public void updateChuancaicaiProductByProductId(Product productInfo);
	//��������
	public void updateYuecaiProductByProductId(Product productInfo);
	//�����ղ�
	public void updateSucaiProductByProductId(Product productInfo);
	//����³��
	public void updateLucaiProductByProductId(Product productInfo);
	
	//ɾ���ܲ˵���Ʒͨ��productId
	public void delProductByProductId(Integer productId);
	//ɾ������
	public void delChuanCaiProductByProductId(Integer productId);
	//ɾ������
	public void delYueCaiProductByProductId(Integer productId);
	//ɾ���ղ�
	public void delSuCaiCaiProductByProductId(Integer productId);
	//ɾ��³��
	public void delLuCaiProductByProductId(Integer productId);

	//�޸�����Ʒ ���¼�
	public void updateProductIsFinish(@Param("id")Integer id, @Param("isFinish")int isFinish);
	//�޸�����Ʒ���¼�
	public void updateChuancaiFinish(@Param("productId")String productId, @Param("isFinish")int isFinish);
	//�޸�����Ʒ���¼�
	public void updateYuecaiFinish(@Param("productId")String productId, @Param("isFinish")int isFinish);
	//�޸�����Ʒ���¼�
	public void updateSucaiFinish(@Param("productId")String productId, @Param("isFinish")int isFinish);
	//�޸�����Ʒ���¼�
	public void updateLucaiFinish(@Param("productId")String productId, @Param("isFinish")int isFinish);
	
	//������Ʒ
	public Product findGreen(Integer id);

	

}
