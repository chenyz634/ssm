<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>版本控制</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<!-- jqgrid-->
<link href="${path }/resources/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">
<link href="${path }/resources/css/upload/fileinput.css" rel="stylesheet">
<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

<body class="gray-bg">
	<div class="panel-body">
		<div class="row">
			
			<div class="col-lg-2">
				<div class="input-group">
					<span class="input-group-addon">version type:</span>
					<select class="form-control" id = "txt_search_versiontype">
						<option value="">---请选择---</option>
						<option value="1">android</option>
						<option value="2">iphone</option>
				    </select>
				</div>
			</div>
            <div style="margin-left:15px;margin-top: 10px;margin-bottom: 10px;">
            	<button id="btn_version_search" type="button" class="btn btn-default">
	            	<span class="glyphicon glyphicon-search" aria-hidden="true"></span>查询
	            </button>
            </div>
	  	</div>
	  	
		<div id="toolbar" class="btn-group">
			<c:forEach items="${operationList}" var="oper">
				<privilege:operation operationId="${oper.operationid }" id="${oper.operationcode }" name="${oper.operationname }" clazz="${oper.iconcls }"  color="#093F4D"></privilege:operation>
			</c:forEach>
        </div>
        <table id="table_version"></table>
	</div>
	
	<!-- 新增和修改对话框 -->
	<div class="modal fade" id="modal_version_edit" role="dialog" aria-labelledby="modal_version_edit" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<form id="form_version" method="post" action="reserveVersion.htm">
						<input type="hidden" name="vid" id="hidden_txt_vid" value=""/>
						<table style="border-collapse:separate; border-spacing:0px 10px;" calss="dataTable">
							<thead><tr><th>新增版本</th> </tr></thead>
							<tr>
								<td>Version Type：</td>
								<td>
									<select class="form-control" name="versiontype" id = "versiontype" aria-required="true" required>
										<option value="">---请选择---</option>
										<option value="1">android</option>
										<option value="2">iphone</option>
				                	</select>
								</td>
							</tr>
							<tr>
								<td>URL：</td>
								<td>
									<input type="text" id="url" name="url"
									class="form-control" aria-required="true" required value="autoupload" readOnly/>
								</td>
							</tr>
							<tr>
								<td>Version：</td>
								<td>
									<input type="text" id="version" name="version"
									class="form-control" aria-required="true" required/>
								</td>
							</tr>
							<tr>
								<td>Release Name：</td>
								<td>
									<input type="text" id="releasename" name="releasename"
									class="form-control" aria-required="true" required/>
								</td>
							</tr>
							<tr>
								<td>Force Update：</td>
								<td>
									<select class="form-control" name="forceupdate" id = "forceupdate" aria-required="true" required>
										<option value="">---请选择---</option>
										<option value="0" selected>不强制</option>
										<option value="1">强制</option>
				                	</select>
								</td>
							</tr>
							<tr>
								<td>Use Version：</td>
								<td>
									<select class="form-control" name="useversion" id = "useversion" aria-required="true" required>
										<option value="">---请选择---</option>
										<option value="0" selected>停用</option>
										<option value="1">启用</option>
				                	</select>
								</td>
							</tr>
							<tr>
								<td>description：</td>
								<td>
									<input type="text" id="temp" name="temp"
									class="form-control" aria-required="true" required/>
								</td>
							</tr>
						</table>
						
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"  id="submit_form_version_btn">保存</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!--删除对话框 -->
	<div class="modal fade" id="modal_version_del" role="dialog" aria-labelledby="modal_version_del" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					 <h4 class="modal-title" id="modal_version_del_head"> 刪除  </h4>
				</div>
				<div class="modal-body">
							删除所选记录？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger"  id="del_version_btn">刪除</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<!--上传对话框 -->
	<div class="modal fade" id="modal_version_upload" role="dialog" aria-labelledby="modal_version_upload" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					 <h4 class="modal-title" id="modal_version_upload_head"> 上传  </h4>
				</div>
				<div class="modal-body">
					<form enctype="multipart/form-data">
		                <div class="form-group">
		                    <input id="file-0" type="file" multiple class="file" data-min-file-count="1">
		                    <!-- <input id="file-0" class="file" type="file" multiple data-min-file-count="1"> -->
		                </div>
		            </form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="ui-jqdialog modal-content" id="alertmod_table_version_mod"
		dir="ltr" role="dialog"
		aria-labelledby="alerthd_table_version" aria-hidden="true"
		style="width: 200px; height: auto; z-index: 2222; overflow: hidden;top: 274px; left: 534px; display: none;position: absolute;">
		<div class="ui-jqdialog-titlebar modal-header" id="alerthd_table_version"
			style="cursor: move;">
			<span class="ui-jqdialog-title" style="float: left;">注意</span> <a id ="alertmod_table_version_mod_a"
				class="ui-jqdialog-titlebar-close" style="right: 0.3em;"> <span
				class="glyphicon glyphicon-remove-circle"></span></a>
		</div>
		<div class="ui-jqdialog-content modal-body" id="alertcnt_table_version">
			<div id="select_message"></div>
			<span tabindex="0"> <span tabindex="-1" id="jqg_alrt"></span></span>
		</div>
		<div class="jqResize ui-resizable-handle ui-resizable-se glyphicon glyphicon-import"></div>
	</div>
	
	<!-- Peity-->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>
	
	<script src="${path }/resources/js/bootbox.min.js"></script>
	
	<!-- Bootstrap table-->
	<script src="${path }/resources/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
	<script src="${path }/resources/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="${path }/resources/js/upload/fileinput.js" type="text/javascript"></script>
	<!-- 自定义js-->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	
	 <!-- jQuery Validation plugin javascript-->
    <script src="${path }/resources/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${path }/resources/js/plugins/validate/messages_zh.min.js"></script>
   
   	<!-- jQuery form  -->
    <script src="${path }/resources/js/jquery.form.min.js"></script>
    <script type="text/javascript">
    Date.prototype.Format = function (fmt) {
	    var o = {  
	        "M+": this.getMonth() + 1, //月份
	        "d+": this.getDate(), //日
	        "H+": this.getHours(), //小时
	        "m+": this.getMinutes(), //分
	        "s+": this.getSeconds(), //秒
	        "S": this.getMilliseconds() //毫秒   
	    };  
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));  
	    for (var k in o)  
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));  
	    return fmt;  
	};
	
	$(function () {
		
		init();
		$("#btn_version_search").bind("click",function(){
	    	//先销毁表格  
	        $('#table_version').bootstrapTable('destroy');
	    	init();
	    });
		var validator = $("#form_version").validate({
    		submitHandler: function(form){
   		      $(form).ajaxSubmit({
   		    	dataType:"json",
   		    	success: function (data) {
   		    		
   		    		if(data.success && !data.errorMsg ){
   		    			validator.resetForm();
   		    			$('#modal_version_edit').modal('hide');
   		    			$("#table_version").bootstrapTable('refresh');
   		    		}else{
   		    			$("#select_message").text(data.errorMsg);
   		    			$("#alertmod_table_version_mod").show();
   		    		}
                }
   		      });     
   		   }  
	    });
	    $("#submit_form_version_btn").click(function(){
	    	$("#form_version").submit();
	    });
	});    
	
	var init = function () {
		//1.初始化Table
	    var oTable = new TableInit();
	    oTable.Init();
	    //2.初始化Button的点击事件
	    var oButtonInit = new ButtonInit();
	    oButtonInit.Init();
	};
	
	var TableInit = function () {
	    var oTableInit = new Object();
	    //初始化Table
	    oTableInit.Init = function () {
	        $('#table_version').bootstrapTable({
	            url: 'versionList.htm',         //请求后台的URL（*）
	            method: 'post',                      //请求方式（*）
	            contentType : "application/x-www-form-urlencoded",
	            toolbar: '#toolbar',                //工具按钮用哪个容器
	            striped: true,                      //是否显示行间隔色
	            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	            pagination: true,                   //是否显示分页（*）
	            sortable: true,                     //是否启用排序
	            sortName: "vid",
	            sortOrder: "desc",                   //排序方式
	            queryParams: oTableInit.queryParams,//传递参数（*）
	            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	            pageNumber:1,                       //初始化加载第一页，默认第一页
	            pageSize: 10,                       //每页的记录行数（*）
	            pageList: [10, 25, 50, 75, 100],    //可供选择的每页的行数（*）
	            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	            strictSearch: true,
	            showColumns: true,                  //是否显示所有的列
	            showRefresh: false,                  //是否显示刷新按钮
	            minimumCountColumns: 2,             //最少允许的列数
	            clickToSelect: true,                //是否启用点击选中行
	           // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	            uniqueId: "vid",                     //每一行的唯一标识，一般为主键列
	            showToggle:true,                    //是否显示详细视图和列表视图的切换按钮
	            cardView: false,                    //是否显示详细视图
	            detailView: false,                   //是否显示父子表
	            columns: [{
	                checkbox: true
	            },
	            {
	                field: 'vid',
	                title: 'vid',
	                sortable:true
	            },
	            {
	                field: 'versiontype',
	                title: 'VersionType',
	                sortable:true,
	                formatter:function(value,row,index){
	                	return value==1?"android":"iphone";
	                }
	            }, {
	                field: 'url',
	                title: 'URL',
	                sortable:true
	            }, {
	                field: 'version',
	                title: 'Version'
	            }, {
	                field: 'releasedate',
	                title: 'Release Date',
	                sortable:true,
	                formatter:function(value,row,index){
	                	return new Date(value).Format('yyyy-MM-dd HH:mm:ss');
	                }
	            }, {
	                field: 'releasename',
	                title: 'Release Name'
	            }, {
	                field: 'forceupdate',
	                title: 'Force Update',
	                sortable:true,
	                formatter:function(value,row,index){
	                	return value=="1"?"强制更新":"不强制更新";
	                }
	            }, {
	                field: 'useversion',
	                title: 'Use Version',
	                sortable:true,
	                formatter:function(value,row,index){
	                	return value=="1"?"启用":"停用";
	                }
	            },{
	                field: 'temp',
	                title: 'description'
	            }],
	            onClickRow: function (row) {
	            	//$("#alertmod_table_user_mod").hide();
	            }
	        });
	    };
	    
	  //得到查询的参数
	    oTableInit.queryParams = function (params) {
	        var temp = {//这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
	            limit: params.limit,   //页面大小
	            offset: params.offset,  //页码
	            versiontype: $("#txt_search_versiontype").val(),
	            search:params.search,
	            order: params.order,
	            ordername: params.sort
	        };
	        return temp;
	    };
	    
	    return oTableInit;
	};

	var ButtonInit = function () {
	    var oInit = new Object();
	    var postdata = {};

	    oInit.Init = function () {
	        //初始化页面上面的按钮事件
	    	$("#btn_version_add").click(function(){
	    		$("#form_version").resetForm();
	    		document.getElementById("hidden_txt_vid").value='';
	    		$('#modal_version_edit').modal({backdrop: 'static', keyboard: false});
				$('#modal_version_edit').modal('show');
	        });
	        
	    	$("#btn_version_edit").click(function(){
	    		var getSelections = $('#table_version').bootstrapTable('getSelections');
	    		if(getSelections && getSelections.length==1){
	    			initEditVersion(getSelections[0]);
	    			$('#modal_version_edit').modal({backdrop: 'static', keyboard: false});
					$('#modal_version_edit').modal('show');
	    		}else{
	    			$("#select_message").text("请选择其中一条数据");
	    			$("#alertmod_table_version_mod").show();
	    		}
	    		
	        });
	    	
	    	$("#btn_version_delete").click(function(){
	    		var getSelections = $('#table_version').bootstrapTable('getSelections');
	    		if(getSelections && getSelections.length>0){
	    			$('#modal_version_del').modal({backdrop: 'static', keyboard: false});
	    			$("#modal_version_del").show();
	    		}else{
	    			$("#select_message").text("请选择数据");
	    			$("#alertmod_table_version_mod").show();
	    		}
	        });
	        
	    	$("#btn_version_upload").click(function(){
	    		var getSelections = $('#table_version').bootstrapTable('getSelections');
	    		if(getSelections && getSelections.length==1){
	    			initEditVersion(getSelections[0]);
	    			$('#modal_version_upload').modal({backdrop: 'static', keyboard: false});
	    			$("#modal_version_upload").show();
	    		}else{
	    			//bootbox.dialog({ message: '请选择其中一条数据' });
	    			$("#select_message").text("请选择其中一条数据");
	    			//bootbox.alert(result.msg);
	    			$("#alertmod_table_version_mod").show();
	    		}
	        });
	        
	    	$("#btn_version_download").click(function(){
	    		var getSelections = $('#table_version').bootstrapTable('getSelections');
	    		if(getSelections && getSelections.length==1){
	    			var data = "versiontype="+getSelections[0].versiontype+"&url="+getSelections[0].url
	    				+"&releasename="+getSelections[0].releasename;
	    			download("downloadmyfile.htm",data,"post")
	    		}else{
	    			//bootbox.dialog({ message: '请选择其中一条数据' });
	    			$("#select_message").text("请选择其中一条数据");
	    			//bootbox.alert(result.msg);
	    			$("#alertmod_table_version_mod").show();
	    		}
	        });
	    };

	    return oInit;
	};
	
	function initEditVersion(getSelection){
		$('#hidden_txt_vid').val(getSelection.vid);
		$('#forceupdate').val(getSelection.forceupdate);
		$('#versiontype').val(getSelection.versiontype);
		$('#url').val(getSelection.url);
		$('#version').val(getSelection.version);
		$('#releasename').val(getSelection.releasename);
		$('#useversion').val(getSelection.useversion);
		$('#temp').val(getSelection.temp);
	}
	
	function download(url, data, method){
		//url and data options required
		if( url && data ){
			//data can be string of parameters or array/object
			data =typeof data =='string'? data : jQuery.param(data);
			//split params into form inputs
			var inputs ='';
			jQuery.each(data.split('&'), function(){
				var pair =this.split('=');
				inputs+='<input type="hidden" name="'+ pair[0]+'" value="'+ pair[1]+'" />';
			});
			//send request
			jQuery('<form action="'+ url +'" method="'+(method||'post')+'">'+inputs+'</form>').appendTo('body').submit().remove();
		};
	}
	
	$("#del_version_btn").click(function(){
		var getSelections = $('#table_version').bootstrapTable('getSelections');
		var idArr = new Array();
		var ids;
		getSelections.forEach(function(item){
			idArr.push(item.vid);
		});
		ids = idArr.join(",");
		$.ajax({
		    url:"deleteVersion.htm",
		    dataType:"json",
		    data:{"ids":ids},
		    type:"post",
		    success:function(res){
		    	if(res.success){
	    			$('#modal_version_del').modal('hide');
	    			$("#table_version").bootstrapTable('refresh');
	    		}else{
	    			$("#select_message").text(res.errorMsg);
	    			$("#alertmod_table_version_mod").show();
	    		}
		    }
		});
	});
	
	$("#alertmod_table_version_mod_a").click(function(){
		$("#alertmod_table_version_mod").hide();
	});
	
	$("#file-0").fileinput({
        uploadUrl: 'uploadVersion.htm', 
        //allowedFileExtensions : ['', 'png','gif'],
        uploadAsync: true,
        overwriteInitial: true,
        maxFileSize: 500000,
        maxFilesNum: 1,
        browseOnZoneClick: true,
        dropZoneEnabled: false,
        enctype:'multipart/form-data',
        //allowedFileTypes: ['image', 'video', 'flash'],
        slugCallback: function(filename) {
            return filename.replace('(', '_').replace(']', '_');
        },
        uploadExtraData:function(){
        	var data = {
        			vid : $('#hidden_txt_vid').val(),
        			versiontype : $('#versiontype').val(),
        			version : $('#version').val(),
        			releasename : $('#releasename').val()
        	};
        	return data;
        }
	});
					
	$("#file-0").on("fileuploaded",function(event, data, previewId, index) {
			var result = data.response;
			if (result.success) {
				$("#modal_version_upload").modal("hide");
				$('#table_version').bootstrapTable("refresh");
				alert(result.msg);
				//bootbox.alert();
			} else {
				alert(result.msg);
				//bootbox.alert(result.msg);
			}
	});
</script>
</body>
</html>