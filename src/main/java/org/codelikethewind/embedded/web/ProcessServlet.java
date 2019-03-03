package org.codelikethewind.embedded.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codelikethewind.embedded.util.StartupBean;
import org.jbpm.services.api.model.UserTaskInstanceDesc;
import org.jbpm.services.ejb.api.ProcessServiceEJBLocal;
import org.jbpm.services.ejb.api.RuntimeDataServiceEJBLocal;

public class ProcessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
    private ProcessServiceEJBLocal processService;
	
	@EJB
    private RuntimeDataServiceEJBLocal runtimeDataService;


	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
		        
		long processInstanceId = -1;
        Map<String, Object> params = new HashMap<String, Object>();
        processInstanceId = processService.startProcess(StartupBean.DEPLOYMENT_ID,
                "com.sample.bpmn.hello", params);
        System.out.println("Process instance " + processInstanceId + " has been successfully started.");
        UserTaskInstanceDesc task = runtimeDataService.getTaskById(1L);

        System.out.println("Task information " + task );

        req.setAttribute("message", "process instance (id = "
        		+ processInstanceId + ") has been started.");
        

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context
        		.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, res);
        
    }
}
