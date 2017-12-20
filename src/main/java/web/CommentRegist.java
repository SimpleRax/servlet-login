package web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class CommentRegist implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)throws IOException, ServletException {
		System.out.println("doFilter begin..");
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpServletResponse res = (HttpServletResponse)arg1;
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		Regist regist = new Regist();
		if(regist.DoRegist(req, res)){
			arg2.doFilter(arg0, arg1);
		}
		System.out.println("doFilter end..");
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
