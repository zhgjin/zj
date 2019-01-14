package cn.sky999.entity;

import cn.sky999.annotation.Condition;
import cn.sky999.annotation.ConditionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 代码信息
 * 
 */
@Table(name = "t_sys_code_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeInfo implements Serializable  {
	/**
	 * 
	 */
	@Id
	@Column(name = "INFO_ID", nullable = false, length = 32)
	private String infoId;//
	@Column(name = "TYPE_ID", nullable = true, length = 32)
	private String typeId;// 代码类型
	@Column(name = "INFO_VALUE", nullable = true, length = 128)
	private String infoValue;// 值
	@Column(name = "INFO_CONTENT", nullable = true, length = 128)
	@Condition(value= ConditionType.LIKE)
	private String infoContent;// 内容
	@Column(name = "SORT_NO", nullable = true, length = 6)
	private Integer sortNo;// 排序号
	@Column(name = "REMARK", nullable = true, length = 128)
	private String remark;// 备注
}
