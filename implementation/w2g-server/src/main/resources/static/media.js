function updateMediaFields(id, title, duration) {
    document.getElementById("mediaUpdate").elements["id"].value = id;
    document.getElementById("mediaUpdate").elements["title"].value = title;
    document.getElementById("mediaUpdate").elements["duration"].value = duration;
}

function updateAlbumFields(id, title, authors) {
    document.getElementById("albumUpdate").elements["id"].value = id;
    document.getElementById("albumUpdate").elements["title"].value = title;
    document.getElementById("albumUpdate").elements["authors"].value = authors;
}
