// 현재 나의 위치 가져오기
function fetchPosition(event) {
	const position = navigator.geolocation.getCurrentPosition(success);
}

function success(position) {
	const crd = position.coords;
	const lat = crd.latitude;
	const lnt = crd.longitude;
	document.getElementById("lat").value = lat;
	document.getElementById("lnt").value = lnt;
}

function getNearWifiInfo() {
	const lat = document.getElementById("lat").value;
	const lnt = document.getElementById("lnt").value;
	location.href = `/mission01?lat=${lat}&lnt=${lnt}`;
	
}