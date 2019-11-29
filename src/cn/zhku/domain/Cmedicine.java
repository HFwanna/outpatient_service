package cn.zhku.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Cmedicine {
	//为了回显的字段
	private Long cm_drug_id;
	public Long getCm_drug_id() {
		return cm_drug_id;
	}
	public void setCm_drug_id(Long cm_drug_id) {
		this.cm_drug_id = cm_drug_id;
	}

	private Long cm_id;
	public Long getCm_id() {
		return cm_id;
	}
	public void setCm_id(Long cm_id) {
		this.cm_id = cm_id;
	}

	//处方表多对一挂号病人表
	@JSONField(serialize=false)
	private Registration cm_registration;
	public Registration getCm_registration() {
		return cm_registration;
	}
	public void setCm_registration(Registration cm_registration) {
		this.cm_registration = cm_registration;
	}

	//表示处方多对一药物表
	//药物表有药品名称、规格、金额3个参数
	private DrugMessage2 cm_drugMessage2;
	public DrugMessage2 getCm_drugMessage2() {
		return cm_drugMessage2;
	}
	public void setCm_drugMessage2(DrugMessage2 cm_drugMessage2) {
		this.cm_drugMessage2 = cm_drugMessage2;
	}

		//一组药
		private Integer cm_group;
		//处方
		private Integer cm_prescription;
		//每次量：通过前台对象选取导入
		private String cm_mg;
		//单位：通过前台对象选取导入
		private String cm_unit;
		//用药途径：通过前台对象选取导入
		private String cm_pathway;
		//用药频率：通过前台对象选取导入
		private String cm_frequency;
		//用药天数：通过前台对象选取导入
		private String cm_days;
		//总量：通过前台对象选取导入
		private String cm_totalCount;
		
		public Integer getCm_group() {
			return cm_group;
		}
		public void setCm_group(Integer cm_group) {
			this.cm_group = cm_group;
		}
		public Integer getCm_prescription() {
			return cm_prescription;
		}
		public void setCm_prescription(Integer cm_prescription) {
			this.cm_prescription = cm_prescription;
		}
		public String getCm_mg() {
			return cm_mg;
		}
		public void setCm_mg(String cm_mg) {
			this.cm_mg = cm_mg;
		}
		public String getCm_unit() {
			return cm_unit;
		}
		public void setCm_unit(String cm_unit) {
			this.cm_unit = cm_unit;
		}
		public String getCm_pathway() {
			return cm_pathway;
		}
		public void setCm_pathway(String cm_pathway) {
			this.cm_pathway = cm_pathway;
		}
		public String getCm_frequency() {
			return cm_frequency;
		}
		public void setCm_frequency(String cm_frequency) {
			this.cm_frequency = cm_frequency;
		}
		public String getCm_days() {
			return cm_days;
		}
		public void setCm_days(String cm_days) {
			this.cm_days = cm_days;
		}
		public String getCm_totalCount() {
			return cm_totalCount;
		}
		public void setCm_totalCount(String cm_totalCount) {
			this.cm_totalCount = cm_totalCount;
		}

		
		
		//药物信息，同过pre_drugMessage获取到相应的值输入
		private String cm_drug_message;
		//药物名字，通过pre_drugMessage获取到相应的值输入
		private String cm_drug_name;
		//药物金额，通过pre_drugMessage获取到相应的值输入
		private Double cm_drug_price;
		//药物规格
		private String cm_drug_standard;
		public String getCm_drug_message() {
			return cm_drug_message;
		}
		public void setCm_drug_message(String cm_drug_message) {
			this.cm_drug_message = cm_drug_message;
		}
		public String getCm_drug_name() {
			return cm_drug_name;
		}
		public void setCm_drug_name(String cm_drug_name) {
			this.cm_drug_name = cm_drug_name;
		}
		public Double getCm_drug_price() {
			return cm_drug_price;
		}
		public void setCm_drug_price(Double cm_drug_price) {
			this.cm_drug_price = cm_drug_price;
		}
		
		
		
		public String getCm_drug_standard() {
			return cm_drug_standard;
		}
		public void setCm_drug_standard(String cm_drug_standard) {
			this.cm_drug_standard = cm_drug_standard;
		}
		@Override
		public String toString() {
			return "Cmedicine [cm_id=" + cm_id + ", cm_registration="
					+ cm_registration + ", cm_drugMessage=" + cm_drugMessage2
					+ ", cm_group=" + cm_group + ", cm_prescription="
					+ cm_prescription + ", cm_mg=" + cm_mg + ", cm_unit="
					+ cm_unit + ", cm_pathway=" + cm_pathway + ", cm_frequency="
					+ cm_frequency + ", cm_days=" + cm_days + ", cm_totalCount="
					+ cm_totalCount + ", cm_drug_message=" + cm_drug_message
					+ ", cm_drug_name=" + cm_drug_name + ", cm_drug_price="
					+ cm_drug_price + "]";
		}
		
		
		
}
