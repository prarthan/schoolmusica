package com.khatu.musicschool.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;




public class AjaxBotFilter implements Filter {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void doFilter(ServletRequest request, ServletResponse response,
		      FilterChain chain) throws IOException {

			  String queryString = "";
			  
			  if (request instanceof HttpServletRequest) {
				  String url = ((HttpServletRequest)request).getRequestURL().toString();
				  queryString = ((HttpServletRequest)request).getQueryString();
			   
		      if ((queryString != null) && (queryString.contains("_escaped_fragment_"))) {

		       // rewrite the URL back to the original #! version
		       // remember to unescape any %XX characters
		       url = url + "?id=" + request.getParameter("id") ;
		       logger.info(request.getParameter("id"));
		       String url_with_hash_fragment = URLDecoder.decode(url, "UTF-8");

		       // use the headless browser to obtain an HTML snapshot
		       final WebClient webClient = new WebClient();
		       HtmlPage page = (HtmlPage) webClient.getPage(url_with_hash_fragment);

		       // important!  Give the headless browser enough time to execute JavaScript
		       // The exact time to wait may depend on your application.
		        try {
					webClient.wait(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		       // return the snapshot
		        PrintWriter out = response.getWriter();
		        out.println(page.asXml());
		     } else {
		      try {
		        // not an _escaped_fragment_ URL, so move up the chain of servlet (filters)
		        chain.doFilter(request, response);
		      } catch (ServletException e) {
		        System.err.println("Servlet exception caught: " + e);
		        e.printStackTrace();
		      }
		    }
		}	      
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
