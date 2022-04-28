/**
 * register.html 폼 유효성 검증
 * jquery validation plug-in 사용
 */
$(function () {
  $("#regist").validate({
    //rules 정하기-유효성 검정 규칙 지정
    //폼 요소 이름 사용
    rules: {
      userid: {
        required: true,
        validId: true,
		remote:{ //ajax
			url:'/member/checkId',
			type:'post',
			data:{
				userid:function(){
					return $("#userid").val();
				}
			}
		}
      },
      password: {
        required: true,
        validPwd: true,
      },
      confirm_password: {
        required: true,
        equalTo: "#password", //현재 요소가 어떤 요소랑 값이 같아야 하는가?(아이디 사용)
        validPwd: true,
      },
	  name: {
        required: true,
      },
      gender: {
        required: true,
      },
      email: {
        required: true,
        email: true,
      },
    }, //rules 종료

    //규칙이 맞지 않을 경우 보여줄 메세지 작성
    messages: {
      userid: {
        required: "아이디 필수 입력 요소",
		remote:"아이디는 사용 중입니다.",
      },
      password: {
        required: "비밀번호 필수 입력 요소",
      },
      confirm_password: {
        required: "비밀번호 재입력 필수 입력 요소",
        equalTo: "비밀번호 불일치",
      },
      gender: {
        required: "성별 필수 입력 요소",
      },
      email: {
        required: "이메일 필수 입력 요소",
        email: "이메일 형식을 확인해주세요",
      },
      name: {
        required: "이름 필수 입력 요소",
      },
    },
	
	errorPlacement:function(error,element){
		$(element).closest("form")
				  .find("\small[id='"+element.attr('id')+"']")
				  .append(error);
	}
  });
});

$.validator.addMethod(
  "validId",
  function (data) {
    const regId = /^(?=.*[A-za-z])(?=.*[0-9])[A-Za-z0-9]{6,12}$/;
    return regId.test(data);
  },
  "아이디는 영문자, 숫자의 조합으로 6~12자리로 만들어야 합니다"
);

$.validator.addMethod(
  "validPwd",
  function (data) {
    const regPwd =
      /^(?=.*[A-za-z])(?=.*[0-9])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,15}$/;
    return regPwd.test(data);
  },
  "비밀번호는 영문자,숫자,특수문자의 조합으로 8~15자리로 만들어야 합니다"
);

