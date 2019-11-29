package cn.zhku.domain;

import com.alibaba.fastjson.annotation.JSONField;

public class Prescription {
	//为了回显的字段
	private Long pre_drug_id;
	public Long getPre_drug_id() {
		return pre_drug_id;
	}
	public void setPre_drug_id(Long pre_drug_id) {
		this.pre_drug_id = pre_drug_id;
	}


	private Long pre_id;
	public Long getPre_id() {
		return pre_id;
	}

	public void setPre_id(Long pre_id) {
		this.pre_id = pre_id;
	}
	
	
	//处方表多对一挂号病人表
	@JSONField(serialize=false)
	private Registration pre_registration;
	public Registration getPre_registration() {
		return pre_registration;
	}

	public void setPre_registration(Registration pre_registration) {
		this.pre_registration = pre_registration;
	}
	
	//表示处方多对一药物表
	//药物表有药品名称、规格、金额3个参数
	private DrugMessage pre_drugMessage;
	public DrugMessage getPre_drugMessage() {
		return pre_drugMessage;
	}

	public void setPre_drugMessage(DrugMessage pre_drugMessage) {
		this.pre_drugMessage = pre_drugMessage;
	}
	
	
	
		//一组药
		private Integer pre_group;
		//处方
		private Integer pre_prescription;
		//每次量：通过前台对象选取导入
		private String pre_mg;
		//单位：通过前台对象选取导入
		private String pre_unit;
		//用药途径：通过前台对象选取导入
		private String pre_pathway;
		//用药频率：通过前台对象选取导入
		private String pre_frequency;
		//用药天数：通过前台对象选取导入
		private String pre_days;
		//总量：通过前台对象选取导入
		private String pre_totalCount;
		
		
		public Integer getPre_group() {
			return pre_group;
		}

		public void setPre_group(Integer pre_group) {
			this.pre_group = pre_group;
		}

		public Integer getPre_prescription() {
			return pre_prescription;
		}

		public void setPre_prescription(Integer pre_prescription) {
			this.pre_prescription = pre_prescription;
		}

		public String getPre_mg() {
			return pre_mg;
		}

		public void setPre_mg(String pre_mg) {
			this.pre_mg = pre_mg;
		}

		public String getPre_unit() {
			return pre_unit;
		}

		public void setPre_unit(String pre_unit) {
			this.pre_unit = pre_unit;
		}

		public String getPre_pathway() {
			return pre_pathway;
		}

		public void setPre_pathway(String pre_pathway) {
			this.pre_pathway = pre_pathway;
		}

		public String getPre_frequency() {
			return pre_frequency;
		}

		public void setPre_frequency(String pre_frequency) {
			this.pre_frequency = pre_frequency;
		}

		public String getPre_days() {
			return pre_days;
		}

		public void setPre_days(String pre_days) {
			this.pre_days = pre_days;
		}

		public String getPre_totalCount() {
			return pre_totalCount;
		}

		public void setPre_totalCount(String pre_totalCount) {
			this.pre_totalCount = pre_totalCount;
		}
		
		//药物信息，同过pre_drugMessage获取到相应的值输入
		private String pre_drug_message;
		//药物名字，通过pre_drugMessage获取到相应的值输入
		private String pre_drug_name;
		//药物金额，通过pre_drugMessage获取到相应的值输入
		private Double pre_drug_price;
		//药物规格
		private String pre_drug_standard;
		
		public String getPre_drug_standard() {
			return pre_drug_standard;
		}
		public void setPre_drug_standard(String pre_drug_standard) {
			this.pre_drug_standard = pre_drug_standard;
		}
		public Double getPre_drug_price() {
			return pre_drug_price;
		}

		public void setPre_drug_price(Double pre_drug_price) {
			this.pre_drug_price = pre_drug_price;
		}

		public String getPre_drug_message() {
			return pre_drug_message;
		}

		public void setPre_drug_message(String pre_drug_message) {
			this.pre_drug_message = pre_drug_message;
		}

		public String getPre_drug_name() {
			return pre_drug_name;
		}

		public void setPre_drug_name(String pre_drug_name) {
			this.pre_drug_name = pre_drug_name;
		}
		
		//医生工号不需要生成字段
		@JSONField(serialize=false)
		private Doctor doctor;
		public Doctor getDoctor() {
			return doctor;
		}
		public void setDoctor(Doctor doctor) {
			this.doctor = doctor;
		}

		@Override
		public String toString() {
			return "Prescription [pre_id=" + pre_id + ", pre_registration="
					+ pre_registration + ", pre_drugMessage=" + pre_drugMessage
					+ ", pre_group=" + pre_group + ", pre_prescription="
					+ pre_prescription + ", pre_mg=" + pre_mg + ", pre_unit="
					+ pre_unit + ", pre_pathway=" + pre_pathway
					+ ", pre_frequency=" + pre_frequency + ", pre_days="
					+ pre_days + ", pre_totalCount=" + pre_totalCount
					+ ", pre_drug_message=" + pre_drug_message
					+ ", pre_drug_name=" + pre_drug_name + ", pre_drug_price="
					+ pre_drug_price + "]";
		}
		
		
	
	

	


	
	
}
