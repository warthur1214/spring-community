package com.warthur.community.wechat.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by warthur on 2018/5/10.
 */
@Data
public class PageDTO implements Serializable {
	private static final long serialVersionUID = 2174862597228242944L;

	private Integer previous;
	private Integer next;
	private Integer current;
	private Integer pageCount;
	private Integer total;
	private Integer pages;
}
