package cn.zhku.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;

import cn.zhku.domain.DiseaseType;
import cn.zhku.domain.Registration;

/**
 * Servlet implementation class svServlet2
 */
@WebServlet("/svServlet2")
public class svServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public svServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("第一步有没有进来:servlet");
		String parameter = request.getParameter("reg_id");
		System.out.println("有没有获取到parameter"+parameter);
		
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		/*Registration r1 = new Registration();
		r1.setReg_patientName("陈先生");
		session.save(r1);*/
		
		Criteria criteria = session.createCriteria(DiseaseType.class);
		criteria.add(Restrictions.eq("dis_id", Long.valueOf(parameter)));
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
