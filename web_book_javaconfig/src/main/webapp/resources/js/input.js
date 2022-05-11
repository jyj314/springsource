/**
 * 
 */
 $(function(){
	//입력 버튼이 클릭하면
	$(".btn-primary").click(function(e){
		//submit 기능 중지
		e.preventDefault();
		//code,price 요소 가져오기
		let code = $('#code');
		let price = $('#price');
		
		//사용자 입력값 가져온 후 숫자로 들어왔는지 확인하기
		//숫자가 아니라면 메세지 띄우고 되돌아가기
			// if(isNaN(code.val())){
		if(!/^[0-9]{4}$/.test(code.val())){
			alert('코드를 확인해 주세요');
			code.select(); //틀린곳에가서 표시해줌
			return;
		}
		
		if(!/^[0-9]+$/.test(price.val())){
			alert('가격을 확인해 주세요');
			price.select(); //틀린곳에가서 표시해줌
			return;
		}
		//입력이 올바로 되었다면 form submit 시키기
		$("form").submit();

	})
	
})