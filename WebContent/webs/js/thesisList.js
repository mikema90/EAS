// JavaScript Document
$(document).ready(function() {
	//test();
	pageOffset=getParam("pageOffset");
	maxItemCount=getParam("maxItemCount");
	if(pageOffset==null){
		pageOffset=1;
	}
	if(maxItemCount==null){
		maxItemCount=25;
	}
		$.ajax({
		type: 'POST',
		url: "../getPaper",
		data: {pageOffset:pageOffset, maxItemCount:maxItemCount},
		success: function (jsonData) {
			if (jsonData.Status == "success") {
				loadContent(jsonData);
			} else {
				alert("加载失败");
			}
		},
		error: function () {
			alert("加载失败");
		},
		dataType: 'json'
	});
});

function loadContent(jsonData){
	wholeThesisData=jsonData.paper;
	$.each(jsonData.paper, function(idx, paperItem){
		var tmpAuthorName=paperItem.first_author;
		if(paperItem.other_authors!=""){
			tmpAuthorName=tmpAuthorName+","+paperItem.other_authors;
		}
		insertNewRow(paperItem.id, paperItem.college_name, tmpAuthorName, paperItem.title, paperItem.journal, paperItem.issues, paperItem.post_date, paperItem.language, paperItem.journal_type, paperItem.pdf_url);
	});
	pageOffset=parseInt(jsonData.pageOffset);
	maxPageCount=parseInt(jsonData.pageCount);
	for(var i=1;i<maxPageCount+1;i++){
		var newOption = new Option(i + "", i + "");
		$("#pageOffset").append(newOption);
	}
	$("#pageOffset").find("[value='" + pageOffset + "']").attr("selected", "selected");
	var maxItemCount=getParam("maxItemCount");
	$("#maxItemCount").find("[value='" + maxItemCount + "']").attr("selected", "selected");
}

function changePageOffset(){
	window.location="thesisList.html?pageOffset="+$("#pageOffset").val()+"&maxItemCount="+$("#maxItemCount").val();
}

function changeMaxItemCount(){
		window.location="thesisList.html?pageOffset=1&maxItemCount="+$("#maxItemCount").val();
}

function test(){
	var pageOffset=parseInt(getParam("pageOffset"));
	var maxPageCount=8;
	for(var i=1;i<maxPageCount+1;i++){
		var newOption = new Option(i + "", i + "");
		$("#pageOffset").append(newOption);
	}
	$("#pageOffset").find("[value='" + pageOffset + "']").attr("selected", "selected");
	var maxItemCount=getParam("maxItemCount");
	$("#maxItemCount").find("[value='" + maxItemCount + "']").attr("selected", "selected");
}

function insertNewRow(thesisId, school, authorName, thesisName, periodicalName, periodicalSn, publishTime, isForeignLanguage, isCore, pdfId){
	var newRow=$("#templates tr").clone(true);
	
	var currentRowId;
	if($("#contentTable").find(".rowId:last").length==0){
		currentRowId=0;
	}else{
		currentRowId=parseInt($("#contentTable").find(".rowId:last").text());
	}
	newRow.find(".rowId").text(currentRowId+1);
	newRow.find(".thesisId").text(thesisId);
	newRow.find(".school").text(school);
	newRow.find(".authorName").text(authorName);
	newRow.find(".thesisName").text(thesisName);
	newRow.find(".periodicalName").text(periodicalName);
	newRow.find(".periodicalSn").text(periodicalSn);
	newRow.find(".publishTime").text(publishTime);
	newRow.find(".isForeignLanguage").text(isForeignLanguage);
	newRow.find(".isCore").text(isCore);
	newRow.find(".pdfId a").attr("href","../downloadPDF?paper_id="+thesisId);
	
	$("#contentTable tbody").append(newRow);
}

function modify(targetElement){
	var targetParent = $(targetElement).closest("tr");
	var thesisId=targetParent.find(".thesisId").text();
	var myDate = new Date();
	var infoId=myDate.getTime()*3+""+thesisId*13;
	$.each(wholeThesisData, function(idx, paperItem){
		var indexStr=(idx+1)+"";
		var targetIdStr=targetParent.find(".rowId").text()+"";
		//alert(targetIdStr+","+indexStr);
		if(parseInt(targetIdStr)==parseInt(indexStr)){
			$.cookie(infoId, JSON.stringify(paperItem), { path: '/', expires:1});
			window.location="uploadThesis.html?id="+infoId;
		}
	});
}

function delItem(targetElement){
	if(confirm("确认删除吗？")==false){
		return;
	}
	var targetParent = $(targetElement).closest("tr");
	var thesisId=targetParent.find(".thesisId").text();
	$.ajax({
		type: 'POST',
		url: "../deletePaper",
		data: {paper_id:thesisId},
		success: function (jsonData) {
			if (jsonData.Status == "success") {
				alert("删除成功");
				window.location.reload();
			} else {
				alert("删除失败");
			}
		},
		error: function () {
			alert("删除失败");
		},
		dataType: 'json'
	});
}

function previousPage(){
	if(parseInt(pageOffset)<=1){
		alert("已经是第一页了");
		return;
	}else{
		//alert("thesisList.html?pageOffset="+(parseInt(pageOffset)-1)+"&maxItemCount="+maxItemCount);
		window.location="thesisList.html?pageOffset="+(parseInt(pageOffset)-1)+"&maxItemCount="+maxItemCount;
	}
}

function nextPage(){
	if(parseInt(pageOffset)>=maxPageCount){
		alert("已经是最后一页了");
		return;
	}else{
		//alert("thesisList.html?pageOffset="+(parseInt(pageOffset)+1)+"&maxItemCount="+maxItemCount);
		window.location="thesisList.html?pageOffset="+(parseInt(pageOffset)+1)+"&maxItemCount="+maxItemCount;
	}
}

function getParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null){
		return unescape(r[2]);
	}
	return null;
}