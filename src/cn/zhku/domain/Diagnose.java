package cn.zhku.domain;

public class Diagnose {
	private String ops_diagnose;

	public String getOps_diagnose() {
		return ops_diagnose;
	}

	public void setOps_diagnose(String ops_diagnose) {
		this.ops_diagnose = ops_diagnose;
	}
	
//写完了写了diseastype类的一个一对多个diagnose   ，diagnose.hbm和diseasType.hbm内容并没改变，还有这个类也没有改变，改变这两个类之后，
	//就完成了汪diseastYpe中添加了一个关联到diagnse的字段，再回到AjaxDiseaseType84行后面直接往 list的每个里面diagnose关联字段增加一个一整个diagnose
	//然后前台获取数据的时候，直接把response[i].diagnose.length 循环，把值给高diagnose对应的field字段域就行了！
}
