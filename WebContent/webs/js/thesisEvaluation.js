// JavaScript Document
$(document).ready(function () {
	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");
	$.ajax({
		type: 'POST',
		url: "../getEvaluationPaper",
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				$.each(jsonData.paper, function (idx, itemPaper) {
					var tmpAuthorName = itemPaper.first_author;
					if (itemPaper.other_authors != "") {
						tmpAuthorName = tmpAuthorName + "," + itemPaper.other_authors;
					}
					insertNewRow(itemPaper.id, itemPaper.college_name, tmpAuthorName, itemPaper.title, itemPaper.journal, itemPaper.issues, itemPaper.post_date, itemPaper.language, itemPaper.journal_type, itemPaper.pdf_url, itemPaper.passed, itemPaper.comment);
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

function insertNewRow(thesisId, school, authorName, thesisTitle, periodicalName, periodicalSn, publishTime, thesisLanguage, isCore, pdfId, evalResult, remark) {
	var newRow = $("#templates tr").clone(true);
	var currentRowId;
	if ($("#contentTable").find(".rowId:last").length == 0) {
		currentRowId = 0;
	} else {
		currentRowId = parseInt($("#contentTable").find(".rowId:last").text());
	}
	newRow.find(".rowId").text(currentRowId + 1);
	newRow.find(".thesisId").text(thesisId);
	newRow.find(".school").text(school);
	newRow.find(".authorName").text(authorName);
	newRow.find(".thesisTitle").text(thesisTitle);
	newRow.find(".periodicalName").text(periodicalName);
	newRow.find(".periodicalSn").text(periodicalSn);
	newRow.find(".publishTime").text(publishTime);
	newRow.find(".thesisLanguage").text(thesisLanguage);
	newRow.find(".isCore").text(isCore);
	newRow.find(".pdfId a").attr("href", "../downloadPDF?paper_id=" + thesisId);
	newRow.find(".evalResult").val(evalResult);
	newRow.find(".remark div").html(remark + "<span onclick=\"editRemark(this)\">编辑</span>");

	$("#contentTable tbody").append(newRow);
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
			'evalResult': $(itemUnsaved).find(".evalResult").val(),
			'remark': divText.substring(0, divText.length - 2),
		};
		if (objArrayStr == "") {
			objArrayStr = $.param(obj);
		} else {
			objArrayStr = objArrayStr + "&" + $.param(obj);
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

function test() {
	insertNewRow("123", "软院", "高光宇", "dfasdfasdf", "杂志", "123456", "12-14-12-123", "中文", "非核心", "pdf1234", "unselected", "很好很好很好很好很好很好很好很好很好很好");
}