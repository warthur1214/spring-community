package com.warthur.wechat.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by warthur on 2018/5/8.
 */
@Data
public class BaseParam implements Serializable {
	private static final long serialVersionUID = -2452727317172816211L;

	private String signaure;
}
