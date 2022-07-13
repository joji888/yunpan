package com.qst.yunpan.dao;

import org.apache.ibatis.annotations.Param;

public interface OfficeDao {

    void addOffice(@Param("officeId") String officeId, @Param("officeMd5") String officeMd5) throws Exception;

    String getOfficeId(String officeMd5) throws Exception;
    
}
