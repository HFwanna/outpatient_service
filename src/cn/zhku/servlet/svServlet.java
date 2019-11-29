package cn.zhku.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;

import cn.zhku.domain.Registration;

/**
 * Servlet implementation class svServlet
 */
@WebServlet("/svServlet")
public class svServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public svServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*System.out.println("第一步有没有访问servlet");
		Map<String, String> map = new HashMap<String, String>();
		List list = new ArrayList<Map<String, String>>();
		map.put("cust_name", "渣渣辉");
		map.put("cust_level", "1");
		map.put("menuname", "客户");
		list.add(map);
		String json = JSON.toJSONString(list);
		response.setContentType("application/json;charset=utf8");
		try {
			System.out.println("第二步有没有打印json"+json);
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("第一步有没有访问servlet");
		
		String parameter = request.getParameter("name");
		System.out.println("parameter:"+parameter);
		//更改reg_id就诊未就诊操作
		if (parameter!=null) {
			Configuration configuration = new Configuration().configure();
			SessionFactory sf = configuration.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			
			String hql = "update Registration set reg_state = 1 where reg_id = :reg_id";
			Query query = session.createQuery(hql);
			query.setParameter("reg_id", Long.valueOf(parameter));
			int ret = query.executeUpdate();
			
			transaction.commit();
			session.close();
			sf.close();
			response.sendRedirect("./NewFile.jsp");
			return;
		}
		//这个是回显病人列表list
			Configuration configuration = new Configuration().configure();
			SessionFactory sf = configuration.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			/*Registration r1 = new Registration();
			r1.setReg_patientName("陈先生");
			session.save(r1);*/
			Criteria criteria = session.createCriteria(Registration.class);
			List list = criteria.list();
			
			transaction.commit();
			session.close();
			sf.close();
			
		
		
		String json = (String) JSON.toJSONString(list);
		response.setContentType("application/json;charset=utf8");
		try {
			System.out.println("第二步有没有打印json"+json);
			response.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
