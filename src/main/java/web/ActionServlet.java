package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;

public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		UserDAO dao = new UserDAO();
		PrintWriter pw = res.getWriter();
		String uri = req.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		System.out.println("Service begin..");
		if("/login".equals(action)){
			String username = req.getParameter("username");
			String pwd = req.getParameter("pwd");
			//查询数据库
			try {
				User user = dao.findByUsername(username);
				if(user !=null && user.getPwd().equals(pwd)){
					//登录成功，将一些数据绑定到session对象上，然后重定向到首页
					HttpSession sessio = req.getSession();
					sessio.setAttribute("user", user);
					res.sendRedirect("index.jsp");
				}else{
					//登录失败,转发到登录页面，并且提示用户
					req.setAttribute("login_failed", "用户名或密码错误");
					req.getRequestDispatcher("login.jsp").forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				pw.println("系统繁忙，稍后重试");
			}
		}else if("/regist".equals(action)){		
			User user = new User();
			user.setUsername(req.getParameter("username"));
			user.setName(req.getParameter("name"));
			user.setPwd(req.getParameter("pwd"));
			user.setGender(req.getParameter("sex"));
			try {
				 User user1 = dao.findByUsername(user.getUsername());
				if(user1==null){
					dao.save(user);
					res.sendRedirect("login.jsp");
				}else{
					req.setAttribute("regist_failed", "该用户已存在");
					req.getRequestDispatcher("regist.jsp").forward(req, res);
				}
			} catch (Exception e) {
				e.printStackTrace();
				pw.println("系统繁忙，稍后再试");
			}
		}
		System.out.println("Service end..");
	}
   

}
