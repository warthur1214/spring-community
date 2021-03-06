package com.warthur.community.common.entity;

import com.fasterxml.jackson.annotation.JsonValue;
import com.warthur.community.common.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * data response
 * @author warthur
 * @date 2018/5/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResponse extends Response {
	private static final long serialVersionUID = -5063369357722670417L;

	private BaseDTO data;

	public DataResponse(Error res, BaseDTO data) {
		super(res.entity().getStatus(), res.entity().getMessage());
		this.data = data;
	}
}
