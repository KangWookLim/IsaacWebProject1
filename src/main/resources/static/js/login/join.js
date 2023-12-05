 let isUserIdChk = false;

function userIdChk() {
    const ID = $('#ID').val();
    isUserIdChk = false;

    if($.trim(ID).length === 0) {
        alert('아이디를 입력하십시오.');
        return false;
    }

    $.ajax({
        url : '/mem/chk',
        type : 'get',
        dataType : 'json',
        data : 'json',
        data :{
            ID : ID
        }
    }).done(function (data){
        if(data === 0) {
            alert('사용가능한 ID 입니다');
            console.log(data);
            isUserIdChk = true;
            $('#idChkBtn').prop('disabled', true);
        }else {
            alert('이미 등록된 ID 입니다.');
        }
    }).fail(function (xhr, status, error) {
        alert('시스템에 문제가 발생했습니다. 관리자에게 문의해주세요.');
        console.log(status);
    });
}

function validated () {
    const inputAll = document.querySelectorAll("input");
    let isValid = true;

    if(!isUserIdChk) {
        alert('아이디 중복체크를 실행해주세요');
        isValid = false;
        return false;
    }

    $.each(inputAll, function (index, obj) {
        const inputId = $(obj).attr('id');
        checkObj = $('#' + inputId);
        let message = '';

        if(inputId === 'ID') {
            message = "아이디를 입력하십시오.";
        }else if(inputId === 'memPasswd') {
            message = '패스워드를 입력하십시오';
        }else if(inputId === 'memPasswdConfirm') {
            message = '패스워드를 입력하십시오';
        }else if(inputId === 'memName') {
            message = "이름을 입력하십시오";
        }
    });
        if( $('#PW').val() !== $('#memPasswdConfirm').val()) {
            alert('비밀번호 확인이 일치하지 않습니다!');
            isValid = false;
            return false;
        }


    return isValid;
}


function join(){
    if(validated()) {
        $('#frm').submit();
    }
}
