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

function cancelRemarkEditor(){
	$("#remarkEditorBckgnd").css("visibility","hidden");
	$("#remarkEditorOuterWrapper").css("visibility","hidden");
}

function editRemark(targetElement){
	$("#remarkEditorBckgnd").css("visibility","visible");
	$("#remarkEditorOuterWrapper").css("visibility","visible");
	ongoingRemarkDiv=$(targetElement).closest("div");
	var remarkText=ongoingRemarkDiv.text();
	remarkText=remarkText.substr(0,remarkText.length-2);
	$("#remarkEditorInnerWrapper textarea").val(remarkText);
}

function confirmRemarkEditor(){
	if($("#remarkEditorInnerWrapper textarea").val().length>30){
		alert("字数不能超过30字");
		return;
	}
	$("#remarkEditorBckgnd").css("visibility","hidden");
	$("#remarkEditorOuterWrapper").css("visibility","hidden");
	var editorSpan=ongoingRemarkDiv.find("span").clone(false);
	ongoingRemarkDiv.text($("#remarkEditorInnerWrapper textarea").val());
	ongoingRemarkDiv.append(editorSpan);
}