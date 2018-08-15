package com.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.dao.*;
import com.bean.*;

import com.opensymphony.xwork2.ActionSupport;

public class GoLogin extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String Type;
	private String Username;
	private String Password;
	private String Msg;
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	//�����û������execute����
	public String execute() throws Exception {
		
		
		
		if(Type.equals("ϵͳ����Ա"))
		{
			if (null == new AdminDao().CheckLogin(Username, Password)) {
				Msg = "�û��������������";
				return INPUT;
			}
			else
			{
				//��ȡID
				String Admin_ID=new AdminDao().CheckLogin(Username, Password);
				//����session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Admin_ID);
				session.setAttribute("type", "1");
				return SUCCESS;
			}
		}
		else if(Type.equals("¥�����Ա"))
		{
			if (null == new TeacherDao().CheckLogin(Username, Password)) {
				Msg = "�û��������������";
				return INPUT;
			}
			else
			{
				//��ȡID
				String Teacher_ID=new TeacherDao().CheckLogin(Username, Password);
				//����session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Teacher_ID);
				session.setAttribute("type", "2");
				
				return SUCCESS;
			}
		}
		else if(Type.equals("ѧ��"))
		{
			if (null == new StudentDao().CheckLogin(Username, Password)) {
				Msg = "�û��������������";
				return INPUT;
			}
			else
			{
				//��ȡID
				String Student_ID=new StudentDao().CheckLogin(Username, Password);
				//����session
				HttpSession session = ServletActionContext.getRequest().getSession();
				session.setAttribute("id", Student_ID);
				session.setAttribute("type", "3");
				return SUCCESS;
			}
		}
		else
		{
			Msg = "������ʹ���";
			return INPUT;
		}
		
	}
}
