package com.myweb.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.myweb.dao.VersionInfoMapper;
import com.myweb.entity.VersionInfo;
import com.myweb.entity.VersionInfoExample;
import com.myweb.entity.VersionInfoExample.Criteria;
import com.myweb.util.FileUploadUtils;

@Service
public class VersionInfoService {
	@Autowired
	VersionInfoMapper versionInfoMapper;
	
	//查询所有
	public List<VersionInfo> getVersionInfoList(){
		return  versionInfoMapper.selectAll();
	}
	
	public PageInfo<VersionInfo> findVersionInfoPage(VersionInfo versionInfo, int pageNum, int pageSize, String ordername, String order) {
		PageHelper.startPage(pageNum, pageSize);
		ordername = StringUtil.isNotEmpty(ordername)?ordername:"vid";
		order = StringUtil.isNotEmpty(order)?order:"desc";
		VersionInfoExample example = new VersionInfoExample();
		Criteria criteria = example.createCriteria();
		if (StringUtil.isNotEmpty(versionInfo.getVersiontype())) {
			criteria.andVersiontypeEqualTo(versionInfo.getVersiontype());
		}
		example.setOrderByClause(ordername+" "+order);
		List<VersionInfo> versionInfoList = versionInfoMapper.selectByExample(example);
		PageInfo<VersionInfo> pageInfo = new PageInfo<VersionInfo>(versionInfoList);
		return pageInfo;
	}
	
	
	public int updateByVersionInfoSelective(VersionInfo versionInfo) {
		int row = versionInfoMapper.updateByPrimaryKeySelective(versionInfo);
		return row;
	}
	
	public boolean addVersionInfo(VersionInfo versionInfo) {
		//查询vid的最大值
		Integer maxid = versionInfoMapper.selectMaxVid();
		Integer vid = maxid!=null?maxid:0;
		versionInfo.setVid(vid+1);
		int row = versionInfoMapper.insert(versionInfo);
		return row>0?true:false;
	}
	
	public int deleteVersion(List<String> values) {
		VersionInfoExample vExample = new VersionInfoExample();
		Criteria criteria =	vExample.createCriteria();
		List<Integer> intList = new ArrayList<Integer>();
		for(int i=0;values!=null && i<values.size();i++) {
			intList.add(Integer.valueOf(values.get(i)));
		}
		criteria.andVidIn(intList);
		int row = versionInfoMapper.deleteByExample(vExample);
		return row;
	}
	
	public VersionInfo getVersionInfo(VersionInfo record) {
		/*VersionInfoExample example = new VersionInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andVersiontypeEqualTo(versionType);
		criteria.andUseversionEqualTo(1);*/
		VersionInfo versionInfo = versionInfoMapper.selectOne(record);
		return versionInfo;
		
	}
	
	public List<VersionInfo> getVersoinInfoByExample(VersionInfoExample vExample) {
		return versionInfoMapper.selectByExample(vExample);
	}
}
