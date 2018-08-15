package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class TBAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String TB_TeacherID ;
    private String TB_BuildingID ;
	public String getTB_TeacherID() {
		return TB_TeacherID;
	}

	public void setTB_TeacherID(String tBTeacherID) {
		TB_TeacherID = tBTeacherID;
	}

	public String getTB_BuildingID() {
		return TB_BuildingID;
	}

	public void setTB_BuildingID(String tBBuildingID) {
		TB_BuildingID = tBBuildingID;
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
		
		//��ѯ�Ƿ����
		List<TBBean> list=new TBDao().GetList("TB_TeacherID='"+TB_TeacherID+"' and TB_BuildingID="+TB_BuildingID, "");
		if(list.size()>0)
		{
			out.print("<script language='javascript'>alert('�ù���Ա�Ѿ��ڹ���¥���ˣ���Ҫ�ظ���ӣ�');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		
		//���
		TBBean cnbean=new TBBean();
		cnbean.setTB_TeacherID(Integer.parseInt(TB_TeacherID));
		cnbean.setTB_BuildingID(Integer.parseInt(TB_BuildingID));

		new TBDao().Add(cnbean);
		    
		//��ת
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='TBManager.action?Building_ID="+TB_BuildingID+"';</script>");
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
