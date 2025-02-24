$(document).on("shown.bs.modal", "#productionInspection", function () {
    
    let rejectionQuantity = "";
    let selectedDefectData = null;
    let selectedCauseData = null;
    let defectGridFocusedRowKey = null;  
    let inputGridFocusedRowKey = null;  
	
	let process_id = "";
	
    defectCode();
    defectInputGrid();

    // ------------------- Modal Number Pad -------------------
    $(document).on("click", ".key", function () {
        rejectionQuantity += $(this).data("value");
        updateRejectionQuantity();
    });

    $(document).on("click", "#delete", function () {
        rejectionQuantity = rejectionQuantity.slice(0, -1);
        updateRejectionQuantity();
    });

    $(document).on("click", "#clear", function () {
        rejectionQuantity = "";
        updateRejectionQuantity();
    });

    function updateRejectionQuantity() {
        $("#error").text("");
        $("#save").prop("disabled", false);
        
        let numValue = parseInt(rejectionQuantity, 10) || 0;
        if (defectInputGrid && inputGridFocusedRowKey !== null) {
            defectInputGrid.setValue(inputGridFocusedRowKey, 'DEFECT_QUANTITY', numValue);
            defectInputGrid.refreshLayout();
        }
    }

    // ------------------- 불량 코드 Grid (defectGrid) -------------------
    function defectCode() {
		process_id = currentWorkcenterId.split('-')[1];
        const url = '/inspection/defectCode';
        const defectDataSource = {
            api: { readData: { url: url, method: 'GET', initParams: {'process_id': process_id} } },
            contentType: 'application/json',
        };

        defectGrid = new tui.Grid({
            el: document.getElementById('defect_grid'),
            data: defectDataSource,
            scrollX: false,
            scrollY: false,
            columns: [
                { header: '코드', name: 'DEFECT_ID', align: 'center', editor: 'text' },
                { header: '불량명', name: 'DEFECT_NAME', align: 'center', editor: 'text' },
            ]
        });

        defectGrid.on('focusChange', (ev) => {
            defectGrid.setSelectionRange({
                start: [ev.rowKey, 0],
                end: [ev.rowKey, defectGrid.getColumns().length]
            });
            selectedDefectData = defectGrid.getRow(ev.rowKey);
            defectGridFocusedRowKey = ev.rowKey;
            $(".defect-type").removeClass("defect-type-hidden");
        });
    }

    // ------------------- 불량유형 버튼 클릭 시 원인 코드 Grid (causeGrid) 활성화 -------------------
    $(document).on("click", ".defect-type", function () {
        let causeType = $(this).data("value");
        causeCode(causeType);
    });

    // ------------------- 불량 원인 코드 Grid (causeGrid) -------------------
    function causeCode(causeType) {
        if (causeGrid) {
            causeGrid.destroy();
            causeGrid = null;
        }

        const url = '/inspection/defectCauseCode';
        const causeDataSource = {
            api: { readData: { url: url, method: 'GET', initParams: { 'cause_type': causeType, 'process_id': process_id } } },
            contentType: 'application/json',
        };

        causeGrid = new tui.Grid({
            el: document.getElementById('cause_grid'),
            data: causeDataSource,
            scrollX: false,
            scrollY: false,
            columns: [
                { header: '코드', name: 'CAUSE_ID', align: 'center', editor: 'text' },
                { header: '불량유형', name: 'SUB_NAME', align: 'center', editor: 'text' },
                { header: '설명', name: 'DESCRIPTION', align: 'center', editor: 'text' },
            ]
        });

        causeGrid.on('focusChange', (ev) => {
            causeGrid.setSelectionRange({
                start: [ev.rowKey, 0],
                end: [ev.rowKey, causeGrid.getColumns().length]
            });
            selectedCauseData = causeGrid.getRow(ev.rowKey);
            if (selectedDefectData && selectedCauseData) {
                updateDefectInputGrid(selectedDefectData, selectedCauseData);
            }
        });
    }

    // ------------------- 불량 입력 Grid (defectInputGrid) -------------------
    function defectInputGrid() {
        class ButtonRenderer {
            constructor(props) {
                const el = document.createElement('button');
                el.textContent = '불량취소';
                el.classList.add('btn', 'btn-danger', 'btn-sm');
                el.addEventListener('click', () => {
                    const rowKey = props.rowKey;
                    defectInputGrid.removeRow(rowKey);
                });
                this.el = el;
            }
            getElement() {
                return this.el;
            }
        }

        defectInputGrid = new tui.Grid({
            el: document.getElementById('defect_input_grid'),
            scrollX: false,
            scrollY: false,
            columns: [
                //{ header: '작업지시 ID', name: 'PRODUCTION_ORDER_ID', align: 'center' },
                //{ header: '작업지시 상세 ID', name: 'PRODUCTION_ORDER_DETAIL_ID', align: 'center' },
                { header: '제품ID', name: 'ITEM_ID', align: 'center' },
                { header: '불량코드', name: 'DEFECT_CODE', align: 'center' },
                { header: '불량명', name: 'DEFECT_NAME', align: 'center' },
                { header: '불량유형코드', name: 'DEFECT_CAUSE_CODE', align: 'center' },
                { header: '불량유형명', name: 'DEFECT_CAUSE_NAME', align: 'center' },
                { header: '생산수량', name: 'TOTAL_QUANTITY', align: 'center' },
                { header: '불량수량', name: 'DEFECT_QUANTITY', align: 'center' },
                { header: '불량취소', name: 'CANCEL', renderer: { type: ButtonRenderer }, align: 'center' },
            ],
        });

        defectInputGrid.on('focusChange', (ev) => {
            defectInputGrid.setSelectionRange({
                start: [ev.rowKey, 0],
                end: [ev.rowKey, defectInputGrid.getColumns().length]
            });
            inputGridFocusedRowKey = ev.rowKey;
            rejectionQuantity = "";
        });
    }

    // ------------------- defectInputGrid 업데이트 (선택된 불량 + 원인 데이터 반영) -------------------
    function updateDefectInputGrid(defectData, causeData) {
        let data = defectInputGrid.getData();
        let existingRowKey = null;
        
        for (let i = 0; i < data.length; i++) {
            if (data[i].DEFECT_ID === defectData.DEFECT_ID && data[i].DEFECT_CAUSE_ID === causeData.CAUSE_ID) {
                existingRowKey = i;
                break;
            }
        }
		
        if (existingRowKey !== null) {
            defectInputGrid.focus(existingRowKey, 'DEFECT_QUANTITY');
            inputGridFocusedRowKey = existingRowKey;
        } else {
            let newRowKey = defectInputGrid.getRowCount();
            defectInputGrid.appendRow({
                PRODUCTION_ORDER_ID: window.selectedProductionRow.PRODUCTION_ORDER_ID,
                PRODUCTION_ORDER_DETAIL_ID: window.selectedProductionRow.PRODUCTION_ORDER_DETAIL_ID,
                ITEM_ID: window.selectedProductionRow.ITEM_ID,
                DEFECT_CODE: `${process_id}-${defectData.DEFECT_ID}`,
                DEFECT_NAME: defectData.DEFECT_NAME,
                DEFECT_CAUSE_CODE: causeData.CAUSE_ID,
                DEFECT_CAUSE_NAME: causeData.SUB_NAME,
                TOTAL_QUANTITY: window.selectedProductionRow.AMOUNT,
                DEFECT_QUANTITY: 0,
            });
            defectInputGrid.focus(newRowKey, 'DEFECT_QUANTITY');
            inputGridFocusedRowKey = newRowKey;
        }
        rejectionQuantity = "";
    }
	
	$('#save').on('click', function () {
	    let saveButton = $('#save');
	    
	    if (saveButton.prop('disabled')) {
	        return;
	    }
	    saveButton.prop('disabled', true);
	    
	    let totalCount = defectInputGrid.getData()
	                        .map(row => parseInt(row.DEFECT_QUANTITY, 10) || 0)
	                        .reduce((acc, cur) => acc + cur, 0);
	    
	    let allowedCount = parseInt(window.selectedProductionRow.AMOUNT, 10) || 0;
	    
	    if (totalCount > allowedCount) {
	        $("#error").text("불량 수량이 검사 수량을 초과할 수 없습니다. 불량수량: " 
	            + totalCount + ", 생산수량: " + allowedCount);
	        saveButton.prop("disabled", true);
	        return;
	    }
	    
	    let requestData = defectInputGrid.getData();
	    let filteredData = requestData.filter(item => parseInt(item.DEFECT_QUANTITY, 10) !== 0);
	    
	    if (filteredData.length === 0) {
	        filteredData.push({ PRODUCTION_ORDER_DETAIL_ID: window.selectedProductionRow.PRODUCTION_ORDER_DETAIL_ID });
	    }
		
	    $.ajax({
	        url: '/ajax/saveProductionInspection',
	        type: 'POST',
	        contentType: 'application/json',
	        data: JSON.stringify(filteredData),
	        success: function (response) {
	            if(response.result){
	                $('#productionInspection').modal('hide');
	                $('body').removeClass('modal-open');
	                $('.modal-backdrop').remove();
					location.reload();
	            }
	        },
	        error: function (xhr, status, error) {
	            console.error("모달 저장 실패", xhr, status, error);
	        },
	        complete: function () {
	            saveButton.prop('disabled', false);
	        }
	    });
	});

	
});
