package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价加工时间配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteUrgentBuildVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 平米id
	 */
	private Integer areaId;
		/**
	 * 加急类型id
	 */
	private Integer urgentTypeId;
		/**
	 * 层数id
	 */
	private Integer layerNumberId;
		/**
	 * 建造天数
	 */
	private String dayNumber;
		/**
	 * 费用
	 */
	private BigDecimal price;
	
}
