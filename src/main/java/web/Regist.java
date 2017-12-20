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
			 * 先比较验证码是否正确，如果验证码不正确，则返回登录页面并提示用户，否则，比较用户名和密码
			 * */	
			//用户提交的验证码
			String number1 = req.getParameter("number");
			//session对象上绑定的验证码
			HttpSession session = req.getSession();
			String number2 = (String)session.getAttribute("number");
			//比较两个验证码
			if(!number1.equals(number2)){
				req.setAttribute("number_error", "验证码错误");
				req.getRequestDispatcher("login.jsp").forward(req, res);
				return false;
			}
			return true;
		}else if("/regist".equals(action)){
			String number1 = req.getParameter("number");
			HttpSession session = req.getSession();
			String number2 = (String)session.getAttribute("number");
			if(!number1.equals(number2)){
				req.setAttribute("number_error", "验证码错误");
				req.getRequestDispatcher("regist.jsp").forward(req, res);
				return false;
			}
			return true;
		}
		System.out.println("Regist end..");
		return false;
	}
	
}
