// JavaScript Document

function insertNewRow(schoolId, schoolName, schoolUserName) {
	var newRow = $("#templates tr").clone(true);
	newRow.find(".school input").val(schoolId);
	newRow.find(".school div").val(schoolName);
	newRow.find(".schoolUserName input").text(schoolUserName);
	$("#contentTable tbody").append(newRow);
}

function resetPassword(targetElement){
	$("#exchangingDataBckgnd").css("visibility","visible");
	$("#exchangingDataInnerWrapper").css("visibility","visible");
	var targetParent = $(targetElement).closest("tr");
	var schoolId=targetParent.find(".schoolId").text();
	submitForm(targetParent);
}

function finishEdit(targetParent) {
	targetParent.find(".school textarea").text(targetParent.find(".school select option:selected").text());
	var userNameInputBox = targetParent.find(".schoolUserName input");
	userNameInputBox.attr("readonly", "readonly");
	targetParent.addClass("finishEdit");
	targetParent.removeClass("editing");
	inEditing=false;
}

function submitForm(schoolId) {
	$.ajax({
		type: 'POST',
		url: "../TestingGGY",
		data: {schoolId:schoolId},
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility","hidden");
			if (jsonData.Status == "success") {
				finishEdit(targetParent);
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

function testA() {
	insertNewRow("software", "哈哈哈", "12345678901", "abc", "def")
}