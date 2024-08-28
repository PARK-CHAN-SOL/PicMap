package com.picmap.app.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.picmap.app.member.MemberDTO;

/**
 * Servlet Filter implementation class Manage
 */
public class ManagerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ManagerFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest)request;
		
		HttpSession session = req.getSession();
		
		Object obj = session.getAttribute("member");
		
		if(obj != null) {		
			//로그인한 계정이 매니저가 맞나
			MemberDTO m = (MemberDTO)obj;
			Long manager = m.getMemberNum();
			
			if(manager == 1) {		
				chain.doFilter(request, response);
			}else {
				request.setAttribute("result", "권한이 없습니다.");
				request.setAttribute("url", "/");
				RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/message.jsp");
				view.forward(request, response);
			}
		}else {
			request.setAttribute("result", "권한이 없습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/message.jsp");
			view.forward(request, response);
		}
			

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
