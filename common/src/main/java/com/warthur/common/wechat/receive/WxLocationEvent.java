package com.warthur.common.wechat.receive;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/4/3.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxEventMsg.class
})
@Data
public class WxLocationEvent extends WxEventMsg {

	private static final long serialVersionUID = 7565173296202786315L;

	@XmlElement(name = "Latitude")
	private String latitude;
	@XmlElement(name = "Longitude")
	private String longitude;
	@XmlElement(name = "Precision")
	private float precision;
}
