
let bomGrid  = undefined;
let test1Grid = undefined;
let inputGrid = undefined;

$(document).on("shown.bs.modal", "#materialInput", function() {
	
//	---------------- Modal Number Pad ----------------
//	console.log("모달 열림");
//	$("#keyboard").on("click", ".key", function() {
//		let value = $(this).data("value");
//		console.log(value);
//		$("#input").val(function(index, currentVal) {
//			return currentVal + value;
//		});
//	});
//	
//	$("#delete").click(function() {
//		$("#input").val(function(index, currentVal) {
//			return currentVal.slice(0, -1);
//		});
//	});
//	
//	$("#clear").click(function() {
//		$("#input").val("");
//	});

	
//	------------------- Toast Grid -------------------
	
	// 중복 생성 방지
    if (orderDetailGrid) {
        console.log("✅ 기존 Grid 리로드");
        orderDetailGrid.reloadData();
        return;
    }

//	const url = '/ajax/toastTest';
	const dataSource = {
	};
	
	$(function () {
	    grid();
	});
	
	function grid() {
	    orderDetailGrid = new tui.Grid({
	        el: document.getElementById('bom_grid'),
	        data: dataSource,
	        scrollX: true,
	        scrollY: true,
	        rowHeaders: ['rowNum'],
	        columns: [
	            {header: 'ID', name: 'ID', align: 'center', editor: 'text'},
	            {header: 'COL_1', name: 'COL_1', align: 'center', editor: 'text'},
	            {header: 'COL_2', name: 'COL_2', align: 'center', editor: 'text'},
	            {header: 'COL_3', name: 'COL_3', align: 'center', editor: 'text'},
	        ],
	    });
		
		// cell 선택 -> 전체 row 선택
		orderDetailGrid.on('focusChange', (ev) => {
		  orderDetailGrid.setSelectionRange({
		    start: [ev.rowKey, 0],
		    end: [ev.rowKey, orderDetailGrid.getColumns().length]
		  });
		});	
	} //function grid() 끝
});