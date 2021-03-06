// JavaScript Document
inEditing = false;

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

function insertNewRow(tupleId, school, expertName, expertId, majors, languageAuths) {
	var newRow = $("#templates tr").clone(true);
	newRow.find(".school select").val(school);
	newRow.find(".school textarea").text(newRow.find(".school select option:selected").text());
	newRow.find(".expertName textarea").text(expertName);
	newRow.find(".expertId textarea").text(expertId);
	newRow.find("input[name='tupleId']").attr("value", tupleId);
	var majorArray = majors.split(",");
	for (var i = 0; i < majorArray.length; i++) {
		newRow.find("input[name='" + majorArray[i] + "']").attr("checked", "checked");
	}
	var languageAuthArray = languageAuths.split(",");
	for (var i = 0; i < languageAuthArray.length; i++) {
		newRow.find("input[name='" + languageAuthArray[i] + "']").attr("checked", "checked");
	}
	$("#contentTable tbody").append(newRow);
}

function insertNewRowForEdit(school, expertName, expertId, majors, languageAuths) {
	var newRow = $("#templates tr").clone(true);
	newRow.find(".school select").val(school);
	newRow.find(".school textarea").text(newRow.find(".school select option:selected").text());
	newRow.find(".expertName textarea").text(expertName);
	newRow.find(".expertId textarea").text(expertId);
	edit(newRow);
	$("#contentTable tbody").append(newRow);
}

function edit(targetElement) {
	if (inEditing == true) {
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
	inEditing = true;
}

function confirmEdit(targetElement) {
	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");
	var targetParent = $(targetElement).closest("tr");
	var checkBoxes = targetParent.find("input[type=checkbox]");
	for (var i = 0; i < checkBoxes.length; i++) {
		//alert($(checkBoxes[i]).is(':checked'));
		if ($(checkBoxes[i]).is(':checked') == true) {
			$(checkBoxes[i]).val("checked");
		} else {
			$(checkBoxes[i]).val("unchecked");
		}
	}
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
	inEditing = false;
}

function submitForm(targetParent) {
	var tempForm = $("<form></form>");
	var clonedTarget = targetParent.clone(true);
	tempForm = tempForm.append(clonedTarget);
	var submitData = $(tempForm).serialize();
	//alert(submitData);
	$.ajax({
		type: 'POST',
		url: "../addExpert",
		data: submitData,
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				finishEdit(targetParent);
			} else {
				alert("添加/修改失败");
			}
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			alert("添加/修改失败");
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		dataType: 'json'
	});
}

function resetPassword(targetElement) {
	var targetParent = $(targetElement).closest("tr");
	if (inEditing == true) {
		alert("有修改未提交\n请先点击行末的“确定”提交\n或刷新页面。");
		return;
	}
	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");
	var expertId = targetParent.find(".expertId").text();
	$.ajax({
		type: 'POST',
		url: "../resetExpertPWD",
		data: {
			expertId: expertId
		},
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				alert("密码重置成功");
			} else {
				alert("重置密码失败");
			}
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			alert("重置密码失败");
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		dataType: 'json'
	});
}

function del(targetElement) {
	if (confirm("确定删除？") == false) {
		return;
	}
	
	var targetParent = $(targetElement).closest("tr");
	if (targetParent.hasClass("finishEdit") && inEditing == true) {
		alert("有修改未提交\n请先点击行末的“确定”提交\n或刷新页面。");
		return;
	}

	$("#exchangingDataBckgnd").css("visibility", "visible");
	$("#exchangingDataInnerWrapper").css("visibility", "visible");

	var tupleId = targetParent.find("input[name='tupleId']").val();
	//alert(expertId);
	$.ajax({
		type: 'POST',
		url: "../deleteExpert",
		data: {
			tupleId: tupleId
		},
		success: function (jsonData) {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			if (jsonData.Status == "success") {
				targetParent.remove();
				inEditing = false;
			} else {
				alert("删除失败");
			}
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		error: function () {
			$("#exchangingDataInnerWrapper").css("visibility", "hidden");
			alert("删除失败");
			$("#exchangingDataBckgnd").css("visibility", "hidden");
		},
		dataType: 'json'
	});
}

function testA() {
	if (inEditing == true) {
		alert("有修改未提交\n请先点击行末的“确定”提交\n或刷新页面。");
		return;
	}
	insertNewRowForEdit("03000", "", "", "", "")
}

function onchangeSelect(targetElement) {
	//alert($(targetElement).val());
	var selectedValue = $(targetElement).val();
	$(targetElement).find("option[selected='selected']").removeAttr("selected");
	$(targetElement).find("option[value='" + selectedValue + "']").attr("selected", "selected");
}

function onchangeTextArea(targetElement) {
	var textValue = $(targetElement).val();
	$(targetElement).text(textValue);
}