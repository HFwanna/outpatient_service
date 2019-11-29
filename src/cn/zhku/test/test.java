package cn.zhku.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.dialect.function.StandardAnsiSqlAggregationFunctions.SumFunction;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonArray;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import cn.zhku.action.UserAction;
import cn.zhku.domain.DrugMessage;
import cn.zhku.domain.Registration;
import cn.zhku.domain.User;
import cn.zhku.service.UserService;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import sun.font.CreatedFontTracker;

public class test {
	@Test
	public void fun1() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction userAction = (UserAction) ac.getBean("userAction");
		UserAction userAction2 = (UserAction) ac.getBean("userAction");
		userAction.login();
		System.out.println(userAction==userAction2);
	}
	
	@Test
	public void fun2() {
		Configuration conf = new Configuration().configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		User u = session.get(User.class, 1l);
		System.out.println(u);
		
		
		tx.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun3() throws Exception {
		
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction ua = (UserAction) aContext.getBean("userAction");
		ua.login();
	}
	
	@Test
	public void fun4() throws Exception {
		
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserAction ua = (UserAction) aContext.getBean("userAction");
//		ua.saveUsercode();;
	}
	
	@Test
	public void fun5() throws Exception{
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Registration r1 = new Registration();
		r1.setReg_patientName("陈先生");
		session.save(r1);
		
		
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun6() throws Exception{
		    // write your code here
		       // HashMap<String,HashMap<String,String>> m = new HashMap<>();
		         String arr = "{'arr1':[{'dig1':'haha1'}],'arr2':[{'dig2':'haha2'}]}";
		         
		         Map<String, Object> map= JSON.parseObject(arr);
		         String string = map.get("arr1").toString();
		         JSONArray array = JSON.parseArray(string);
		         System.out.println(array.getJSONObject(0).get("dig1"));


	}
	/* public static void main(String[] args) {
		    // write your code here
		       // HashMap<String,HashMap<String,String>> m = new HashMap<>();
		        String arr = "[{\'arr1\':{\'dig1\':\'haha1\'},\'arr2\':{\'dig2\':\'haha2\'}}]";
		         JSONArray array = JSON.parseArray(arr);




		        for(int i=0;i<array.size();i++)
		        {
		           JSONObject job = array.getJSONObject(i);

		            for(String key:job.keySet())
		            {
		                 JSONObject j =job.getJSONObject(key);

		                 for(String key2: j.keySet())
		                 {
		                     System.out.println(key2);
		                     System.out.println(j.getString(key2));
		                 }
		            }
		        }

		        System.out.println("ok");
		    }*/

	@Test
	public void fun7() throws ParseException {
		/* String string = "2016-10-24";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 System.out.println(sdf.parse(string));*/
		System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-20"));
		
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		Registration r1 = new Registration();
		r1.setReg_patientName("呀五大啊打发打发");
		r1.setReg_date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-03-20"));;
		session.save(r1);
		
		
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun8() throws ParseException {
		String arr = "[[{\"dis_diagnose\":\"发烧\"},{\"dis_description\":\"感冒\"},{\"dis_doctor\":\"周医生\"},{\"dis_time\":\"2018-03-20\"},{\"dis_id\":2}],[{\"dis_diagnose\":\"脚痛\"},{\"dis_description\":\"头也痛\"},{\"dis_doctor\":\"周医生\"},{\"dis_time\":\"2018-03-21\"},{\"dis_id\":3}]]";
		String jsonString = JSON.toJSONString(arr);
		JSONArray jsonArray = JSON.parseArray(arr);
		JSONArray jsonArray2 = jsonArray.getJSONArray(0);
		JSONObject jsonObject = jsonArray2.getJSONObject(4);
		Long long1 = jsonObject.getLong("dis_id");
		System.out.println(long1 instanceof Long);
		System.out.println(long1);
		System.out.println((Long.parseLong("2")) == 2l);
	}
	
	@Test
	public void fun9() throws Exception {
		//对0-9数字循环，算出21个数21次方相加是否等于本身21位数
		int sum = 0;
		for(int i =1;i<10;i++) {
			//对第一个数9忠可能
			sum += Math.pow(i, 4);
			for(int j = 0 ; j <10;j++) {
				//对第二个数10种可能
				sum += Math.pow(j, 4);
				for(int z = 0 ; z < 10 ;z++) {
					sum += Math.pow(z, 4);
					for(int k = 0 ; k < 10 ;k++) {
						sum += Math.pow(k, 4);
						if (sum == i*1000+j*100+z*10+k) {
							System.out.println(sum);
							//清空sum
							sum -= Math.pow(k, 4);
							break;
						}
						sum -= Math.pow(z, 4);
					}
					sum -= Math.pow(z, 4);
				}
				sum -= Math.pow(j, 4);
			}
			sum = 0;
		}
		
	}

	public void cycle1(int sum,int rate,int weishu,int kaifang) {
		//递归
		for(int i = 0;i<10;i++) {
			//统计循环次数
			rate--;
			sum += Math.pow(i, kaifang);
			weishu += i*Math.pow(10, rate);
			//到达这个条件可以执行最内层代码
			if (rate ==0) {
				if (sum == weishu) {
					System.out.println(sum);
					//回到上一个嵌套的sum状态和位数状态
					sum -= Math.pow(i, kaifang);
					weishu -= i*Math.pow(10, rate);
					rate++;
					break;
				}
				sum -= Math.pow(i, kaifang);
				weishu -= i*Math.pow(10, rate);
				rate++;
			}else {
				//继续嵌套
				cycle1(sum, rate, weishu,kaifang);
				//嵌套完成这一层也需要回调状态以进行本层的下一次i的循环，不毁掉状态就会累积值了
				sum -= Math.pow(i, kaifang);
				weishu -= i*Math.pow(10, rate);
				rate++;
			}
		}
	}
	
	@Test
	public void fun10() throws Exception {
		//对0-9数字循环，算出21个数21次方相加是否等于本身21位数
		int sum = 0;
		int weishu = 0;
		int kaifang = 4;
		for(int i =1;i<10;i++) {
			//对第一个数9忠可能
			sum += Math.pow(i, kaifang);
			weishu += i*Math.pow(10, 2);
			cycle1(sum, kaifang-1, weishu,kaifang);
			sum = 0;
			weishu = 0;
		}
		
	}
	
	@Test
	public void fun11() throws Exception {
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "select d.dis_diagnose,d.dis_doctor From DiseaseType d"+
		" where registration.reg_id = :id";
		String hql2 = "select d.drug_id From DrugMessage d where drug_name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("id", 2l);
//		query.setParameter("name", "众生丸");
		List<Object[]> list = query.list();//【】，【】，【】   记录数：3
//		Object[] objects = list.get(0);
		List<Map<String, String>> list2 = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			Map<String,String> map  = new HashMap<>();
			map.put("dis_diagnose", (String) list.get(i)[0]);
			map.put("dis_doctor", (String) list.get(i)[1]);
			list2.add(map);
		}
//		List<Object> asList = Arrays.asList(objects);
		System.out.println(list2.get(0).get("dis_diagnose"));
		System.out.println(list2.get(0).get("dis_doctor"));
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun12() throws Exception {
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "select r.reg_patientName,r.reg_date from Registration r" + 
				" where reg_id = :id" ;
		Query query = session.createQuery(hql);
		query.setParameter("id", 2l);
//		query.setParameter("name", "众生丸");
		Object[] objs =  (Object[]) query.uniqueResult();//【】，【】，【】   记录数：3
//		Object[] objects = list.get(0);
		Map<String,String> map  = new HashMap<>();
		map.put("reg_patientName", (String) objs[0]);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(objs[1]);
		map.put("reg_date", dateString);
//		List<Object> asList = Arrays.asList(objects);
		System.out.println(map.get("reg_patientName"));
		System.out.println(map.get("reg_date"));
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun13() throws Exception {
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		
		String hql = "select p.pre_totalCount,p.pre_drug_name from Prescription p" +
				" where pre_registration.reg_id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", 2l);
//		query.setParameter("name", "众生丸");
		List<Object[]> list = query.list();//【】，【】，【】   记录数：3
//		Object[] objects = list.get(0);
		List<Map<String, String>> list2 = new ArrayList<>();
		for(int i = 0;i<list.size();i++) {
			Map<String,String> map  = new HashMap<>();
			map.put("pre_totalCount", (String) list.get(i)[0]);
			map.put("pre_drug_name", (String) list.get(i)[1]);
			list2.add(map);
		}
//		List<Object> asList = Arrays.asList(objects);
		System.out.println(list2.get(0).get("pre_totalCount"));
		System.out.println(list2.get(0).get("pre_drug_name"));
		
		transaction.commit();
		session.close();
		sf.close();
	}
	
	@Test
	public void fun14() {
		// 替换
		String str = "dhfjksaduirfn 11@qq..com dsjhkfa w_a-ng@163.com.d wokaz";
		String regex = " "; 
		String[] strs=str.split(regex);
		 for(String str2:strs){
		 String ragexDemo = "[a-zA-Z0-9]([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*"
		 + "@([a-zA-Z0-9]+)\\.[a-zA-Z]+\\.?[a-zA-Z]{0,2}";
		Pattern p = Pattern.compile(ragexDemo);
		Matcher m = p.matcher(str2);
		while(m.find()){
		System.out.println(m.group(0));
		  }
		 }
	}
	
	@Test
	//测试dc关联表查询
	public void fun15() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Registration.class,"r");
		String a = "";
		Date time1 = new Date(118,2,1);
		Date time2 = new Date(118,2,31);
		criteria.createAlias("diseaseTypes","d");
		criteria.add(Restrictions.eqProperty("r.reg_id", "d.registration"));
		criteria.add(Restrictions.like("d.dis_doctor", "%李%"));
		
		criteria.add(Restrictions.between("d.dis_time", time1, time2));
		List list = criteria.list();
		for(int i = 0;i<list.size();i++) {
			
			System.out.println(list.get(i));
		}
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void fun16() {
		Integer aInteger = Integer.valueOf("2");
		System.out.println(aInteger);
	}
	@Test
	public void fun17() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(DrugMessage.class);
		Boolean boolean1 = false;
		criteria.add(Restrictions.eq("drug_type", boolean1));
		criteria.add(Restrictions.between("drug_price", 12.0, 15.0));
		List list = criteria.list();
		for(int i = 0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	//测试dc关联表4表查询
	public void fun18() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Registration.class,"r");
		criteria.createAlias("diseaseTypes", "d");
		criteria.createAlias("prescription", "p");
		criteria.createAlias("prescription.pre_drugMessage", "drug");
		criteria.add(Restrictions.eqProperty("r.reg_id", "p.pre_registration"));
		criteria.add(Restrictions.eqProperty("r.reg_id", "d.registration"));
		criteria.add(Restrictions.eqProperty("drug.drug_id", "p.pre_drugMessage"));
		criteria.add(Restrictions.eq("d.dis_doctor", "李教授"));
		criteria.add(Restrictions.eq("drug.drug_id", 4l));
		
		List list = criteria.list();
		for(int i = 0;i<list.size();i++) {
			
			System.out.println(list.get(i));
		}
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	@Test
	public void fun19() {
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("select * from ops_registration r where r.reg_patientName = '陈先生'");
		Object[] registration = (Object[]) query.uniqueResult();
		System.out.println(registration.toString());
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void fun20() {
		String regist = "regist=666";
		Map map = new HashMap<>();
		Pattern pattern = Pattern.compile("[a-z]*=");
		//获得matcher对象
		Matcher matcher = pattern.matcher((CharSequence) regist);
		//把xxx=的等号前面内容提取出来 ,(((String)registration[i]).split("="))[0]是把 xxx= 截取xxx内容
		String key = (regist.split("="))[0];
		//把匹配XXX=的内容替换为空
		String value = matcher.replaceAll("");
		map.put(key, value);
		System.out.println(map.get(key));
	}
}
