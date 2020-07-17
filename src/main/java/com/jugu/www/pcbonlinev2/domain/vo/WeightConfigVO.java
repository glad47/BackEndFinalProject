package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 重量公式表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class WeightConfigVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * PCB类型
	 */
	private Integer pcbtype;
		/**
	 * 层数
	 */
	private Integer layerNum;
		/**
	 * 成品厚度
	 */
	private Integer finishThickness;
		/**
	 * 内层铜厚
	 */
	private Integer innerLayerCopper;
		/**
	 * 外层铜厚
	 */
	private Integer outerLayerCopper;
		/**
	 * 重量
	 */
	private BigDecimal weight;
	
}
