// JavaScript Document
function showAuthorEditor(){
	//if (eval(authorNum)=='undefined'){
	//	authorNum=1;
	//}
	$("#authorEditor").css("display","table-row");
	$("#authorEditorEnter").css("display","none");
	$("#authorNameInputBox").focus();
}

function hideAuthorEditor(){
	$("#authorEditor").css("display","none");
	$("#authorEditorEnter").css("display","table-row");
}

function insertAuthorInfo(){
	var newAuthorInfo=$("#authorInfoTemplate").clone(true);
	newAuthorInfo.find(".authorOrder").text($("#authorEditor").find(".authorOrder").text());
	newAuthorInfo.find(".authorName").text($("#authorNameInputBox").val());
	newAuthorInfo.find(".authorId").text($("#authorIdInputBox").val());
	
	newAuthorInfo.insertBefore($("#authorEditor"));
	//$("#authorListTable").find("tbody").append(newAuthorInfo);
	
	hideAuthorEditor();
	var authorNumStr=$("#authorEditor").find(".authorOrder").text();
	$("#authorEditor").find(".authorOrder").text(parseInt(authorNumStr)+1);
	$("#authorNameInputBox").val("");
	$("#authorIdInputBox").val("");
}

function editAuthorInfo(targetElement){
	var targetParent=$(targetElement).closest("tr");
	targetParent.find(".authorName").find("input").val(targetParent.find(".authorName").find("div").text());
	targetParent.find(".authorId").find("input").val(targetParent.find(".authorId").find("div").text());
	targetParent.addClass("editing");
	targetParent.removeClass("finishEdit");
}

function quitEditingAuthor(targetElement){
	var targetParent=$(targetElement).closest("tr");
	targetParent.find(".authorName").find("input").val("");
	targetParent.find(".authorId").find("input").val("");
	targetParent.addClass("finishEdit");
	targetParent.removeClass("editing");
}

function confirmEditingAuthor(targetElement){
	var targetParent=$(targetElement).closest("tr");
	targetParent.find(".authorName").find("div").text(targetParent.find(".authorName").find("input").val());
	targetParent.find(".authorId").find("div").text(targetParent.find(".authorId").find("input").val());
	quitEditingAuthor(targetElement);
	resetRowOrder();
}

function resetRowOrder(){
	$("#authorListTable .authorOrder").each(function(index, element) {
        $(element).text(index+1);
    });
}

function delAuthorInfo(){
	
}