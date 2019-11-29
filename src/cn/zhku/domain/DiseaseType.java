package cn.zhku.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class DiseaseType {
	private Long dis_id;
	//疾病类型
	private String dis_type;
	//药物限额
	private Double dis_restri_extra;
	//剩余限额
	private Double dis_resi_extra;
	//诊断
	private String dis_diagnose;
	//诊断人
	private String dis_doctor;
	//诊断时间
	private Date dis_time;
	//描述
	private String dis_description;
	
//	//一对多
//	private Set<Diagnose> diagnoses = new HashSet<Diagnose>();
	
	
//	public Set<Diagnose> getDiagnoses() {
//		return diagnoses;
//	}
//	public void setDiagnoses(Set<Diagnose> diagnoses) {
//		this.diagnoses = diagnoses;
//	}

	//医生工号不需要生成字段
	@JSONField(serialize=false)
	private Doctor doctor;
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	//通过疾病类型找到病人，一对多
	@JSONField(serialize=false)
	private Registration registration;
	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	

	public Long getDis_id() {
		return dis_id;
	}

	public void setDis_id(Long dis_id) {
		this.dis_id = dis_id;
	}

	public String getDis_type() {
		return dis_type;
	}

	public void setDis_type(String dis_type) {
		this.dis_type = dis_type;
	}

	public Double getDis_restri_extra() {
		return dis_restri_extra;
	}

	public void setDis_restri_extra(Double dis_restri_extra) {
		this.dis_restri_extra = dis_restri_extra;
	}

	public Double getDis_resi_extra() {
		return dis_resi_extra;
	}

	public void setDis_resi_extra(Double dis_resi_extra) {
		this.dis_resi_extra = dis_resi_extra;
	}

	public String getDis_diagnose() {
		return dis_diagnose;
	}

	public void setDis_diagnose(String dis_diagnose) {
		this.dis_diagnose = dis_diagnose;
	}

	public String getDis_doctor() {
		return dis_doctor;
	}

	public void setDis_doctor(String dis_doctor) {
		this.dis_doctor = dis_doctor;
	}

	public Date getDis_time() {
		return dis_time;
	}

	public void setDis_time(Date dis_time) {
		this.dis_time = dis_time;
	}

	public String getDis_description() {
		return dis_description;
	}

	public void setDis_description(String dis_description) {
		this.dis_description = dis_description;
	}
	//转换日期格式输出到前台页面
	private String dis_time_s;

	public String getDis_time_s() {
		return dis_time_s;
	}

	public void setDis_time_s(String dis_time_s) {
		this.dis_time_s = dis_time_s;
	}

	@Override
	public String toString() {
		return "DiseaseType [dis_id=" + dis_id + ", dis_type=" + dis_type
				+ ", dis_restri_extra=" + dis_restri_extra + ", dis_resi_extra="
				+ dis_resi_extra + ", dis_diagnose=" + dis_diagnose
				+ ", dis_doctor=" + dis_doctor + ", dis_time=" + dis_time
				+ ", dis_description=" + dis_description + ", registration="
				+ registration + ", dis_time_s=" + dis_time_s + "]";
	}
	
	
	
}
