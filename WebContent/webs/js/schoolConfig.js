// JavaScript Document
$(document).ready(function() {
		$.ajax({
		type: 'POST',
		url: "../getcollege",
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
	$.each(jsonData.college, function(idx, schoolItem){
		insertNewRow(schoolItem.college_id, schoolItem.name)
	});
}

function insertNewRow(schoolId, schoolName) {
	var newRow = $("#templates tr").clone(true);
	newRow.find(".school .schoolId").text(schoolId);
	newRow.find(".school .schoolName").text(schoolName);
	newRow.find(".schoolUserName div").text(schoolId+"00");
	$("#contentTable tbody").append(newRow);
}

function resetPassword(targetElement){
	$("#exchangingDataBckgnd").css("visibility","visible");
	$("#exchangingDataInnerWrapper").css("visibility","visible");
	var targetParent = $(targetElement).closest("tr");
	var schoolId=targetParent.find(".schoolId").text();
	submitForm(schoolId);
}

function submitForm(schoolId) {
	$.ajax({
		type: 'POST',
		url: "../TestingGGY",
		data: {schoolId:schoolId},
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility","hidden");
			if (jsonData.Status == "success") {
				alert("修改成功");
			} else {
				alert("修改失败");
			}
			$("#exchangingDataBckgnd").css("visibility","hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility","hidden");
			alert("修改失败");
			$("#exchangingDataBckgnd").css("visibility","hidden");
		},
		dataType: 'json'
	});
}