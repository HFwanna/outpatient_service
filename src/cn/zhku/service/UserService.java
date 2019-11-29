package cn.zhku.service;

import java.util.List;

import javax.annotation.Resource;
import javax.print.Doc;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.zhku.dao.UserDao;
import cn.zhku.domain.Customer;
import cn.zhku.domain.Doctor;
import cn.zhku.domain.User;
import cn.zhku.utils.MD5Utils;
import cn.zhku.utils.PageBean;

@Service("userService")
@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED,readOnly=false)
public class UserService {
	@Resource(name="userDao")
	UserDao userDao;

	public void setUserDao(UserDao ud) {
		this.userDao = ud;
	}
	
	public Doctor getByUsername(Doctor u) {
		//查询返回User
		Doctor existU = userDao.getByUsername(u);
		//判断返回的existU是否为空
		if (existU==null) {
			throw new RuntimeException("用户名不存在");
		}
		//判断existU中的password是否是正确，因为数据库中存储的是md5加密的数据，所以前台获取密码也要用md5加密再检验32位字符串是否一致
		if(!existU.getDoc_password().equals(MD5Utils.md5(u.getDoc_password()))) {
			throw new RuntimeException("密码错误！");
		}
		
		return existU;
	}
	

	public void saveUsercode(Doctor doctor) {
		//如果用户id等于空才是注册页调用的方法，否则是修改也面更新方法，
		if (doctor.getDoc_id()==null) {
			// 获取数据库中的usercode
			Doctor exitD = userDao.getByUsername(doctor);
			//判断是否存在这个用户名字
			if (exitD != null) {
				//有这个用户了
				throw new RuntimeException("用户名字已经被使用！");
			} 
		}
			//利用md5加密方法把前台用户密码加密后再存入数据库
			doctor.setDoc_password(MD5Utils.md5(doctor.getDoc_password()));
			userDao.saveOrUpdate(doctor);
		
	}

	public PageBean getPageBean(DetachedCriteria dc, Integer pageSize,
			Integer currentPage) {
		//调用dao查询总记录数
		Integer totalCount = userDao.getTotalCount(dc);
		//调用PageBean的构造方法处理currentPage保证不溢出，还有把customerList和pageCount也封装进来
		PageBean pb = new PageBean(currentPage,pageSize,totalCount);
		//调用dao查询cust_name的模糊查询，返回customerList
		List<Doctor> list = userDao.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//返回PageBean对象
		return pb;
	}

	public Doctor getById(Long user_id) {
		return userDao.getById(user_id);
	}

	public User deleteById(Long user_id) {
		userDao.delete(user_id);
		return null;
	}
}
