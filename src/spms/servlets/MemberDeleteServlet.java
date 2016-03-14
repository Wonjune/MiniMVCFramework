package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try{
			ServletContext sc = this.getServletContext();
			
			MemberDao dao = (MemberDao)sc.getAttribute("memberDao");
			System.out.println(req.getAttribute("no"));
			int result = dao.delete(Integer.parseInt((String)req.getAttribute("no")));
			if(result == 0) throw new Exception();
			req.setAttribute("viewUrl", "request:list.do");
			
		}catch(Exception e){
			throw new ServletException(e);
		}
	}

}
