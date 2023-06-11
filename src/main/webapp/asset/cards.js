var updateCardModal = document.getElementById("updateCardModal");
updateCardModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var updateCardId = button.getAttribute("data-bs-cardId");

    var modalBodyInputCardId = updateCardModal.querySelector("#updateCardId");

    modalBodyInputCardId.value = updateCardId;
});

var delCardModal = document.getElementById("delCardModal");
delCardModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var delCardId = button.getAttribute("data-bs-cardId");

    var modalBodyInputCardId = delCardModal.querySelector("#delCardId");

    modalBodyInputCardId.value = delCardId;
});