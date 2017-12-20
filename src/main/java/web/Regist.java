package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Regist {
	
	public boolean DoRegist(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		System.out.println("Regist begin..");
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("doFilter action:"+action);
		if("/login".equals(action)){
			/*
			 * �ȱȽ���֤���Ƿ���ȷ�������֤�벻��ȷ���򷵻ص�¼ҳ�沢��ʾ�û������򣬱Ƚ��û���������
			 * */	
			//�û��ύ����֤��
			String number1 = req.getParameter("number");
			//session�����ϰ󶨵���֤��
			HttpSession session = req.getSession();
			String number2 = (String)session.getAttribute("number");
			//�Ƚ�������֤��
			if(!number1.equals(number2)){
				req.setAttribute("number_error", "��֤�����");
				req.getRequestDispatcher("login.jsp").forward(req, res);
				return false;
			}
			return true;
		}else if("/regist".equals(action)){
			String number1 = req.getParameter("number");
			HttpSession session = req.getSession();
			String number2 = (String)session.getAttribute("number");
			if(!number1.equals(number2)){
				req.setAttribute("number_error", "��֤�����");
				req.getRequestDispatcher("regist.jsp").forward(req, res);
				return false;
			}
			return true;
		}
		System.out.println("Regist end..");
		return false;
	}
	
}
