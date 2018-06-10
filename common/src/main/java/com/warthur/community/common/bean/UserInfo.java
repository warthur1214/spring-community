package com.warthur.community.common.bean;

import com.warthur.community.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
 */
@Data
public class UserInfo implements BaseDTO {

    private static final long serialVersionUID = -5451567723505258159L;

    private String userId;
    private String openId;
    private String unionId;
    private String userName;
    private String headImage;
    private String mobile;
    private String secret;
}
