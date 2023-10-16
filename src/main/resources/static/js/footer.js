function measureLoadingTime() {
    let pageLoadStart = performance.timing.navigationStart
    let pageLoadEnd = performance.timing.domContentLoadedEventEnd
    
    let totalLoadingTime = pageLoadEnd - pageLoadStart;
    let serverLoadingTime = parseInt(document.getElementById("server-time").innerHTML);
    let clientLoadingTime = totalLoadingTime - serverLoadingTime;
    document.getElementById("client-time").innerHTML = clientLoadingTime.toString();
}

window.addEventListener("load", measureLoadingTime);