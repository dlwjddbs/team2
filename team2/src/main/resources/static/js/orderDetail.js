
$(document).on("shown.bs.modal", "#orderDetail", function() {
//	---------------- Modal Number Pad ----------------
    console.log("모달 열림");
    $("#keyboard").on("click", ".key", function() {
        let value = $(this).data("value");
		console.log(value);
        $("#input").val(function(index, currentVal) {
            return currentVal + value;
        });
    });

    $("#delete").click(function() {
        $("#input").val(function(index, currentVal) {
            return currentVal.slice(0, -1);
        });
    });

    $("#clear").click(function() {
        $("#input").val("");
    });

//	------------------- Toast Grid -------------------

	let grid1;
	const url = '/ajax/toastTest';
	const dataSource = {
	    api: {
			createData: {url: url, method: 'POST'},
	        readData: {url: url, method: 'GET'},
			updateData: {url: url, method: 'PUT'},
			deleteData: {url: url, method: 'DELETE', headers: {'X-Delete-IDs': ''}}
	    },
		contentType: 'application/json',
	};
	
	$(function () {
	    grid();
	});
	
	function grid() {
	    grid1 = new tui.Grid({
	        el: document.getElementById('detail_toast_single'),
	        data: dataSource,
	        scrollX: true,
	        scrollY: true,
	        rowHeaders: ['checkbox'],
	        columns: [
	            {header: 'ID', name: 'ID', align: 'center', editor: 'text'},
	            {header: 'COL_1', name: 'COL_1', align: 'center', editor: 'text'},
	            {header: 'COL_2', name: 'COL_2', align: 'center', editor: 'text'},
	            {header: 'COL_3', name: 'COL_3', align: 'center', editor: 'text'},
	        ],
	    });
	
	    grid1.on('failResponse', function(ev) {
	        alert(JSON.parse(ev.xhr.responseText).message);
	    });
		
		
		grid1.on('editingStart', function(ev) {
		    const {rowKey, columnName, value} = ev;
		    const rowData = grid1.getRow(rowKey);
			
			// PK 수정 불가 && 새로 추가된 행은 작성 가능
			if (columnName == 'ID' && !rowData.isNew) {
				ev.stop();
			}
		});
	}
	
	$("#btn_add_row").on("click", function () {
	    const newRowKey = grid1.appendRow(
	        {
	            "ID": '',
	            "COL_1": '',
	            "COL_2": '',
	            "COL_3": '',
	            isNew: true
	        },
	        {focus: true}
	    );
	});
	
	$("#btn_insert").on("click", function() {
		grid1.request('createData');
		grid1.reloadData();  // 실패시 테이블 리로드
		// GET 요청시 데이터 보낼려면 header 설정
	});
	
	$("#btn_update").on("click", function() {
	grid1.request('updateData');
});

$("#btn_delete").on("click", function() {
	grid1.removeCheckedRows();
	
	// 반드시 removeCheckedRows()후에 실행 순서
	// DELETE는 body가 없고 url과 header만 있음, 기본값 url -> url대신 header에 담아 보냄
	const ids = grid1.getModifiedRows().deletedRows.map(row => row.ID).join(',');
	dataSource.api.deleteData.headers['X-Delete-IDs'] = ids;
	
    grid1.request('deleteData');
	grid1.reloadData();  // 실패시 테이블 리로드
});
});