package com.example.Jpa.Study.param;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class addOrderDetailParam {
	private long id;
	@NotEmpty(message = "商品名称不能为空")
	private String itemName;
	@NotEmpty(message = "商品数量不能为空")
	@Max(value = 20,message = "最大订购数量为20")
	@Min(value = 1,message = "最小订购数量为1")
	private long itemNum;
}
