package com.wx.vo;

public class PageBean {
	
	
	private Integer code; 				//: res.status, //�����ӿ�״̬
    private String msg;				//		: res.message, //������ʾ�ı�
    private Integer count;			//: res.total, //�������ݳ���
    private Object data	;		//: res.data.item //���������б�
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
    
    
}
