// JavaScript Document
function insertNewRow(school, authorName, thesisName, periodicalName, periodicalSn, publishTime, isForeignLanguage, isCore, pdfId, firstExpertName, firstEvalResult, firstRemark, finalResult, reward){
	var newRow=$("#templates .fullColumnRow").clone(true);
	var currentRowId=parseInt($("#contentTable").find(".rowId:last").text());
	newRow.find(".rowId").text(currentRowId+1);
	newRow.find(".school").text(school);
	newRow.find(".authorName").text(authorName);
	newRow.find(".thesisName").text(thesisName);
	newRow.find(".periodicalName").text(periodicalName);
	newRow.find(".periodicalSn").text(periodicalSn);
	newRow.find(".publishTime").text(publishTime);
	newRow.find(".isForeignLanguage").text(isForeignLanguage);
	newRow.find(".isCore").text(isCore);
	newRow.find(".pdfId a").attr("href","../requestPdf?pdfId="+pdfId);
	newRow.find(".firstExpertName").text(firstExpertName);
	newRow.find(".firstEvalResult").text(firstEvalResult);
	newRow.find(".firstRemark").text(firstRemark);
	newRow.find(".finalResult").text(finalResult);
	newRow.find(".reward").text(reward);
	
	$("#contentTable tbody").append(newRow);
}

function insertExpert(expertName, evalResult, remark){
	var newRow=$("#templates .furtherExpert").clone(true);
	newRow.find(".expertName").text(expertName);
	newRow.find(".evalResult").text(evalResult);
	newRow.find(".remark").text(remark);
	
	$("#contentTable tbody").append(newRow);
	increaseRowSpan();
}

function increaseRowSpan(){
	var targetTuples=$("#contentTable .fullColumnRow:last td");
	for(var i=0;i<targetTuples.length;i++){
		if(targetTuples.eq(i).attr("class")=="firstExpertName"){
			continue;
		}else if(targetTuples.eq(i).attr("class")=="firstEvalResult"){
			continue;
		}else if(targetTuples.eq(i).attr("class")=="firstRemark"){
			continue;
		}
		targetTuples.eq(i).attr("rowSpan",parseInt(targetTuples.eq(i).attr("rowSpan"))+1);
	}
}

function TestA(){
	insertNewRow("院系XXX", "作者小明", "论文名", "期刊名balabala", "期刊号xxxx", "xxxx年xx月xx日", "是", "是", "pdfIdAbcdefg", "专家XXX", "不属于", "嗯嗯嗯", "不属于", "500");
}

function TestB(){
	insertExpert("专家YYY", "不属于", "嗯嗯嗯");
}