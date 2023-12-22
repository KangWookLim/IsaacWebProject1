function initEvt() {

    $('#loginBtn').on('click', function(evt){
        login();
    });
}

function validated(){
    const ID = $('#ID');
    const PW = $('#PW');

    if($.trim(ID.val()).length === 0) {
        alert('아이디를 입력하십시오.');
        ID.focus();
        return false;
    }

    if($.trim(PW.val()).length === 0) {
        alert('패스워드를 입력하십시오.');
        PW.focus();
        return false;
    }

    return true;
}

function login() {

    if(validated()) {
        const dataParam = {
            ID : $('#ID').val(),
            PW : $('#PW').val()
        }

        $.ajax({
            url : '/mem/login',
            type : 'post',
            dataType : 'json',
            data : dataParam
        }).done(function(data){
            if(data.resultCode === 200) {
            	alert('성공');
                location.href ="/";
            }else {
                console.log(data.resultCode);
                alert('아이디 또는 패스워드를 확인하십시오.');
                return false;
            }
        }).fail(function(xhr, status, error){
            alert('에러코드 : ' + xhr.status);
        });

    }

}

$(document).ready(function(){
    initEvt();
});
