// JavaScript Document
$(document).ready(

function () {
	var yearSelector = $("select[name='timeYear']");
	for (var i = 2010; i <= 2030; i++) {
		var newOption = new Option(i + "", i + "");
		yearSelector.append(newOption);
	}
	var myDate = new Date();
	yearSelector.find("[value='" + myDate.getFullYear() + "']").attr("selected", "selected");

	var infoId = getParam("id");
	if (infoId == null) {
		submitUrl="../addPaper";
		return;
	} else {
		thesisInfo = JSON.parse($.cookie(infoId));
		$("#thesisTypeSelector").find("option:contains('" + thesisInfo.category + "')").attr("selected", "selected");
		insertAuthor2nd(thesisInfo.first_author, thesisInfo.first_author_wid);
		if(thesisInfo.other_authors!=""){
			var otherAuthorNames=thesisInfo.other_authors.split(",");
			var otherAuthorIds=thesisInfo.other_authors_wid.split(",");
			for(var i=0;i<otherAuthorNames.length;i++){
				insertAuthor2nd(otherAuthorNames[i], otherAuthorIds[i]);
			}
		}
		$("#thesisNameInputBox").val(thesisInfo.title);
		$("#periodicalNameInputBox").val(thesisInfo.journal);
		var periodicalSn=thesisInfo.issues.split("-");
		$("#periodicalSelector").val(periodicalSn[0]);
		changePeriodicalSnSelector();
		if(periodicalSn[0]=="ISSN"){
			$(".periodicalSn.sn2").val(periodicalSn[2]);
		}
		$(".periodicalSn.sn1").val(periodicalSn[1]);
		$(".timeSelector.timeOfYear option[value='"+thesisInfo.post_date.split("-")[0]+"']").attr("selected","selected");
		$(".timeSelector.timeOfMonth option[value='"+parseInt(thesisInfo.post_date.split("-")[1])+"']").attr("selected","selected");
		$("#languageSelector option:contains('"+thesisInfo.language+"')").attr("selected","selected");
		$("#uploadingStatus").css("color", "#F60");
		$("#thesisId").val(thesisInfo.id);
		$("#uploadingStatus").text("不上传新文件则保留原文件");
		submitUrl="../updatePaper";
	}
});

function insertAuthor2nd(authorName, authorId){
	var newAuthorInfo = $("#authorInfoTemplate").clone(true);
	newAuthorInfo.find(".authorOrder").text($("#authorEditor").find(".authorOrder").text());
	newAuthorInfo.find(".authorName").find("input").val(authorName);
	newAuthorInfo.find(".authorId").find("input").val(authorId);
	newAuthorInfo.insertBefore($("#authorEditor"));
	var authorNumStr = $("#authorEditor").find(".authorOrder").text();
	$("#authorEditor").find(".authorOrder").text(parseInt(authorNumStr) + 1);
}

function getParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

function showAuthorEditor() {
	$("#authorEditor").css("display", "table-row");
	$("#authorEditorEnter").css("display", "none");
	$("#authorNameInputBox").focus();
}

function hideAuthorEditor() {
	$("#authorEditor").css("display", "none");
	$("#authorEditorEnter").css("display", "table-row");
}

function insertAuthorInfo() {
	if ($("#authorNameInputBox").val() == "" || $("#authorIdInputBox").val() == "") {
		alert("作者姓名和工号不能为空");
	} else {
		insertAuthor2nd($("#authorNameInputBox").val(), $("#authorIdInputBox").val())
		hideAuthorEditor();
		$("#authorNameInputBox").val("");
		$("#authorIdInputBox").val("");
	}
}

function editAuthorInfo(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	targetParent.find(".authorName").find("input").removeAttr("readonly");
	targetParent.find(".authorId").find("input").removeAttr("readonly");
	targetParent.find(".authorName").find("div").text(
	targetParent.find(".authorName").find("input").val());
	targetParent.find(".authorId").find("div").text(
	targetParent.find(".authorId").find("input").val());
	targetParent.addClass("editing");
	targetParent.removeClass("finishEdit");
}

function confirmAndQuitEditing(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	targetParent.find(".authorName").find("input").attr("readonly", "readonly");
	targetParent.find(".authorId").find("input").attr("readonly", "readonly");
	targetParent.addClass("finishEdit");
	targetParent.removeClass("editing");
	targetParent.find(".authorName").find("div").text("");
	targetParent.find(".authorId").find("div").text("");
	resetRowOrder();
}

function rollbackEditing(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	targetParent.find(".authorName").find("input").val(
	targetParent.find(".authorName").find("div").text());
	targetParent.find(".authorId").find("input").val(
	targetParent.find(".authorId").find("div").text());
	confirmAndQuitEditing(targetElement);
}

function resetRowOrder() {
	$("#authorListTable .authorOrder").each(function (index, element) {
		$(element).text(index + 1);
	});
}

function moveUp(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	var targetIndex = $("#authorListTable tr").index(targetParent);
	if (targetIndex != 1) {
		targetParent.insertBefore($("#authorListTable tr:eq(" + (targetIndex - 1) + ")"));
	}
	resetRowOrder();
}

function moveDown(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	var targetIndex = $("#authorListTable tr").index(targetParent);
	var trNum = $("#authorListTable tr").size();
	if (targetIndex != (trNum - 3)) {
		targetParent.insertAfter($("#authorListTable tr:eq(" + (targetIndex + 1) + ")"));
	}
	resetRowOrder();
}

function delAuthorInfo(targetElement) {
	$(targetElement).closest("tr").remove();
	resetRowOrder();
}

function changePeriodicalSnSelector() {
	var targetParent = $("#periodicalSelector").closest(".oneLine");
	targetParent.removeClass("isCN");
	targetParent.removeClass("isISBN");
	targetParent.removeClass("isISSN");
	targetParent.addClass("is"+$("#periodicalSelector").val());
	$(".periodicalSn.sn1").val("");
	$(".periodicalSn.sn2").val("");
	$("#periodicalType").val("");
}

function onChangeSn1(targetElement) {
	if ($("#periodicalSelector").val() == "ISSN") {
		if ($(targetElement).val().length >= 4) {
			$(".periodicalSn.sn2").focus();
			if ($(targetElement).val().length > 4) {
				$(targetElement).val($(targetElement).val().substr(0, 4));
			}
		}
	} else if ($("#periodicalSelector").val() == "ISBN") {
		if ($(targetElement).val().length >= 13) {
			$(targetElement).val($(targetElement).val().substr(0, 13));
			if ($(targetElement).val().length > 13) {
				$(targetElement).val($(targetElement).val().substr(0, 4));
			}
		}
	}else if ($("#periodicalSelector").val() == "CN") {
		if ($(targetElement).val().length >= 2) {
			$(".periodicalSn.sn2").focus();
			if ($(targetElement).val().length > 2) {
				$(targetElement).val($(targetElement).val().substr(0, 2));
			}
		}
	} 
}

function onChangeSn2(targetElement) {
	if ($(targetElement).val().length >= 4) {
		$(targetElement).val($(targetElement).val().substr(0, 4));
	}
}

function uploadFile() {
	if ($("#uploadingStatus").text() != "等待上传") {
		alert("请选择文件");
	} else {
		$("#uploadingStatus").css("color", "#F60");
		$("#uploadingStatus").text("正在上传...");
		uploadingStatusNum = 3;

		try {
			$(window.frames["hidden_frame"].document).text("");
		} catch (e) {}
		checkingInterval = setInterval(checkUploadingStatus, 2000);
		$("#uploadFileForm").submit();
	}
}

function uploadingFailure() {
	$("#uploadingStatus").css("color", "#F00");
	$("#uploadingStatus").text("上传失败");
}

function changeUploadingStatus() {
	$("#uploadingStatus").css("color", "#F60");
	$("#uploadingStatus").text("等待上传");
}

function uploadingSuccess(tempName) {
	$("#fileTempName").val(tempName);
	$("#uploadingStatus").css("color", "#0F0");
	$("#uploadingStatus").text("上传成功！");
}

function checkUploadingStatus() {
	try {
		var iframeContent = $(window.frames["hidden_frame"].document).text();
		if (iframeContent.toLowerCase().indexOf("success") > 0) {
			clearInterval(checkingInterval);
		} else if (iframeContent != "") {
			clearInterval(checkingInterval);
			uploadingFailure();
		} else {
			uploadingStatusNum++;
			if (uploadingStatusNum == 7) {
				uploadingStatusNum = 1;
			}
			var showWord = "正在上传";
			for (var i = 0; i < uploadingStatusNum; i++) {
				showWord = showWord + ".";
			}
			$("#uploadingStatus").text(showWord);
		}
	} catch (e) {
		clearInterval(checkingInterval);
		uploadingFailure();
	}
}

function submitForm() {
	if ($("#authorListTable tbody").find("tr").length < 4) {
		alert("作者不能为空");
	} else if ($("#thesisNameInputBox").val() == "" || $("#periodicalNameInputBox").val() == "") {
		alert("论文和期刊名称不能为空");
	} else if ($("#periodicalSelector").val() == "ISSN" && ($(".periodicalSn.sn1").val().length != 4 || $(".periodicalSn.sn2").val().length != 4)) {
		alert("ISSN需要8位数字或字母并且由短横隔开");
	} else if ($("#periodicalSelector").val() == "ISBN" && $(".periodicalSn.sn1").val().length != 13) {
		alert("ISSN需要13位数字");
	}else if ($("#periodicalSelector").val() == "CN" && ($(".periodicalSn.sn1").val().length != 2||($(".periodicalSn.sn2").val().length != 2 && $(".periodicalSn.sn2").val().length != 4))) {
		alert("CN需要两位+两位字符，或者两位+四位字符");
	} else if ($("#uploadingStatus").text() != "上传成功！" && $("#uploadingStatus").text() != "不上传新文件则保留原文件") {
		alert("请上传附件");
	} else {
		var submitData = $("#thesisInfoForm").serialize();
		$.ajax({
			type: 'POST',
			url: submitUrl,
			data: submitData,
			success: function (jsonData) {
				if (jsonData.Status == "success") {
					window.location = jsonData.redirectUrl;
				} else {
					alert("保存未成功");
				}
			},
			error: function () {
				alert("保存失败");
			},
			dataType: 'json'
		});
	}
}

function cancelForm(){
	if(confirm("确认要放弃更改并返回吗？")==false){
		return;
	}
	window.location="thesisList.html"
}