package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class StudentTH extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private List<BuildingBean> buildinglist;
	private List<DomitoryBean> domitorylist;

	public List<BuildingBean> getBuildinglist() {
		return buildinglist;
	}

	public void setBuildinglist(List<BuildingBean> buildinglist) {
		this.buildinglist = buildinglist;
	}

	public List<DomitoryBean> getDomitorylist() {
		return domitorylist;
	}

	public void setDomitorylist(List<DomitoryBean> domitorylist) {
		this.domitorylist = domitorylist;
	}

	private String BuildingID;
	private String DomitoryID;
	public String getBuildingID() {
		return BuildingID;
	}

	public void setBuildingID(String buildingID) {
		BuildingID = buildingID;
	}

	public String getDomitoryID() {
		return DomitoryID;
	}

	public void setDomitoryID(String domitoryID) {
		DomitoryID = domitoryID;
	}

	private String Student_Username;
	
	public String getStudent_Username() {
		return Student_Username;
	}

	public void setStudent_Username(String studentUsername) {
		Student_Username = studentUsername;
	}
	private StudentBean cnbean;
	public StudentBean getCnbean() {
		return cnbean;
	}

	public void setCnbean(StudentBean cnbean) {
		this.cnbean = cnbean;
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
		List<StudentBean> list=new StudentDao().GetList("Student_Username='"+Student_Username+"'", "");
		if(list.size()<1)
		{
			out.print("<script language='javascript'>alert('ѧ�Ų����ڣ���ѧ��δ������ס״̬��');history.back(-1);</script>");
			out.flush();out.close();return null;
		}
		
		
		//��ѯ¥��
		buildinglist=new BuildingDao().GetList("","Building_Name");
//		System.out.println(BuildingID);
		//��ѯ����
		String strWhere="1=1 ";
		if(!(isInvalid(BuildingID)))
		{
			strWhere+=" and Domitory_BuildingID='"+BuildingID+"'";
		}
		else{
			strWhere+=" and 1=2";
		}
		//��ѯ����
		domitorylist=new DomitoryDao().GetList(strWhere,"Domitory_Name");
		
		//��ѯѧ����Ϣ
		cnbean=new StudentDao().GetFirstBean("Student_Username='"+Student_Username+"'");
		
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
