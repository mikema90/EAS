// JavaScript Document
$(document).ready(function() {
	test();
	var pageOffset=getParam("pageOffset");
	var maxItemCount=getParam("maxItemCount");
		$.ajax({
		type: 'POST',
		url: "../getPaper",
		data: {pageNum:pageNum, rowCount:rowCount},
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
	$.each(jsonData.paper, function(idx, paperItem){
		insertNewRow(paperItem.college_name, paperItem.first_author+","+paperItem.other_authors, paperItem.title, paperItem.journal, paperItem.issues, paperItem.post_date, paperItem.language, paperItem.journal_type, paperItem.pdf_url);
	});
	var pageOffset=parseInt(jsonData.pageOffset);
	var maxPageCount=parseInt(jsonData.maxPageCount);
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

function insertNewRow(school, authorName, thesisName, periodicalName, periodicalSn, publishTime, isForeignLanguage, isCore, pdfId){
	var newRow=$("#templates tr").clone(true);
	
	var currentRowId;
	if($("#contentTable").find(".rowId:last").length==0){
		currentRowId=0;
	}else{
		currentRowId=parseInt($("#contentTable").find(".rowId:last").text());
	}
	newRow.find(".rowId").text(currentRowId+1);
	newRow.find(".school").text(school);
	newRow.find(".authorName").text(authorName);
	newRow.find(".thesisName").text(thesisName);
	newRow.find(".periodicalName").text(periodicalName);
	newRow.find(".periodicalSn").text(periodicalSn);
	newRow.find(".publishTime").text(publishTime);
	newRow.find(".isForeignLanguage").text(isForeignLanguage);
	newRow.find(".isCore").text(isCore);
	newRow.find(".pdfId a").attr("href","../"+pdfId.replace("\\","/"));
	
	$("#contentTable tbody").append(newRow);
}

function getParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r!=null){
		return unescape(r[2]);
	}
	return null;
}