package com.warthur.community.wechat.pojo;

import com.warthur.community.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author warthur
 * @date 2018/5/10
 */
@Data
public class PageDTO implements BaseDTO {
	private static final long serialVersionUID = 2174862597228242944L;

	private Integer previous;
	private Integer next;
	private Integer current;
	private Integer pageCount;
	private Integer total;
	private Integer pages;
}
