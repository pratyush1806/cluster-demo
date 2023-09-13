package com.pratyush;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/indexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String counter = (String) session.getAttribute("counter");

		if (counter == null) {
			session.setAttribute("counter", "1");
		} else {
			int hitCounter = Integer.valueOf(counter);
			hitCounter++;
			session.setAttribute("counter", String.valueOf(hitCounter));
		}

		Map<String, String> hostInfo = new HashMap<String, String>();
		hostInfo.put("node-name", System.getProperty("jboss.node.name"));
		hostInfo.put("host-name", "" + InetAddress.getLocalHost().getHostName());
		hostInfo.put("x-forwarded-for", "" + getUserReadIP(request));
		hostInfo.put("localAddr", request.getLocalAddr() + ":" + request.getLocalPort());
		
		request.setAttribute("hostInfo", hostInfo);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	private String getUserReadIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
