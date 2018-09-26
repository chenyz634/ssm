package com.myweb.dao;

import com.myweb.base.dao.BaseMapper;
import com.myweb.entity.Log;

public interface LogMapper extends BaseMapper<Log> {

	void truncateTable();
}