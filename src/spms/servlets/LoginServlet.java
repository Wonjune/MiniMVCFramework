package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("viewUrl", "/auth/LogInForm.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Member member = (Member)req.getAttribute("member");
		
		try{
			ServletContext sc = this.getServletContext();
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			
			member = dao.exist(member.getEmail(), member.getPassword());
			
			if(member != null){
				HttpSession session = req.getSession();
				session.setAttribute("member", member);
				req.setAttribute("viewUrl", "redirect:../member/list.do");
			}else{
				req.setAttribute("viewUrl", "/auth/LogInFail.jsp");
			}
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}
