package com.myweb.dao;

import com.myweb.base.dao.BaseMapper;
import com.myweb.entity.VersionInfo;
import com.myweb.entity.VersionInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VersionInfoMapper extends BaseMapper<VersionInfo> {
    long countByExample(VersionInfoExample example);

    int deleteByExample(VersionInfoExample example);

    List<VersionInfo> selectByExample(VersionInfoExample example);

    int updateByExampleSelective(@Param("record") VersionInfo record, @Param("example") VersionInfoExample example);

    int updateByExample(@Param("record") VersionInfo record, @Param("example") VersionInfoExample example);
    
    int selectMaxVid();
}