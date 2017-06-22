package initialize;

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
import com.ebupt.vnbo.request.initialize.InitRequest;
import com.ebupt.vnbo.service.initialize.InitService;
import com.ebupt.vnbo.serviceImpl.Initialize.InitServiceImpl;
import com.ebupt.vnbo.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Servlet implementation class InitManage
 */
public class InitManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InitService initService=new InitServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitManage() {
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
	       InitRequest initRequest=JSON.toJavaObject(webRequest, InitRequest.class);
	       pr.write(initService.resolve(initRequest).toJSONString());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
