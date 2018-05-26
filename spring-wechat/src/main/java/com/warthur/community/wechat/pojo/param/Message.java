package com.warthur.community.wechat.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by warthur on 2018/5/10.
 */
@Data
public class Message implements Serializable {

	private static final long serialVersionUID = 6507153902629755688L;
	@Pattern(regexp = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$", groups = {MsgSend.class})
	private String tel;

	private String openId;

	private String smsCode;

	@NotEmpty(groups = {MsgSend.class})
	private String code;

	public interface MsgSend {}
}
