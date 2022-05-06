/**
 * uploadform_ajax 스크립트
 */

 $(function(){
	$("#uploadBtn").click(function(){
		console.log("ajax 파일 업로드 호출");
		
		//폼 객체 생성
		let formData = new FormData();
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']")
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		//폼 객체에 첨부파일들 추가
		for(let i=0;i<files.length;i++){
			formData.append("uploadFile",files[i]);
		}
		
		//enctype="multipart/form-data" 와 같은 코드의 의미
		
		//processDAta:false => 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		//                     기본값은 true(application/x-www-form-urlencoded)
		//comtentType:false=> 기본값은 true
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			data:formData,
			dataType:'json',
			success:function(result){
				//console.log(result);
				showUploadFile(result);
			}
		})
	}) //upload Btn 종료
	
	function showUploadFile(result){
		let uploadResult = $(".uploadResult ul");
		
		let str ="";
		
		$(result).each(function(idx,obj){
			
			if(obj.fileType){ //이미지 파일
				str += "<li>"+obj.fileName+"</li>";			
			}else{ //txt 파일
				str += "<li><img src='/resources/img/attach.png'><div>"+obj.fileName+"</div></li>";
			}

		});
		
		uploadResult.append(str);
	}//showUploadFile 종료
})






