document.querySelector(".request").addEventListener("click", event => {
    requestActivity();
});

function requestActivity() {
    const url = "https://www.boredapi.com/api/activity";
    const http = new XMLHttpRequest();
    http.open("GET", url);

    http.onreadystatechange = function() {
        if (http.readyState === 4 && http.status === 200) {
            insertOutputResponse(this.response);
        }
    };

    http.send();
}

function insertOutputResponse(response) {
    let decodedResponse = JSON.parse(response);
    let container = document.querySelector(".activity");

    container.innerHTML = "";

    let activity = document.createElement("h4");
    activity.append(decodedResponse.activity);
    container.append(activity);

    let link = document.createElement("a");
    link.href = decodedResponse.link;
    link.text = decodedResponse.link;
    link.target = "_blank";
    container.append(link);

    let type = document.createElement("p");
    type.append(decodedResponse.type);
    container.append(type);
}