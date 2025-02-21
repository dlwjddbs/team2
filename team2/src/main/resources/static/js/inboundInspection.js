$(document).on("shown.bs.modal", "#inboundInspection", function() {
    
    
    let rejectionQuantity = "";  // 넘버패드 입력값 저장
    let selectRejection = null;
	let selectCode = null;
    let focusedRowKey = null;  // 현재 포커스된 rowKey 저장
	
	
    // ------------------- Modal Number Pad -------------------
    $(document).on("click", ".key", function() {
        rejectionQuantity += $(this).data("value");
        updateRejectionQuantity();
    });

    $(document).on("click", "#delete", function() {
        rejectionQuantity = rejectionQuantity.slice(0, -1);
        updateRejectionQuantity();
    });

    $(document).on("click", "#clear", function() {
        rejectionQuantity = "";
        updateRejectionQuantity();
    });

    function updateRejectionQuantity() {

		$("#error").text("");
		$("#save").prop("disabled", false);
		
		let numValue = parseInt(rejectionQuantity, 10) || 0;

        if (rejectionInputGrid && focusedRowKey !== null) {
            rejectionInputGrid.setValue(focusedRowKey, 'DEFECT_QUANTITY', numValue);
            rejectionInputGrid.refreshLayout();  // UI 강제 업데이트
        }
    }

    // ------------------- Rejection Grid -------------------
    function initializeGrid() {
		if (rejectionGrid) {
			rejectionGrid.destroy();
			rejectionInputGrid.destroy();
			rejectionInputGrid = null;
			rejectionGrid = null;
        }

        const url = '/inspection/rejectionCode';
        const rejectionDataSource = {
            api: { readData: { url: url, method: 'GET' } },
            contentType: 'application/json',
        };
		
        rejectionGrid = new tui.Grid({
            el: document.getElementById('rejection_grid'),
            data: rejectionDataSource,
            scrollX: false,
            scrollY: false,
            columns: [
                { header: '코드', name: 'CODE_ID', align: 'center', editor: 'text' },
                { header: '불량명', name: 'REJECTION', align: 'center', editor: 'text' },
            ]
        });

        // 셀 선택 시 전체 행 선택 및 rejectionInputGrid 업데이트
        rejectionGrid.on('focusChange', (ev) => {
            rejectionGrid.setSelectionRange({
                start: [ev.rowKey, 0],
                end: [ev.rowKey, rejectionGrid.getColumns().length]
            });

            selectRejection = rejectionGrid.getValue(ev.rowKey, "REJECTION");
			selectCode = rejectionGrid.getValue(ev.rowKey, "CODE_ID");
            if (selectRejection && rejectionInputGrid) {
                updateRejectionInputGrid(selectRejection, po_id,);
            }
        });
		
        initializeInputGrid();
    }
	// ------------------- rejection Input Grid -------------------
    function initializeInputGrid() {
		// Button 
		class ButtonRenderer {
			constructor(props) {
				const el = document.createElement('button');
				el.textContent = '불량취소';
				el.classList.add('btn', 'btn-danger', 'btn-sm');
				
				el.addEventListener('click', () => {
					const rowKey = props.rowKey;
					rejectionInputGrid.removeRow(rowKey);
					
				});
				
				this.el = el;
			}
			getElement() {
				return this.el;
			}
		}
		
		const url = `/inspection/inboundInspection?podetail_id=${encodeURIComponent(po_detail_data.PODETAIL_ID)}`;
        const rejectionInputGridDate = {
            api: { readData: { url: url, method: 'GET' } },
            contentType: 'application/json',
        };
		
		console.log(rejectionInputGridDate);
		
        rejectionInputGrid = new tui.Grid({
            el: document.getElementById('rejectioni_input_grid'),
            data: rejectionInputGridDate,
            scrollX: false,
            scrollY: false,
            columns: [
                { header: 'LOT', name: 'INSPECTION_ID', align: 'center'},
                { header: '주문번호', name: 'PO_ID', align: 'center'},
                { header: '주문상세', name: 'PODETAIL_ID', align: 'center' },
                { header: '불량코드', name: 'REJECTION_CODE', align: 'center' },
                { header: '불량명', name: 'REJECTION', align: 'center' },
                { header: '불량수량', name: 'DEFECT_QUANTITY', align: 'center' },
                { header: '불량취소', name: 'CANCEL', renderer: {type: ButtonRenderer}, align: 'center' },
            ],
        });

        // 포커스된 rowKey 저장 + 포커스 변경 시 rejectionQuantity 초기화
        rejectionInputGrid.on('focusChange', (ev) => {
			rejectionInputGrid.setSelectionRange({
                start: [ev.rowKey, 0],
                end: [ev.rowKey, rejectionInputGrid.getColumns().length]
            });
			
            focusedRowKey = ev.rowKey;
            rejectionQuantity = "";
        });
    }

    function updateRejectionInputGrid(selectedRejection) {
        let data = rejectionInputGrid.getData();
        let existingRowKey = null;

        // 기존 데이터에서 해당 불량명이 있는지 확인
        for (let i = 0; i < data.length; i++) {
            if (data[i].REJECTION_CODE === selectCode) {
                existingRowKey = i;
                break;
            }
        }

        if (existingRowKey !== null) {
            // 이미 존재하면 해당 행으로 포커스 이동
            rejectionInputGrid.focus(existingRowKey, 'DEFECT_QUANTITY');
            focusedRowKey = existingRowKey;
        } else {
            // 존재하지 않으면 새로운 행 추가
            let newRowKey = rejectionInputGrid.getRowCount();
            rejectionInputGrid.appendRow({ 
				INSPECTION_ID: '',
				PO_ID: po_id,
				PODETAIL_ID: po_detail_data.PODETAIL_ID,
				REJECTION_CODE: selectCode,
				REJECTION: selectedRejection,
				DEFECT_QUANTITY: 0
			});
            rejectionInputGrid.focus(newRowKey, 'DEFECT_QUANTITY');
            focusedRowKey = newRowKey;
        }

        rejectionQuantity = "";
    }

    $(function () {
        initializeGrid();
    });
	
// ------------------------------------------------- < 저장 버튼 >	
	 $('#save').on('click', function () {
		
		let save = $('#save');
		
		if (save.prop('disabled')) {
			return;
		}
		save.prop('disabled', true);
		
		
		// 불량 수량 전체 합계
		let total_count = rejectionInputGrid.getData()
					        .map(row => parseInt(row.DEFECT_QUANTITY, 10) || 0)
					        .reduce((acc, cur) => acc + cur, 0);
				
		if (total_count > po_detail_data.PO_COUNT) {
		    $("#error").text("불량 수량이 검사 수량을 초과할 수 없습니다. total_count: ",total_count, "po_count: ", po_detail_data.PO_COUNT);
		    $("#save").prop("disabled", true);
			return;
		}
		
		
		let requestData = rejectionInputGrid.getData();
		let filteredData = requestData.filter(item => item.DEFECT_QUANTITY !== 0);
		
		if (filteredData.length === 0) {
	       filteredData.push({ PODETAIL_ID: po_detail_data.PODETAIL_ID });
	   }
		
		$.ajax({
			url: '/ajax/saveInboundInspection',
			type: 'POST',
			contentType: 'application/json',
			data: JSON.stringify(filteredData),
			success: function (response) {
				if(response.result){
					$('#inboundInspection').removeClass('show');
		            $('body').removeClass('modal-open');
		            $('.modal-backdrop').remove();
					inboundDetailGrid.readData();
				}
			},
			error: function (xhr, status, error) {
				console.error("모달 저장 실패", xhr, status, error);
			},
			complete: function() {
				save.prop('disabled', false);
			}
			
		});
		
	});
});
