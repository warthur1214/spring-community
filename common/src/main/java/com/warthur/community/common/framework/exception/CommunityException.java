package com.warthur.community.common.framework.exception;

import com.warthur.community.common.entity.Error;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author warthur
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommunityException extends BaseException {

	private static final long serialVersionUID = -1535065562881117634L;

	public CommunityException(Error error) {
		super(error);
	}
}
