package cn.zhku.domain;

import java.util.HashSet;
import java.util.Set;

import com.alibaba.fastjson.annotation.JSONField;

public class DrugMessage2 {
	private Long drug_id;
	private String drug_name;
	private String drug_message;
	//库存
	private String drug_repertory;
	private Double drug_price;
	//医保类型
	private Boolean drug_type;
	//药物规格
	private String drug_standard;
	
	//配置药品表一对多处方
	@JSONField(serialize=false)
	private Set<Cmedicine> cmedicines = new HashSet<Cmedicine>();
	public Set<Cmedicine> getCmedicines() {
		return cmedicines;
	}
	public void setCmedicines(Set<Cmedicine> cmedicines) {
		this.cmedicines = cmedicines;
	}

	public Long getDrug_id() {
		return drug_id;
	}

	public void setDrug_id(Long drug_id) {
		this.drug_id = drug_id;
	}

	public String getDrug_name() {
		return drug_name;
	}

	public void setDrug_name(String drug_name) {
		this.drug_name = drug_name;
	}

	public String getDrug_message() {
		return drug_message;
	}

	public void setDrug_message(String drug_message) {
		this.drug_message = drug_message;
	}

	public String getDrug_repertory() {
		return drug_repertory;
	}

	public void setDrug_repertory(String drug_repertory) {
		this.drug_repertory = drug_repertory;
	}

	public Double getDrug_price() {
		return drug_price;
	}

	public void setDrug_price(Double drug_price) {
		this.drug_price = drug_price;
	}

	public Boolean getDrug_type() {
		return drug_type;
	}

	public void setDrug_type(Boolean drug_type) {
		this.drug_type = drug_type;
	}

	public String getDrug_standard() {
		return drug_standard;
	}

	public void setDrug_standard(String drug_standard) {
		this.drug_standard = drug_standard;
	}
	
	
	
}
