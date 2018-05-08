package com.warthur.common.wechat.receive;

import com.warthur.common.wechat.WxBaseAuth;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/4/4.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxBaseAuth.class
})
@Data
public class WxRecvTicket extends WxBaseAuth {

	private static final long serialVersionUID = -1178446072037362282L;

	@XmlElement(name = "ComponentVerifyTicket")
	private String componentVerifyTicket;

}
