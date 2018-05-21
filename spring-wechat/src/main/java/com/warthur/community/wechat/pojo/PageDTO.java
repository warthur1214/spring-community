package com.warthur.community.wechat.pojo;

import com.warthur.community.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by warthur on 2018/5/10.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageDTO extends BaseDTO {
	private static final long serialVersionUID = 2174862597228242944L;

	private Integer previous;
	private Integer next;
	private Integer current;
	private Integer pageCount;
	private Integer total;
	private Integer pages;
}
