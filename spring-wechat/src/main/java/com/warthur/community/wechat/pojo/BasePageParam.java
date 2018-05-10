package com.warthur.community.wechat.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by warthur on 2018/5/10.
 */
@Data
public class BasePageParam implements Serializable {
	private static final long serialVersionUID = -7583369231481156915L;

	private Integer pageNum;
	private Integer pageCount;
}
