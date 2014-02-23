package cn.edu.tongji.filter;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.tongji.util.urlAccessSet;

public class UrlFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig filterConfig;
	private FilterChain chain;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	private final static String noAuthUrl[]={"webs/login.html","webs/img/","webs/css/","webs/js/"};

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		this.chain = chain;
		this.request = (HttpServletRequest) servletRequest;
		this.response = ((HttpServletResponse) servletResponse);
		String url = request.getServletPath();
		if (url == null) {
			url = "";
		}

		HttpSession session = request.getSession();
		String identity = (String) session.getAttribute("identity");
		if (noFileUrl(url, request)) { // skip those pages which don`t need
										// control access
			chain.doFilter(request, response);
		} else if (identity == null) { // not login and return to login page
			response.sendRedirect("/EAS/webs/login.html");
		} else {
			verifyUrl(url, identity);// check whether user has permission to
										// access the url
		}
	}

	/**
	 * check whether url need access access
	 */

	protected boolean noFileUrl(String url, HttpServletRequest request) {
		for(int i=0;i<noAuthUrl.length;i++){
			if(url.indexOf(noAuthUrl[i]) >= 0){
				return true;
			}
		}

		return false;
	}

	/**
	 * 
	 * @param url
	 * 
	 * @param identity
	 *            current user`s identity
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void verifyUrl(String url, String identity) throws IOException,
			ServletException {
		if (pass(url, identity)) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect("/EAS/webs/error.html");
		}
	}

	/**
	 * check whether identity has the permission to access the url
	 * 
	 * @param identity
	 * @param url
	 * @return
	 */
	protected boolean pass(String url, String identity) {
		Set<String> urls = null;
		if (identity.equals("college")) {
			urls = urlAccessSet.getInstance().collegeSet;
		} else if (identity.equals("expert")) {
			urls = urlAccessSet.getInstance().expertSet;
		} else if (identity.equals("admin")) {
			urls = urlAccessSet.getInstance().adminSet;
		}
		Iterator<String> iter = urls.iterator();
		while (iter.hasNext()) {
			String temp = (String) iter.next();
			if (url.contains(temp)) {
				return true;
			}
		}
		return false;
	}
}
