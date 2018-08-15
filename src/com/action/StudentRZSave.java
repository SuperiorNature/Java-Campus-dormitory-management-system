package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class StudentRZSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String Building_ID ;
    private String Domitory_ID ;
    private String Student_Username ;
	public String getBuilding_ID() {
		return Building_ID;
	}

	public void setBuilding_ID(String buildingID) {
		Building_ID = buildingID;
	}

	public String getDomitory_ID() {
		return Domitory_ID;
	}

	public void setDomitory_ID(String domitoryID) {
		Domitory_ID = domitoryID;
	}

	public String getStudent_Username() {
		return Student_Username;
	}

	public void setStudent_Username(String studentUsername) {
		Student_Username = studentUsername;
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
		
		StudentBean sbean=new StudentBean();
		//��ѯ�û����Ƿ����
		List<StudentBean> list=new StudentDao().GetAllList("Student_Username='"+Student_Username+"'", "");
		if(list.size()<1)
		{
			out.print("<script language='javascript'>alert('ѧ�Ų����ڣ�');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		else
		{
			sbean=new StudentDao().GetAllFirstBean("Student_Username='"+Student_Username+"'");
			if(!(sbean.getStudent_State().equals("δ��ס")))
			{
				out.print("<script language='javascript'>alert('��ѧ������"+sbean.getStudent_State()+"״̬����ֹ��ס������');history.back(-1);</script>");
				out.flush();out.close();return null;
			}
		}
		//�޸�
		StudentBean cnbean=new StudentBean();
		cnbean=new StudentDao().GetAllBean(sbean.getStudent_ID());
		cnbean.setStudent_DomitoryID(Integer.parseInt(Domitory_ID));
		cnbean.setStudent_State("��ס");
		new StudentDao().Update(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('��ס�����ɹ���');window.location='StudentRZ.action';</script>");
		out.flush();out.close();return null;
		
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
