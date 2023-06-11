var editStuModal = document.getElementById("editStuModal");
editStuModal.addEventListener("show.bs.modal", function (event) {

    var button = event.relatedTarget;

    var stuId = button.getAttribute("data-bs-stuId");
    var stuName = button.getAttribute("data-bs-stuName");
    var grade = button.getAttribute("data-bs-grade");

    var modalBodyInputStuId = editStuModal.querySelector("#stuId");
    var modalBodyInputStuName = editStuModal.querySelector("#stuName");
    var modalBodyInputGrade = editStuModal.querySelector("#grade");

    modalBodyInputStuId.value = stuId;
    modalBodyInputStuName.value = stuName;
    modalBodyInputGrade.value = grade;
});