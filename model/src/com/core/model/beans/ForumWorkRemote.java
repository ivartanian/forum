package com.core.model.beans;

import javax.ejb.Remote;

/**
 * Created by i.vartanian on 24.12.2014.
 */
@Remote
public interface ForumWorkRemote {

    public Object queryByRange(String stmt, int firstResult, int maxResult);

}
