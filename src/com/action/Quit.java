package com.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class Quit extends ActionSupport {


	//�����û������execute����
	public String execute() throws Exception {
		
		//���session
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("id");
		
		return SUCCESS;
		
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}
	
}
