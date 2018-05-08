package com.warthur.common.wechat.receive;

import com.warthur.common.wechat.WxBaseAuth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by warth on 2018/3/13.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WxUnAuthEvent extends WxBaseAuth {
	private static final long serialVersionUID = -8854003666709633319L;

	@XmlElement(name = "AuthorizerAppid")
	private String authorizerAppid;
}
