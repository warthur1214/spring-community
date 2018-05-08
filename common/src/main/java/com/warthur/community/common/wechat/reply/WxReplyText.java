package com.warthur.community.common.wechat.reply;

import com.warthur.community.common.wechat.WxBaseMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

/**
 * Created by warth on 2018/4/4.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({
		WxBaseMsg.class
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxReplyText extends WxBaseMsg {
	private static final long serialVersionUID = 279779062487347031L;

	@XmlElement(name = "Content")
	private String content;

	public WxReplyText(WxBaseMsg baseMsg, String content) {
		super(baseMsg, "text");
		this.content = content;
	}
}
