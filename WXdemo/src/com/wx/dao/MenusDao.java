package com.wx.dao;
import java.util.List;

import com.wx.pojo.Menu;
import com.wx.pojo.MenuChuanCai;
import com.wx.pojo.MenuDessert;
import com.wx.pojo.MenuLuCai;
import com.wx.pojo.MenuSuCai;
import com.wx.pojo.MenuYueCai;

public interface MenusDao {
	
	//��ѯ�ܲ˵�
	public List<Menu> findIndexGreens() ;
	//��ѯ����
	public List<MenuChuanCai> findChuanCai();
	//��������
	public List<MenuYueCai> findYueCai();
	//�����ղ�
	public List<MenuSuCai> findSuCai();
	//����³��
	public List<MenuLuCai> findLuCai();
	//������Ʒ
	public List<MenuDessert> findDessert();

}
