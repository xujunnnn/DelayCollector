package CustomizeMonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ebupt.vnbo.request.monitor.CustomizeMonRequest;
import com.ebupt.vnbo.service.Monitor.CustomizeMonitorService;
import com.ebupt.vnbo.serviceImpl.Monitor.CustomizeMonitorServiceImpl;
import com.ebupt.vnbo.util.BaseUtil;

/**
 * Servlet implementation class CustmoizeMonitorManage
 */
public class CustmoizeMonitorManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustmoizeMonitorManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   response.setContentType("application/json;charset=utf8");
		   request.setCharacterEncoding("utf-8");
		   PrintWriter pr=response.getWriter();
		   //read the input json
	       BufferedReader bufferedReader=new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream(),"utf8"));
	       JSONObject webRequest=BaseUtil.GetJson(bufferedReader);
	       CustomizeMonRequest customizeMonRequest=JSON.toJavaObject(webRequest, CustomizeMonRequest.class);
	       CustomizeMonitorService customizeMonitorService=new CustomizeMonitorServiceImpl();
	       pr.write(customizeMonitorService.resolve(customizeMonRequest).toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
