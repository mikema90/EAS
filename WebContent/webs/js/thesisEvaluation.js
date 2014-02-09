// JavaScript Document
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