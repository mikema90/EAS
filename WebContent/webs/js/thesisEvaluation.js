// JavaScript Document
$(document).ready(function () {
	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");
	$.ajax({
		type: 'POST',
		url: "../getExpert",
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				$.each(jsonData.expert, function (idx, itemExpert) {
					insertNewRow(itemExpert.id, itemExpert.college_id, itemExpert.name, itemExpert.work_id, itemExpert.subject, itemExpert.language);
				});
			} else {
				alert("加载失败");
			}
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			alert("加载失败");
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		dataType: 'json'
	});
});

function insertNewRow(school, authorName, thesisTitle, periodicalName, periodicalSn, publishTime, thesisLanguage, isCore, pdfId, evalResult, remark) {

}

function cancelRemarkEditor() {
	$("#remarkEditorBckgnd").css("visibility", "hidden");
	$("#remarkEditorOuterWrapper").css("visibility", "hidden");
}

function editRemark(targetElement) {
	$("#remarkEditorBckgnd").css("visibility", "visible");
	$("#remarkEditorOuterWrapper").css("visibility", "visible");
	ongoingRemarkDiv = $(targetElement).closest("div");
	var remarkText = ongoingRemarkDiv.text();
	remarkText = remarkText.substr(0, remarkText.length - 2);
	$("#remarkEditorInnerWrapper textarea").val(remarkText);
}

function confirmRemarkEditor() {
	if ($("#remarkEditorInnerWrapper textarea").val().length > 30) {
		alert("字数不能超过30字");
		return;
	}
	$("#remarkEditorBckgnd").css("visibility", "hidden");
	$("#remarkEditorOuterWrapper").css("visibility", "hidden");
	var editorSpan = ongoingRemarkDiv.find("span").clone(false);
	ongoingRemarkDiv.text($("#remarkEditorInnerWrapper textarea").val());
	ongoingRemarkDiv.append(editorSpan);
	ongoingRemarkDiv.closest("tr").addClass("unsaved");
}

function saveData() {
	var objArrayStr = "";
	$.each($(".unsaved"), function (idx, itemUnsaved) {
		var divText = $(itemUnsaved).find("div").text();
		var obj = {
			'tupleId': '23',
			'evalResult': $(itemUnsaved).find(".evaluationResult").val(),
			'remark': divText.substring(0, divText.length - 2),
		};
		if(objArrayStr==""){
			objArrayStr=$.param(obj);
		}else{
			objArrayStr=objArrayStr + "&"+ $.param(obj);
		}
		
	});
	//alert(objArrayStr);
	
	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");
	$.ajax({
		type: 'POST',
		url: "../TestingGGY",
		data: objArrayStr,
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				alert("保存成功");
			} else {
				alert("保存失败");
			}
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			alert("保存失败");
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		dataType: 'json'
	});
}