package com.wx.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wx.vo.PicBean;

@Controller
@RequestMapping(value="upload")
public class UploadController {

	@RequestMapping("/toUpload")
	@ResponseBody
	public PicBean toUpload(@RequestParam MultipartFile file) {
		PicBean pb = new PicBean();
		//1.��ȡ�ļ���
		String fileName = file.getOriginalFilename();
		System.out.println(fileName);
		//2.ָ������·��
		File  f = new File("E:\\С����(javascript)\\΢��С���򶩲���Ŀ\\image\\"+fileName);
		//3.��ʼ�ϴ�
		try {
			file.transferTo(f);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//��װ����
		pb.setCode(0);
		pb.setMsg("ok");
		pb.setData(fileName);
		return pb;
	}

	
}
