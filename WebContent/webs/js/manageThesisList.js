// JavaScript Document
$(document).ready(function () {
	$.ajax({
		type: 'POST',
		url: "../getDeclareStatus",
		success: function (jsonData) {
			submittingStatus = jsonData.submittingStatus;
			if (submittingStatus == "open") {
				$("#submittingStatus").text("关闭申报");
			} else if (submittingStatus == "close") {
				$("#submittingStatus").text("开放申报");
			}
		},
		error: function () {
			alert("加载失败");
		},
		dataType: 'json'
	});

	pageOffset = getParam("pageOffset");
	maxItemCount = getParam("maxItemCount");
	if (pageOffset == null) {
		pageOffset = 1;
	}
	if (maxItemCount == null) {
		maxItemCount = 25;
	}
	$.ajax({
		type: 'POST',
		url: "../getPaper",
		data: {
			pageOffset: pageOffset,
			maxItemCount: maxItemCount
		},
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

function loadContent(jsonData) {
	wholeThesisData = jsonData.paper;
	$.each(jsonData.paper, function (idx, paperItem) {
		var tmpAuthorName = paperItem.first_author;
		if (paperItem.other_authors != "") {
			tmpAuthorName = tmpAuthorName + "," + paperItem.other_authors;
		}
		insertNewRow(paperItem.id, paperItem.college_name, tmpAuthorName, paperItem.title, paperItem.journal, paperItem.issues, paperItem.post_date, paperItem.language, paperItem.journal_type, paperItem.pdf_url, "", "", "", "", "");
	});
	pageOffset = parseInt(jsonData.pageOffset);
	maxPageCount = parseInt(jsonData.pageCount);
	for (var i = 1; i < maxPageCount + 1; i++) {
		var newOption = new Option(i + "", i + "");
		$("#pageOffset").append(newOption);
	}
	$("#pageOffset").find("[value='" + pageOffset + "']").attr("selected", "selected");
	var maxItemCount = getParam("maxItemCount");
	$("#maxItemCount").find("[value='" + maxItemCount + "']").attr("selected", "selected");
}

function insertNewRow(thesisId, school, authorName, thesisName, periodicalName, periodicalSn, publishTime, isForeignLanguage, isCore, pdfId, firstExpertName, firstEvalResult, firstRemark, finalResult, reward) {
	var newRow = $("#templates .fullColumnRow").clone(true);
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
	newRow.find(".thesisName").text(thesisName);
	newRow.find(".periodicalName").text(periodicalName);
	newRow.find(".periodicalSn").text(periodicalSn);
	newRow.find(".publishTime").text(publishTime);
	newRow.find(".isForeignLanguage").text(isForeignLanguage);
	newRow.find(".isCore").text(isCore);
	newRow.find(".pdfId a").attr("href", "../requestPdf?pdfId=" + pdfId);
	newRow.find(".firstExpertName").text(firstExpertName);
	newRow.find(".firstEvalResult").text(firstEvalResult);
	newRow.find(".firstRemark").text(firstRemark);
	newRow.find(".finalResult").text(finalResult);
	newRow.find(".reward").text(reward);

	$("#contentTable tbody").append(newRow);
}

function insertExpert(expertName, evalResult, remark) {
	var newRow = $("#templates .furtherExpert").clone(true);
	newRow.find(".expertName").text(expertName);
	newRow.find(".evalResult").text(evalResult);
	newRow.find(".remark").text(remark);

	$("#contentTable tbody").append(newRow);
	increaseRowSpan();
}

function increaseRowSpan() {
	var targetTuples = $("#contentTable .fullColumnRow:last td");
	for (var i = 0; i < targetTuples.length; i++) {
		if (targetTuples.eq(i).attr("class") == "firstExpertName") {
			continue;
		} else if (targetTuples.eq(i).attr("class") == "firstEvalResult") {
			continue;
		} else if (targetTuples.eq(i).attr("class") == "firstRemark") {
			continue;
		}
		targetTuples.eq(i).attr("rowSpan", parseInt(targetTuples.eq(i).attr("rowSpan")) + 1);
	}
}

function changeSubmittingStatus() {
	var tmpStr;
	var	requestStatus;
	if(submittingStatus=="close"){
		requestStatus="open";
		tmpStr="开放";
	}else if(requestStatus="open"){
		requestStatus="close";
		tmpStr="关闭";
	}else{
		alert("当前申报状态获取失败，请刷新页面。");
		return;
	}
	
	if (confirm("确定"+tmpStr+"申报？") == false) {
		return;
	}
	
	$.ajax({
		type: 'POST',
		url: "../setDeclareStatus",
		data: {
			submittingStatus: requestStatus
		},
		success: function (jsonData) {
			if (jsonData.Status == "success") {
				alert("操作成功");
				window.location.reload();
			} else {
				alert("操作失败，请刷新页面重试");
			}
		},
		error: function () {
			alert("操作失败，请刷新页面重试");
		},
		dataType: 'json'
	});
}

function downloadSummarySheet() {
	$.ajax({
		type: 'POST',
		url: "../downloadSummarySheet",
		success: function (jsonData) {
			if (jsonData.Status == "success") {
				//alert("<form action=\""+ "../"+jsonData.path.replace("\\","/") +"\" method=\'post\'></form>");
				jQuery("<form action=\"" + "../" + jsonData.path.replace("\\", "/") + "\" method=\'post\'></form>").appendTo('body').submit().remove();
				//window.open("../"+jsonData.path.replace("\\","/"));
			} else {
				alert("下载失败，请重试");
			}
		},
		error: function () {
			alert("下载失败，请重试");
		},
		dataType: 'json'
	});
}

function previousPage() {
	if (parseInt(pageOffset) <= 1) {
		alert("已经是第一页了");
		return;
	} else {
		//alert("thesisList.html?pageOffset="+(parseInt(pageOffset)-1)+"&maxItemCount="+maxItemCount);
		window.location = "manageThesisList.html?pageOffset=" + (parseInt(pageOffset) - 1) + "&maxItemCount=" + maxItemCount;
	}
}

function nextPage() {
	if (parseInt(pageOffset) >= maxPageCount) {
		alert("已经是最后一页了");
		return;
	} else {
		//alert("thesisList.html?pageOffset="+(parseInt(pageOffset)+1)+"&maxItemCount="+maxItemCount);
		window.location = "manageThesisList.html?pageOffset=" + (parseInt(pageOffset) + 1) + "&maxItemCount=" + maxItemCount;
	}
}

function changePageOffset() {
	window.location = "thesisList.html?pageOffset=" + $("#pageOffset").val() + "&maxItemCount=" + $("#maxItemCount").val();
}

function changeMaxItemCount() {
	window.location = "thesisList.html?pageOffset=1&maxItemCount=" + $("#maxItemCount").val();
}

function getParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return unescape(r[2]);
	}
	return null;
}

function TestA() {
	insertNewRow("5", "院系XXX", "作者小明", "论文名", "期刊名balabala", "期刊号xxxx", "xxxx年xx月xx日", "是", "是", "pdfIdAbcdefg", "专家XXX", "不属于", "嗯嗯嗯", "不属于", "500", "", "", "", "", "");
}

function TestB() {
	insertExpert("专家YYY", "不属于", "嗯嗯嗯");
}