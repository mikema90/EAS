// JavaScript Document
inEditing=true;

function insertNewRow(school, authorName, authorId, majors, languageAuths) {
	var newRow = $("#templates tr").clone(true);
	newRow.find(".school select").val(school);
	newRow.find(".school textarea").text(newRow.find(".school select option:selected").text());
	newRow.find(".authorName textarea").text(authorName);
	newRow.find(".authorId textarea").text(authorId);
	$("#contentTable tbody").append(newRow);
}

function edit(targetElement) {
	if(inEditing==true){
		alert("有修改未提交\n请先点击行末的“确定”提交\n或刷新页面。");
		return;
	}
	var targetParent = $(targetElement).closest("tr");
	var checkBoxes = targetParent.find("input[type='checkbox']");
	for (var i = 0; i < checkBoxes.length; i++) {
		checkBoxes.eq(i).removeAttr("onclick");
	}
	var textareas = targetParent.find("textarea");
	for (var i = 0; i < textareas.length; i++) {
		textareas.eq(i).removeAttr("readonly");
	}
	targetParent.removeClass("finishEdit");
	targetParent.addClass("editing");
	inEditing=true;
}

function confirmEdit(targetElement) {
	$("#exchangingDataBckgnd").css("visibility","visible");
	$("#exchangingDataInnerWrapper").css("visibility","visible");
	var targetParent = $(targetElement).closest("tr");
	submitForm(targetParent);
}

function finishEdit(targetParent) {
	targetParent.find(".school textarea").text(targetParent.find(".school select option:selected").text());
	var checkBoxes = targetParent.find("input[type='checkbox']");
	for (var i = 0; i < checkBoxes.length; i++) {
		checkBoxes.eq(i).attr("onclick", "return false;");
	}
	var textareas = targetParent.find("textarea");
	for (var i = 0; i < textareas.length; i++) {
		textareas.eq(i).attr("readonly", "readonly");
	}
	targetParent.addClass("finishEdit");
	targetParent.removeClass("editing");
	inEditing=false;
}

function submitForm(targetParent) {
	var tempForm=$("<form></form>");
	var clonedTarget=targetParent.clone(true);
	tempForm.append(clonedTarget);
	var submitData = tempForm.serialize();
	$.ajax({
		type: 'POST',
		url: "../TestingGGY",
		data: submitData,
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

function del(targetElement){
	var targetParent = $(targetElement).closest("tr");
	if(targetParent.hasClass("finishEdit") && inEditing==true){
		alert("有修改未提交\n请先点击行末的“确定”提交\n或刷新页面。");
		return;
	}
	
	$("#exchangingDataBckgnd").css("visibility","visible");
	$("#exchangingDataInnerWrapper").css("visibility","visible");
	
	var authorId=targetParent.find(".authorId").text();
		$.ajax({
		type: 'POST',
		url: "../TestingGGY",
		data: {authorId:authorId},
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility","hidden");
			if (jsonData.Status == "success") {
				targetParent.remove();
				inEditing=false;
			} else {
				alert("删除失败");
			}
			$("#exchangingDataBckgnd").css("visibility","hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility","hidden");
			alert("删除失败");
			$("#exchangingDataBckgnd").css("visibility","hidden");
		},
		dataType: 'json'
	});
}

function testA() {
	insertNewRow("software", "哈哈哈", "12345678901", "abc", "def")
}