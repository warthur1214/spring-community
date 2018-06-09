package com.warthur.community.common;

import com.warthur.community.common.framework.exception.CommunityException;

/**
 * @author warthur
 */
public interface ErrorEntity {

    /**
     * get this object
     * @return this
     */
    CommunityException exception();

    /**
     * response data
     * @param baseDTO data
     * @return response
     */
    Response success(BaseDTO baseDTO);

    /**
     * @return response
     */
    Response success();
}
