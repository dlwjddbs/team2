const NumberTextInputRenderer = function(props) {
    const el = document.createElement('input');
    el.type = 'text';
    el.style.width = 'calc(100% - 10px)';
    el.style.padding = '6px 7px';
    el.style.border = 'solid 1px #ddd';
    el.style.margin = 'auto 5px';
    el.style.textAlign = 'right'; // **우측 정렬 추가**

    this.el = el;
    this.render(props); // 초기값 설정
}

NumberTextInputRenderer.prototype.getElement = function () {
    return this.el;
}

NumberTextInputRenderer.prototype.render = function (props) {
    this.el.value = this.formatNumber(props.value || ''); // 값이 있으면 콤마 추가하여 출력
}

// **천 단위 콤마 추가 함수**
NumberTextInputRenderer.prototype.formatNumber = function(value) {
    if (!value) return '';
    return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 숫자 3자리마다 콤마 추가
}

class CustomNumberEditor {
    constructor(props) {
        const el = document.createElement('input');
        el.type = 'text'; // 숫자만 입력 가능하도록 처리
        el.style.width = '100%';
        el.style.textAlign = 'right';
        el.value = props.value ? this.formatNumber(props.value) : ''; // 초기값 콤마 적용

        this.el = el;
    }

    getElement() {
        return this.el;
    }

    getValue() {
        // 저장될 값은 콤마 없는 숫자로 반환
        return this.el.value.replace(/,/g, '');
    }

    mounted() {
        this.el.focus(); // 자동 포커스

        // **입력할 때 숫자만 남기고, 앞자리 0 제거, 천 단위 콤마 추가**
        this.el.addEventListener('input', (event) => {
            let rawValue = event.target.value.replace(/[^0-9]/g, ''); // 숫자 이외 제거
            rawValue = rawValue.replace(/^0+/, ''); // 앞자리 0 제거
            event.target.value = this.formatNumber(rawValue); // 콤마 추가
        });
    }

    // **천 단위 콤마 추가 함수**
    formatNumber(value) {
        return value.replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 천 단위 콤마 추가
    }
}