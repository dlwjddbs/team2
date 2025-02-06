$(document).on("shown.bs.modal", "#inboundInspection", function() {
    let rejectionGrid = null;
    let rejectionInputGrid = null;
    let rejectionQuantity = "";  // 넘버패드 입력값 저장
    let selectRejection = null;
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
        let numValue = parseInt(rejectionQuantity, 10) || 0;
        console.log("Updated Rejection Quantity:", numValue);

        if (rejectionInputGrid && focusedRowKey !== null) {
            rejectionInputGrid.setValue(focusedRowKey, 'REJECTION_QUANTITY', numValue);
            rejectionInputGrid.refreshLayout();  // UI 강제 업데이트
        }
    }

    // ------------------- Rejection Grid -------------------
    function initializeGrid() {
        if (rejectionGrid) {
            rejectionGrid.destroy();
        }

        const url = '/inspection/inbound';
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

            if (selectRejection && rejectionInputGrid) {
                updateRejectionInputGrid(selectRejection);
            }
        });

        initializeInputGrid();
    }

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
		
        rejectionInputGrid = new tui.Grid({
            el: document.getElementById('rejectioni_input_grid'),
            data: [],
            scrollX: false,
            scrollY: false,
            rowHeaders: ['rowNum'],
            columns: [
                { header: '불량명', name: 'REJECTION', align: 'center' },
                { header: '불량수량', name: 'REJECTION_QUANTITY', align: 'center' },
                { header: 'LOT', name: 'LOT', align: 'center' },
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
            rejectionQuantity = "";  // 포커스 변경될 때 입력값 초기화
            console.log("포커스된 rowKey:", focusedRowKey, "rejectionQuantity 초기화됨");
        });
    }

    function updateRejectionInputGrid(selectedRejection) {
        let data = rejectionInputGrid.getData();
        let existingRowKey = null;

        // 기존 데이터에서 해당 불량명이 있는지 확인
        for (let i = 0; i < data.length; i++) {
            if (data[i].REJECTION === selectedRejection) {
                existingRowKey = i;
                break;
            }
        }

        if (existingRowKey !== null) {
            // 이미 존재하면 해당 행으로 포커스 이동
            rejectionInputGrid.focus(existingRowKey, 'REJECTION_QUANTITY');
            focusedRowKey = existingRowKey;
        } else {
            // 존재하지 않으면 새로운 행 추가
            let newRowKey = rejectionInputGrid.getRowCount();
            rejectionInputGrid.appendRow({ REJECTION: selectedRejection, REJECTION_QUANTITY: 0 });
            rejectionInputGrid.focus(newRowKey, 'REJECTION_QUANTITY');
            focusedRowKey = newRowKey;
        }

        // 포커스 변경될 때 입력값 초기화
        rejectionQuantity = "";
    }

    $(function () {
        initializeGrid();
    });
});
