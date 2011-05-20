package mtcls.server.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mtcls.server.container.HasSpringContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class SpringControllerGWT extends RemoteServiceServlet
		implements Controller, HasSpringContext, ServletContextAware {
	protected RemoteService remoteService;
	protected ApplicationContext applicationContext;
	protected ServletContext servletContext;

    public void init() throws ServletException {
    	super.init();
    	log("SpringControllerGWT:init:INVOKED");
    	setApplicationContext(WebApplicationContextUtils.getWebApplicationContext(getServletContext()));
    }


	/**
	 * Implements Spring Controller interface method.
	 * 
	 * Call GWT's RemoteService doPost() method and return null.
	 * 
	 * @param request
	 *            current HTTP request
	 * @param response
	 *            current HTTP response
	 * @return a ModelAndView to render, or null if handled directly
	 * @throws Exception
	 *             in case of errors
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if(getRemoteService()!=null){
			Controller controller = (Controller)getRemoteService();
			return controller.handleRequest(request, response);
		}
		doPost(request, response);
		return null; // response handled by GWT RPC over XmlHttpRequest
	}

	/**
	 * Process the RPC request encoded into the payload string and return a
	 * string that encodes either the method return or an exception thrown by
	 * it.
	 */
	@Override
	public String processCall(String payload) throws SerializationException {
		if(getRemoteService()!=null){
			RemoteServiceServlet remoteServlet = (RemoteServiceServlet)getRemoteService();
			return remoteServlet.processCall(payload);
		}
		return super.processCall(payload);
	}

	public Class<?> getRemoteServiceClass() {
		Class<?> retval = null;
		if (getRemoteService() == null) {
			return retval;
		}
		retval = getRemoteService().getClass();
		return retval;
	}

	/**
	 * @return the remoteService
	 */
	public RemoteService getRemoteService() {
		return remoteService;
	}

	/**
	 * @param remoteService
	 *            the remoteService to set
	 */
	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}
	
	@Override
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}


	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}


	/**
	 * @param servletContext the servletContext to set
	 */
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
