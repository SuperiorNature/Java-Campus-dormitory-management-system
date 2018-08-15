package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class DomitoryUpdate extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String Domitory_ID;
	private DomitoryBean cnbean;
	public String getDomitory_ID() {
		return Domitory_ID;
	}

	public void setDomitory_ID(String studentID) {
		Domitory_ID = studentID;
	}

	public DomitoryBean getCnbean() {
		return cnbean;
	}

	public void setCnbean(DomitoryBean cnbean) {
		this.cnbean = cnbean;
	}

	private List<BuildingBean> list;
	public List<BuildingBean> getList() {
		return list;
	}
	public void setList(List<BuildingBean> list) {
		this.list = list;
	}
	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
		//��ѯ����¥��
		list=new BuildingDao().GetList("","Building_Name");
		//��ѯ
		cnbean=new DomitoryDao().GetBean(Integer.parseInt(Domitory_ID));
		return SUCCESS;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}
	
}
