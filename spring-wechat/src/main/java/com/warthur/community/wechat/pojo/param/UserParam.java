package com.warthur.community.wechat.pojo.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by warthur on 2018/5/8.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserParam implements Serializable {
	private static final long serialVersionUID = 2129857247737284879L;

	private String tel;
	private String smsCode;
	private String code;
}
