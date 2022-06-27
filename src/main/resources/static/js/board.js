let index = {
	init: function() {
		$("#btn-save").bind("click", ()=> {
			this.save(); 
		});
	},
	
	save: function() {
		
		// 데이터 가져오기
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		}
		console.log("데이터 확인");
		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
			
		})
		.done(function(data, textStatus, xhr){
			if(data.status) {
				 alert("글 쓰기가 완료되었습니다.");
				 location.href="/";
			}
		})
		.fail(function(error) {
			alert("글 쓰기에 실패하였습니다.");
		});
	}
	
}

index.init();