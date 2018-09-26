package com.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.myweb.config.util.ConfigUtil;
import com.myweb.entity.Operation;
import com.myweb.entity.VersionInfo;
import com.myweb.entity.VersionInfoExample;
import com.myweb.entity.VersionInfoExample.Criteria;
import com.myweb.exception.FileNameLengthLimitExceededException;
import com.myweb.exception.InvalidExtensionException;
import com.myweb.service.OperationService;
import com.myweb.service.VersionInfoService;
import com.myweb.util.FileUploadUtils;
import com.myweb.util.PropertiesUtil;
import com.myweb.util.RedisOps;
import com.myweb.util.StringUtil;
import com.myweb.util.WriterUtil;

@RequestMapping("version")
@Controller
public class VersionController {

	@Autowired
	private OperationService operationService;
	@Autowired
	private VersionInfoService versionInfoService;
	static Logger logger = Logger.getLogger(VersionController.class);
	
	@RequestMapping("versionIndex")
	public String index(HttpServletRequest request,HttpServletResponse response,Integer menuid) {
		List<Operation> operationList = operationService.findOperationIdsByMenuid(menuid);
		request.setAttribute("operationList", operationList);
		return "version";
	}
	
	@RequestMapping(value="versionList",method=RequestMethod.POST)
	public void versionList(HttpServletRequest request,HttpServletResponse response,String offset,String limit) throws Exception{
		try {
			VersionInfo versionInfo = new VersionInfo();
			String order = request.getParameter("order");
			String ordername = request.getParameter("ordername");
			String versiontype = request.getParameter("versiontype");
			
			if (StringUtil.isNotEmpty(versiontype)) {
				versionInfo.setVersiontype(versiontype);
			}
			
			Integer pageSize = StringUtil.isEmpty(limit)?ConfigUtil.getPageSize():Integer.parseInt(limit);
			Integer pageNum =  (Integer.parseInt(offset)/pageSize)+1;
			PageInfo<VersionInfo> userList= versionInfoService.findVersionInfoPage(versionInfo,pageNum,pageSize,ordername,order);
			
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("total",userList.getTotal() );
			jsonObj.put("rows", userList.getList());
	        WriterUtil.write(response,jsonObj.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("版本展示错误",e);
			throw e;
		}
	}
	
	// 新增或修改
	@SuppressWarnings("unchecked")
	@RequestMapping("reserveVersion")
	public void reserveUser(HttpServletRequest request,VersionInfo versionInfo,HttpServletResponse response){
		Integer vId = versionInfo.getVid();
		JSONObject result=new JSONObject();
		Date date = new Date();
		versionInfo.setReleasedate(date);
		int row = 0;
		try {
			Object object = RedisOps.hmget(PropertiesUtil.forceUpdateVersion);
			HashMap<String, String> forceUpdateMap = null;
			if (object!=null) {
				forceUpdateMap = (HashMap<String, String>)object;
			}
			List<String> sList = new ArrayList<>();
			sList.add(versionInfo.getVersiontype());
			sList.add(versionInfo.getVersion());
			sList.add(versionInfo.getUrl());
			sList.add(versionInfo.getReleasename());
			sList.add(versionInfo.getForceupdate());
			sList.add(versionInfo.getTemp());
			if (versionInfo.getUseversion()==1) {
				RedisOps.del(PropertiesUtil.version+versionInfo.getVersiontype());
				RedisOps.setObject(PropertiesUtil.version+versionInfo.getVersiontype(), sList);
			}
			if (forceUpdateMap==null) {
				forceUpdateMap = new HashMap<>();
			}
			if (versionInfo.getForceupdate().equals("1")) {
				forceUpdateMap.put(versionInfo.getVersion(), "1");
				RedisOps.hmset(PropertiesUtil.forceUpdateVersion, forceUpdateMap);
			}else {
				RedisOps.hdel(PropertiesUtil.forceUpdateVersion, versionInfo.getVersion());
			}
			if (vId != null) { // vId不为空 说明是修改
				VersionInfoExample example = new VersionInfoExample();
				Criteria criteria = example.createCriteria();
				criteria.andVersiontypeEqualTo(versionInfo.getVersiontype());
				criteria.andUseversionEqualTo(1);
				criteria.andVidNotEqualTo(vId);
				List<VersionInfo> tVersionInfo = versionInfoService.getVersoinInfoByExample(example);
				if (tVersionInfo!=null&&tVersionInfo.size()>0 && versionInfo.getUseversion()==1) {
					throw new Exception("已经有一个版本正在启用");
				}
				row = versionInfoService.updateByVersionInfoSelective(versionInfo);
				if (row > 0) {
					
					result.put("success", true);
				}else {
					result.put("success", false);
					result.put("errorMsg", "修改失败！");
				}
			}else {   // 添加
				versionInfoService.addVersionInfo(versionInfo);
				result.put("success", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("保存版本信息错误",e);
			result.put("success", true);
			result.put("errorMsg", e.getMessage());
		}
		WriterUtil.write(response, result.toString());
	}
	
	/**
	  * 删除版本信息，先删除系统文件
	 * @param request
	 * @param response
	 */
	@RequestMapping("deleteVersion")
	public void delVersion(HttpServletRequest request,HttpServletResponse response){
		JSONObject result=new JSONObject();
		try {
			String[] ids=request.getParameter("ids").split(",");
			VersionInfo versionInfo = new VersionInfo();
			
			for(int i=0;i<ids.length;i++) {
				VersionInfo record = new VersionInfo();
				record.setVid(Integer.valueOf(ids[i]));
				versionInfo = versionInfoService.getVersionInfo(record);
				if (versionInfo.getUseversion()==1) {
					RedisOps.del(PropertiesUtil.version+versionInfo.getVersiontype());
				}
				if (versionInfo.getForceupdate().equals("1")) {
					RedisOps.hdel(PropertiesUtil.forceUpdateVersion, versionInfo.getVersion());
				}
			}
			
			versionInfoService.deleteVersion(Arrays.asList(ids));
			result.put("success", true);
			result.put("delNums", ids.length);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除版本信息错误",e);
			result.put("errorMsg", "对不起，删除失败");
		}
		WriterUtil.write(response, result.toString());
	}
	
	/**
	 * 文件上传
	 * @param request
	 * @param response
	 */
	@RequestMapping("uploadVersion")
	public void uploadVersion(HttpServletRequest request,HttpServletResponse response) {
		JSONObject result=new JSONObject();
		try {
			response.setContentType("text/plain");
			String vid = request.getParameter("vid");
			String versiontype = request.getParameter("versiontype");
			String version = request.getParameter("version");
			String releasename = request.getParameter("releasename");
			if (!StringUtils.isNullOrEmpty(versiontype) && versiontype.equals("1")) {
				versiontype = "android";
			}else {
				versiontype = "iphone";
			}
			String baseDir =FileUploadUtils.getDefaultBaseDir().concat("/").concat(versiontype).concat("/").concat(version);
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			if (multipartResolver.isMultipart(request)) { // 判断request是否有文件上传
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> ite = multiRequest.getFileNames();
				while (ite.hasNext()) {
					MultipartFile file = multiRequest.getFile(ite.next());
					try {
						FileUploadUtils.upload(request,baseDir,file,
								FileUploadUtils.DEFAULT_ALLOWED_EXTENSION, FileUploadUtils.DEFAULT_MAX_SIZE,
								Boolean.FALSE);
						releasename = file.getOriginalFilename();
						
						//continue;
					} catch (IOException e) {
						logger.error("文件上传失败",e);
						result.put("success", false);
						result.put("msg", "upload.server.error");
						//continue;
					} catch (InvalidExtensionException e) {
						logger.error("文件上传失败",e);
						result.put("success", false);
						result.put("msg", "upload.server.error");
						//continue;
					} catch (FileUploadBase.FileSizeLimitExceededException e) {
						logger.error("文件上传失败",e);
						result.put("success", false);
						result.put("msg", "upload.server.error");
						//continue;
					} catch (FileNameLengthLimitExceededException e) {
						logger.error("文件上传失败",e);
						result.put("success", false);
						result.put("msg", "upload.server.error");
						//continue;
					}
				}
				HashMap<String, String> forceUpdateMap = new HashMap<>();
				forceUpdateMap = (HashMap<String, String>) RedisOps.hmget(PropertiesUtil.forceUpdateVersion);
				
				//修改versioninfo表中的url
				VersionInfo versionInfo = new VersionInfo();
				versionInfo.setVid(Integer.valueOf(vid));
				versionInfo.setUrl(baseDir);
				versionInfo.setReleasename(releasename);
				versionInfoService.updateByVersionInfoSelective(versionInfo);
				
				VersionInfo record = new VersionInfo();
				record.setVid(Integer.valueOf(vid));
				record = versionInfoService.getVersionInfo(record);
				List<String> sList = new ArrayList<>();
				sList.add(record.getVersiontype());
				sList.add(record.getVersion());
				sList.add(record.getUrl());
				sList.add(record.getReleasename());
				sList.add(record.getForceupdate());
				sList.add(record.getTemp());
				if (record.getUseversion()==1) {
					RedisOps.del(PropertiesUtil.version+record.getVersiontype());
					RedisOps.setObject(PropertiesUtil.version+record.getVersiontype(), sList);
				}
				if (record.getForceupdate().equals("1")) {
					if (forceUpdateMap==null) {
						forceUpdateMap = new HashMap<>();
					}
					forceUpdateMap.put(versionInfo.getVersion(), "1");
					RedisOps.hmset(PropertiesUtil.forceUpdateVersion, forceUpdateMap);
				}
				result.put("success", true);
				result.put("msg", "上传成功!");
			} else {
				result.put("msg", "upload.server.error");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("上传文件出错",e);
		}
		WriterUtil.write(response, result.toString());
	}
	
	@RequestMapping(value = "/downloadmyfile", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadmyfile(HttpServletRequest request) throws IOException {
    	ResponseEntity<byte[]> responseEntity = null;
    	try {
			String versiontype = request.getParameter("versiontype");
			String url = request.getParameter("url");
			String releasename = request.getParameter("releasename");
			if (!StringUtils.isNullOrEmpty(versiontype) && versiontype.equals("1")) {
				versiontype = "android";
			}else {
				versiontype = "iphone";
			}
			String uploadDir = request.getServletContext().getRealPath("/");
			uploadDir = new File(uploadDir).getParent();
			uploadDir = new File(uploadDir).getParent();
			String filepath = uploadDir+"/"+url+"/"+releasename;
			File file = new File(filepath);
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("UTF-8"), "ISO8859-1"));
			responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseEntity;
    }
    
}
