// JavaScript Document
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
	newRow.find(".pdfId a").attr("href","../requestPdf?pdfId="+pdfId);
	
	$("#contentTable tbody").append(newRow);
}

function testA(){
	insertNewRow("院系XXX", "作者小明", "论文名", "期刊名balabala", "期刊号xxxx", "xxxx年xx月xx日", "是", "是", "pdfIdAbcdefg");
}