package cn.zhku.domain;

import java.util.HashSet;
import java.util.Set;

public class Doctor {
	/*<hibernate-mapping package="cn.zhku.domain">
    	<class name="Doctor" table="ops_doctor">
    		<id name="doc_id" column="doc_id">
    			<generator class="native"></generator>
    		</id>
    		<property name="doc_name" column="doc_name"></property>
    		<property name="doc_password" column="doc_password"></property>
    		<property name="doc_fee" column="doc_fee"></property>
    		
    		<!-- 诊断表可以维护医生的，也可以通过医生找到诊断信息 -->
    		<set name="diseaseTypes" cascade="save-update">
    			<key column="doc_id"></key>
    			<one-to-many class="DiseaseType"/>
    		</set>
    		<set name="prescriptions" cascade="save-update">
    			<key column="doc_id"></key>
    			<one-to-many class="Prescription"/>
    		</set>
    	</class>
	 * */
	private Long doc_id;
	private String doc_name;
	private String doc_password;
	private Integer doc_fee;
	private Set<DiseaseType> diseaseTypes = new HashSet<DiseaseType>();
	private Set<Prescription> prescriptions = new HashSet<Prescription>();
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}
	
	public Long getDoc_id() {
		return doc_id;
	}

	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_password() {
		return doc_password;
	}
	public void setDoc_password(String doc_password) {
		this.doc_password = doc_password;
	}
	
	
	public Integer getDoc_fee() {
		return doc_fee;
	}

	public void setDoc_fee(Integer doc_fee) {
		this.doc_fee = doc_fee;
	}

	public Set<DiseaseType> getDiseaseTypes() {
		return diseaseTypes;
	}
	public void setDiseaseTypes(Set<DiseaseType> diseaseTypes) {
		this.diseaseTypes = diseaseTypes;
	}

	public Set<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
	
	
}
