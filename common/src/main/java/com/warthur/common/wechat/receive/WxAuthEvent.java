package com.warthur.common.wechat.receive;

import com.warthur.common.wechat.WxBaseAuth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/3/12.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxBaseAuth.class
})
@Data
public class WxAuthEvent extends WxBaseAuth {

	private static final long serialVersionUID = 9191093470655769116L;

	@XmlElement(name = "AuthorizerAppid")
	private String authorizerAppid;
	@XmlElement(name = "AuthorizationCode")
	private String authorizationCode;
	@XmlElement(name = "PreAuthCode")
	private String preAuthCode;
	@XmlElement(name = "AuthorizationCodeExpiredTime")
	private String authorizationCodeExpiredTime;
}
