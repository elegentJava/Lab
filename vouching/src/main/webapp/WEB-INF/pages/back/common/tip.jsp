<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="token" value="${token}" />

<div id="tip" class="error-container">
	<h1>信息提示<span style="float: right;"><i id="close" class="icon-stop"></i></span></h1>
	<div class="errorcon">
		 <span id="success" style="display:none;"></span>
		 <span id="faild" style="display:none;"></span>
	</div>
</div>