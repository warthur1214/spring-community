package com.warthur.wechat.pojo.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by warthur on 2018/5/8.
 */
@Data
public class LoginParam implements Serializable {
	private static final long serialVersionUID = 2129857247737284879L;

	private String tel;
	private String smsCode;
	private String code;
}
