package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class TBManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String Building_ID;
	public String getBuilding_ID() {
		return Building_ID;
	}
	public void setBuilding_ID(String buildingID) {
		Building_ID = buildingID;
	}
	private List<TBBean> list;
	public List<TBBean> getList() {
		return list;
	}
	public void setList(List<TBBean> list) {
		this.list = list;
	}
	private List<TeacherBean> teacherlist;
	public List<TeacherBean> getTeacherlist() {
		return teacherlist;
	}
	public void setTeacherlist(List<TeacherBean> teacherlist) {
		this.teacherlist = teacherlist;
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

		//��ѯ
		teacherlist=new TeacherDao().GetList("","");
		
		//��ѯ
		list=new TBDao().GetList("TB_BuildingID="+Building_ID,"");
	
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
