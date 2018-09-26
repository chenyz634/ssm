package com.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.StringUtils;
import com.myweb.tag.AjaxJson;
import com.myweb.util.FileDownloadUtil;
import com.myweb.util.PropertiesUtil;
import com.myweb.util.RedisOps;

/**
 * @author 
 *
 */
@Controller
@RequestMapping("autoDownload")
public class DownloadController {

    //private static final Logger log = LoggerFactory.getLogger(DownloadController.class);
	private static Logger logger = Logger.getLogger(DownloadController.class);
    
    public static void getFiles(File path, List<File> fileMap) {
        if (!path.exists()) {
        	logger.error("## file is null.");
        } else {
            if (path.isFile()) {
                fileMap.add(path);
            } else {
                File[] files = path.listFiles();
                for (int i = 0; i < files.length; i++) {
                    getFiles(files[i], fileMap);
                }
            }
        }
    }

    //检查当前最新版本(因为1.0.0版本用的是这个接口，所以该接口不能删除)
	@RequestMapping(value="getLeastVersion",method = RequestMethod.GET)
    public @ResponseBody AjaxJson getTheLeastVersion(@RequestParam("equipmentType") String equipmentType, HttpServletRequest request) {
    	DownloadController downloadController = new DownloadController();
    	return downloadController.getTheNewVersion(equipmentType,"1.0.0");
    }

	
	//检查当前最新版本(1.0.0版本以上用该接口)
	@RequestMapping(value="getNewVersion",method = RequestMethod.GET)
    public @ResponseBody AjaxJson getTheLeastVersion(@RequestParam("equipmentType") String equipmentType,@RequestParam("preVersion") String preVersion, HttpServletRequest request) {
		DownloadController downloadController = new DownloadController();
    	return downloadController.getTheNewVersion(equipmentType,preVersion);
    }
	
	@SuppressWarnings("unchecked")
	public AjaxJson getTheNewVersion(String equipmentType,String preVersion) {
		AjaxJson json = new AjaxJson();
		try {
			List<String> list = (List<String>) RedisOps.getObject(PropertiesUtil.version+equipmentType);
			HashMap<String, String> forceUpdateMap = new HashMap<>();
			forceUpdateMap = (HashMap<String, String>) RedisOps.hmget(PropertiesUtil.forceUpdateVersion);
			boolean forceupdate = false;
			//拼装文件下载路径
			String filepath = "";
			String releasename = "";
			String version = "";
			if (list!=null && list.size()==6) {
				version = list.get(1);
				releasename = list.get(3);
			}
			if (equipmentType.equals("1")) {
				filepath = PropertiesUtil.androidDownUrl;
			}else {
				filepath = PropertiesUtil.iosDownUrl;
			}
			filepath = filepath+"/"+version+"/"+releasename;
			//判断是否需要强制更新，如果用户跳过了中间的一个强制更新版本，即使最新版本不是强制更新，那么用户仍然需要强制更新
			if (list!=null && list.size()==6) { 
				for(Map.Entry<String, String> entry:forceUpdateMap.entrySet()) {
					if (entry.getKey().compareTo(preVersion)>0) {
						forceupdate = true;
						break;
					}
				}
				
				json.put("version", list.get(1));
				json.put("url", filepath);
				json.put("forceupdate", forceupdate?"1":list.get(4));
				json.put("description", list.get(5));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return json;
	}
	
    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ResponseEntity<byte[]> downloadfile(@RequestParam("filepath") String filepath) throws IOException {
 
        File file = new File(filepath);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", new String(file.getName().getBytes("UTF-8"), "ISO8859-1"));
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/getfile", method = RequestMethod.POST)
    public void downloadfile(HttpServletRequest request) throws IOException {
    	try {
    		String versiontype = request.getParameter("versiontype");
    		String url = request.getParameter("url");
    		String releasename = request.getParameter("releasename");
    		if (!StringUtils.isNullOrEmpty(versiontype) && versiontype.equals("1")) {
    			versiontype = "android";
    		}else {
    			versiontype = "iphone";
    		}
    		String contextPath = request.getContextPath();
    		String baseDir = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
        	String filepath = baseDir+url+"/"+releasename;
            
        	final FileDownloadUtil downloadUtil = new FileDownloadUtil(filepath, releasename, 4);
			downloadUtil.downLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/downloadAn", method = RequestMethod.GET)
    public void downloadAn(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	try {
    		Date now = new Date( );
    	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
    	    logger.debug("downloadAn,start:"+ft.format(now));
    	    @SuppressWarnings("unchecked")
    	    List<String> list = (List<String>) RedisOps.getObject(PropertiesUtil.androidVersion);
    	    //String filepath = "http://ssm-down.galanz.com.cn/autoupload/android";
    	    String filepath = PropertiesUtil.androidDownUrl;
			String releasename = "";
			String version = "";
			if (list!=null && list.size()==6) {
				version = list.get(1);
				releasename = list.get(3);
			}	
			filepath = filepath+"/"+version+"/"+releasename;
			response.setStatus(302);
			response.setHeader("Location",filepath);
			//---分割线---
			//response.sendRedirect(filepath);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    @RequestMapping(value = "/downloadAn2", method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadAn2(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	ResponseEntity<byte[]> responseEntity = null;
    	try {
    		Date now = new Date( );
    	    SimpleDateFormat ft = new SimpleDateFormat ("yyyy.MM.dd hh:mm:ss");
    	    logger.debug("downloadAn,start:"+ft.format(now));
    	    @SuppressWarnings("unchecked")
    	    List<String> list = (List<String>) RedisOps.getObject(PropertiesUtil.androidVersion);
		
			String uploadDir = request.getServletContext().getRealPath("/");
			uploadDir = new File(uploadDir).getParent();
			uploadDir = new File(uploadDir).getParent();
			String filepath = "";
			String releasename = "";
			String url = "";
			if (list!=null && list.size()==6) {
				url = list.get(2);
				releasename = list.get(3);
			}	
			filepath = uploadDir+"/"+url+"/"+releasename;
			File file = new File(filepath);
		    response.setContentType("application/x-msdownload;charset=utf-8");
			
		    HttpHeaders headers = new HttpHeaders();
		    //下载显示的中文名，解决中文名称乱码问题
	        releasename = new String(releasename.getBytes("UTF-8"), "ISO8859-1");
			headers.add("Content-Disposition", "attachment;filename="+releasename);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			responseEntity = new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return responseEntity;
    }
    
    @RequestMapping(value = "/downloadIOS", method = RequestMethod.GET)
    public void downloadIOS(HttpServletRequest request,HttpServletResponse response) throws IOException {
    	try {
    	  
    	    @SuppressWarnings("unchecked")
			List<String> list = (List<String>) RedisOps.getObject(PropertiesUtil.iosVersion);
    	    String filepath = PropertiesUtil.iosDownUrl;
			String releasename = "";
			String version = "";
			if (list!=null && list.size()==6) {
				version = list.get(1);
				releasename = list.get(3);
			}	
			filepath = filepath+"/"+version+"/"+releasename;
			response.setStatus(302);
			response.setHeader("Location",filepath);
			//---分割线---
			//response.sendRedirect(filepath);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
   
}
