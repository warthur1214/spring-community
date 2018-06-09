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
}
