var editBookModal = document.getElementById("editBookModal");
editBookModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var bookId = button.getAttribute("data-bs-bookId");
    var bookName = button.getAttribute("data-bs-bookName");
    var author = button.getAttribute("data-bs-author");
    var description = button.getAttribute("data-bs-description");
    var quantity = button.getAttribute("data-bs-quantity");

    var modalBodyInputBookId = editBookModal.querySelector("#bookId");
    var modalBodyInputBookName = editBookModal.querySelector("#bookName");
    var modalBodyInputAuthor = editBookModal.querySelector("#author");
    var modalBodyInputDescription = editBookModal.querySelector("#description");
    var modalBodyInputQuantity = editBookModal.querySelector("#quantity");

    modalBodyInputBookId.value = bookId;
    modalBodyInputBookName.value = bookName;
    modalBodyInputAuthor.value = author;
    modalBodyInputDescription.value = description;
    modalBodyInputQuantity.value = quantity;
});

var delBookModal = document.getElementById("delBookModal");
delBookModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var delBookId = button.getAttribute("data-bs-bookId");

    var modalBodyInputBookId = delBookModal.querySelector("#delBookId");

    modalBodyInputBookId.value = delBookId;
});

