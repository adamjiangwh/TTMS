function editInfo(obj) {    
    var tds= $(obj).parent().parent().find('td');  
    $('#mdyname').val(tds.eq(0).text());  
 //   $('#mdysex').val(tds.eq(1).text());
 //   $('#mdytype').val(tds.eq(2).text());
    $('#mdyphone').val(tds.eq(3).text());
    $('#mdyaddr').val(tds.eq(4).text());
    $('#mdyemail').val(tds.eq(5).text());
    $('#mdyno').val(tds.eq(6).text());
    $('#mdypass').val(tds.eq(7).text());

    var sex = tds.eq(1).text();   
    if (sex == '女') {    
        document.getElementById('mdysexwomen').checked = true;    
    } else {    
        document.getElementById('mdysexman').checked = true;    
    }
    $('#modifyModal').modal('show');   
    
    var type = tds.eq(2).text();
    if(type == '管理员') {
    	document.getElementById('mdytypeadmin').checked = true;
    } else {
    	document.getElementById('mdytypeuser').checked = true;
    }
    $('#modifyModal').modal('show');
}    
/*
//提交更改    
function update() {    
    //获取模态框数据    
    var name = $('#mdyname').val();        
    var sex = $('input:radio[name="sex"]:checked').val();
    var type = $('input:radio[name="type"]:checked').val();
    var phone = $('mdyphone').val();
    var addr = $('#mdyaddr').val();
    var email = $('#mdyemail').val();   
    var no = $('#mdyno').val();
    var pass = $('#mdypass').val();
    if(type == '管理员') {
    	type = 1;
    } else {
    	type = 0;
    }
    $.ajax({    
        type: "post",    
        url: "People?method=update",    
        data: "name=" + name + "&sex=" + sex + "&type=" + type + "&phone=" + phone+ "&addr=" + addr+ "&email=" + email +"&no=" + no + "&pass=" + pass,    
        dataType: 'html',    
        contentType: "application/x-www-form-urlencoded; charset=utf-8",    
        success: function(result) {    
            //location.reload();    
        }    
    });   
    $('#modal').modal('hide');    
}*/