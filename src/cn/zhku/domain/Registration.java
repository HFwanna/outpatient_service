package cn.zhku.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class Registration {
	private Long reg_id;
	private Long reg_phone;
	private Integer reg_serialNumber;
	private Integer reg_age;
	private String reg_patientName;
	private String reg_card;
	private Boolean reg_sex;
	private Boolean reg_state;
	private Date reg_date;
	private String reg_diseaseType;


	//表示对应处方，挂号病人对象一对多处方
	@JSONField(serialize=false)
	private Set<Prescription> prescription = new HashSet<Prescription>();
	public Set<Prescription> getPrescription() {
		return prescription;
	}
	public void setPrescription(Set<Prescription> prescription) {
		this.prescription = prescription;
	}
	
	//表示和挂号病人一对多医生诊断
	@JSONField(serialize=false)
	private Set<DiseaseType> diseaseTypes = new HashSet<DiseaseType>();
	public Set<DiseaseType> getDiseaseTypes() {
		return diseaseTypes;
	}
	public void setDiseaseTypes(Set<DiseaseType> diseaseTypes) {
		this.diseaseTypes = diseaseTypes;
	}
	public Long getReg_id() {
		return reg_id;
	}
	public void setReg_id(Long reg_id) {
		this.reg_id = reg_id;
	}
	public Long getReg_phone() {
		return reg_phone;
	}
	public void setReg_phone(Long reg_phone) {
		this.reg_phone = reg_phone;
	}
	public Integer getReg_serialNumber() {
		return reg_serialNumber;
	}
	public void setReg_serialNumber(Integer reg_serialNumber) {
		this.reg_serialNumber = reg_serialNumber;
	}
	public Integer getReg_age() {
		return reg_age;
	}
	public void setReg_age(Integer reg_age) {
		this.reg_age = reg_age;
	}
	public String getReg_patientName() {
		return reg_patientName;
	}
	public void setReg_patientName(String reg_patientName) {
		this.reg_patientName = reg_patientName;
	}
	public String getReg_card() {
		return reg_card;
	}
	public void setReg_card(String reg_card) {
		this.reg_card = reg_card;
	}
	public Boolean getReg_sex() {
		return reg_sex;
	}
	public void setReg_sex(Boolean reg_sex) {
		this.reg_sex = reg_sex;
	}
	public Boolean getReg_state() {
		return reg_state;
	}
	public void setReg_state(Boolean reg_state) {
		this.reg_state = reg_state;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getReg_diseaseType() {
		return reg_diseaseType;
	}
	public void setReg_diseaseType(String reg_diseaseType) {
		this.reg_diseaseType = reg_diseaseType;
	}
	@Override
	public String toString() {
		return "Registration [reg_id=" + reg_id + ", reg_phone=" + reg_phone
				+ ", reg_serialNumber=" + reg_serialNumber + ", reg_age="
				+ reg_age + ", reg_patientName=" + reg_patientName
				+ ", reg_card=" + reg_card + ", reg_sex=" + reg_sex
				+ ", reg_state=" + reg_state + ", reg_date=" + reg_date
				+ ", reg_diseaseType=" + reg_diseaseType + "]";
	}
	
	
	
	
	
	
	
}
