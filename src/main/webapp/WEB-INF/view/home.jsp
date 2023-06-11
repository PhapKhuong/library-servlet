<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: phapk
  Date: 24-May-23
  Time: 2:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="/asset/bootstrap.min.css">
    <link rel="stylesheet" href="/asset/libray_style.css">
</head>
<body>
<div class="container">

    <!--TẠO THANH MENU TRÊN ĐẦU CHỨA BOOK, STUDENT, CARD-->
    <div id="menu">
        <div id="position-menu">
            <a href="/library?action=book">Book</a>
            <a href="/library?action=borrowedBook">Borrowed Book</a>
            <a href="/library?action=student">Student</a>
            <a href="/library?action=card">Card</a>
        </div>
    </div>
    <!--KẾT THÚC-->

    <!--TẠO THÂN TRANG-->
    <div id="body-page">

        <!--PHẦN NỘI DUNG CỦA TRANG BOOK-->
        <c:if test="${select==1}">
            <div class="content">

                <!--NỘI DUNG CHÍNH CỦA TRANG BOOK-->
                <div class="main-content">
                    <div class="popup">
                        <c:if test="${error!=null}">
                            <h3>${error}</h3>
                        </c:if>
                    </div>

                    <!--FORM TÌM KIẾM BOOK-->
                    <form class="d-flex" action="/library" method="post">
                        <input type="hidden" name="action" value="searchBook">
                        <input class="form-control me-2" type="search" placeholder="Search by name" aria-label="Search"
                               name="searchBookByName">
                        <input class="form-control me-2" type="search" placeholder="Search by author"
                               aria-label="Search"
                               name="searchBookByAuthor">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>

                    <!--PHẦN HIỂN THỊ BẢNG BOOK-->
                    <h2>Danh sách sách</h2>
                    <table class="table">
                        <thread>
                            <tr>
                                <th scope="col">Mã sách</th>
                                <th scope="col">Tên sách</th>
                                <th scope="col">Tác giả</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Số lượng</th>
                                <th scope="col">
                                    <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#createBookModal">
                                        Create
                                    </button>
                                </th>
                                <th scope="col"></th>
                                <th scope="col"></th>
                            </tr>
                        </thread>
                        <tbody>
                        <c:forEach items="${books}" var="book">
                            <tr class="col">
                                <td>${book.bookId}</td>
                                <td>${book.bookName}</td>
                                <td>${book.author}</td>
                                <td>${book.description}</td>
                                <td>${book.quantity}</td>
                                <td>
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="initBorrow">
                                        <input type="hidden" name="initBookId" value="${book.bookId}">
                                        <input type="hidden" name="initBookName" value="${book.bookName}">
                                        <input type="hidden" name="initAuthor" value="${book.author}">
                                        <input type="hidden" name="initDescription" value="${book.description}">
                                        <input type="hidden" name="initQuantity" value="${book.quantity}">
                                        <input type="submit" class="btn btn-primary" value="Borrow">
                                    </form>
                                </td>
                                <td>
                                    <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#editBookModal"
                                            data-bs-bookId="${book.bookId}"
                                            data-bs-bookName="${book.bookName}"
                                            data-bs-author="${book.author}"
                                            data-bs-description="${book.description}"
                                            data-bs-quantity="${book.quantity}">
                                        Edit
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--KẾT THÚC PHẦN HIỂN THỊ BẢNG BOOK-->

                    <!--TẠO MODAL THÊM SÁCH VÀO THƯ VIỆN-->
                    <div class="modal fade"
                         id="createBookModal"
                         tabindex="-1"
                         aria-labelledby="createBookModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="createBookModalLabel">Thêm sách vào thư viện</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="createBook">
                                        <div class="mb-3">
                                            <label for="createBookName" class="col-form-label">Tên sách:</label>
                                            <input type="text" class="form-control" id="createBookName"
                                                   name="createBookName"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="createAuthor" class="col-form-label">Tác giả:</label>
                                            <input type="text" class="form-control" id="createAuthor"
                                                   name="createAuthor"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="createDescription" class="col-form-label">Mô tả:</label>
                                            <input type="text" class="form-control" id="createDescription"
                                                   name="createDescription" required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="createQuantity" class="col-form-label">Số lượng:</label>
                                            <input type="number" class="form-control" id="createQuantity"
                                                   name="createQuantity"
                                                   required/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL THÊM SÁCH VÀO THƯ VIỆN-->

                    <!--TẠO MODAL SỬA THÔNG TIN SÁCH-->
                    <div class="modal fade"
                         id="editBookModal"
                         tabindex="-1"
                         aria-labelledby="editBookModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editBookModalLabel">Sửa thông tin</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="editBook">
                                        <div class="mb-3">
                                            <input type="hidden" class="form-control" id="bookId" name="bookId"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="bookName" class="col-form-label">Tên sách:</label>
                                            <input type="text" class="form-control" id="bookName" name="newBookName"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="author" class="col-form-label">Tác giả:</label>
                                            <input type="text" class="form-control" id="author" name="newAuthor"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="description" class="col-form-label">Mô tả:</label>
                                            <input type="text" class="form-control" id="description"
                                                   name="newDescription" required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="quantity" class="col-form-label">Số lượng:</label>
                                            <input type="number" class="form-control" id="quantity"
                                                   name="newQuantity"
                                                   required/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL SỬA THÔNG TIN SÁCH-->

                </div>
                <!--KẾT THÚC NỘI DUNG CHÍNH CỦA TRANG BOOK-->
            </div>
        </c:if>
        <!--KẾT THÚC PHẦN NỘI DUNG CỦA TRANG BOOK-->

        <!--PHẦN NỘI DUNG CỦA TRANG STUDENT-->
        <c:if test="${select==2}">
            <div class="content">

                <!--NỘI DUNG CHÍNH CỦA TRANG STUDENT-->
                <div class="main-content">
                    <div class="popup">
                        <c:if test="${error!=null}">
                            <h3>${error}</h3>
                        </c:if>
                    </div>

                    <!--FORM TÌM KIẾM STUDENT-->
                    <form class="d-flex" action="/library" method="post">
                        <input type="hidden" name="action" value="searchStuByName">
                        <input class="form-control me-2" type="search" placeholder="Search by name" aria-label="Search"
                               name="searchStuName">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>

                    <!--PHẦN HIỂN THỊ BẢNG STUDENT-->
                    <h2>Danh sách sinh viên</h2>
                    <table class="table">
                        <thread>
                            <tr>
                                <th scope="col">Mã sinh viên</th>
                                <th scope="col">Tên sinh viên</th>
                                <th scope="col">Lớp</th>
                                <th scope="col">
                                    <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#createStuModal">
                                        Create
                                    </button>
                                </th>
                            </tr>
                        </thread>
                        <tbody>
                        <c:forEach items="${students}" var="student">
                            <tr class="col">
                                <td>${student.stuId}</td>
                                <td>${student.stuName}</td>
                                <td>${student.grade}</td>
                                <td>
                                    <button
                                            type="button"
                                            class="btn btn-primary"
                                            data-bs-toggle="modal"
                                            data-bs-target="#editStuModal"
                                            data-bs-stuId="${student.stuId}"
                                            data-bs-stuName="${student.stuName}"
                                            data-bs-grade="${student.grade}">
                                        Edit
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--KẾT THÚC PHẦN HIỂN THỊ BẢNG STUDENT-->

                    <!--TẠO MODAL THÊM SINH VIÊN-->
                    <div class="modal fade"
                         id="createStuModal"
                         tabindex="-1"
                         aria-labelledby="createStuModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="createStuModalLabel">Thêm sinh viên</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="createStu">
                                        <div class="mb-3">
                                            <label for="createStuName" class="col-form-label">Tên sinh viên:</label>
                                            <input type="text" class="form-control" id="createStuName"
                                                   name="createStuName"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="createGrade" class="col-form-label">Lớp:</label>
                                            <input type="text" class="form-control" id="createGrade" name="createGrade"
                                                   required/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL THÊM SINH VIÊN-->

                    <!--TẠO MODAL SỬA THÔNG TIN SINH VIÊN-->
                    <div class="modal fade"
                         id="editStuModal"
                         tabindex="-1"
                         aria-labelledby="editStuModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="editStuModalLabel">Sửa thông tin sinh viên</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="editStu">
                                        <div class="mb-3">
                                            <input type="hidden" class="form-control" id="stuId" name="stuId"/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="stuName" class="col-form-label">Tên sinh viên:</label>
                                            <input type="text" class="form-control" id="stuName" name="newStuName"
                                                   required/>
                                        </div>
                                        <div class="mb-3">
                                            <label for="grade" class="col-form-label">Lớp:</label>
                                            <input type="text" class="form-control" id="grade" name="newGrade"
                                                   required/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL SỬA THÔNG TIN SINH VIÊN-->

                </div>
                <!--KẾT THÚC NỘI DUNG CHÍNH CỦA TRANG STUDENT-->

            </div>
        </c:if>
        <!--KẾT THÚC PHẦN NỘI DUNG CỦA TRANG STUDENT-->


        <!--PHẦN NỘI DUNG CỦA TRANG CARD-->
        <c:if test="${select==3}">
            <div class="content">

                <!--NỘI DUNG CHÍNH CỦA TRANG CARD-->
                <div class="main-content">
                    <div class="popup">
                        <c:if test="${error!=null}">
                            <h3>${error}</h3>
                        </c:if>
                    </div>

                    <!--FORM TÌM KIẾM CARD-->
                    <form class="d-flex" action="/library" method="post">
                        <input type="hidden" name="action" value="searchCard">
                        <input class="form-control me-2" type="search" placeholder="Search by name of book"
                               aria-label="Search"
                               name="searchCardByBookName">
                        <input class="form-control me-2" type="search" placeholder="Search by student's name"
                               aria-label="Search"
                               name="searchCardByStuName">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>

                    <!--PHẦN HIỂN THỊ BẢNG CARD-->
                    <h2>Danh sách thẻ mượn sách</h2>
                    <table class="table">
                        <thread>
                            <tr>
                                <th scope="col">Mã thẻ</th>
                                <th scope="col">Tên sách</th>
                                <th scope="col">Tác giả</th>
                                <th scope="col">Tên sinh viên</th>
                                <th scope="col">Lớp</th>
                                <th scope="col">Ngày mượn</th>
                                <th scope="col">Ngày trả</th>
                                <th scope="col"></th>
                            </tr>
                        </thread>
                        <tbody>
                        <c:forEach items="${cards}" var="card">
                            <tr class="col">
                                <td>${card.cardId}</td>
                                <td>${card.book.getBookName()}</td>
                                <td>${card.book.getAuthor()}</td>
                                <td>${card.student.getStuName()}</td>
                                <td>${card.student.getGrade()}</td>
                                <td>
                                    <fmt:parseDate value="${card.loanDate}" pattern="yyyy-MM-dd" type="date"
                                                   var="loanDateFormat"/>
                                    <fmt:formatDate value="${loanDateFormat}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <fmt:parseDate value="${card.returnDate}" pattern="yyyy-MM-dd" type="date"
                                                   var="returnDateFormat"/>
                                    <fmt:formatDate value="${returnDateFormat}" pattern="dd/MM/yyyy"/>
                                </td>
                                <td>
                                    <c:if test="${card.status == false}">
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                disabled>
                                            Returned
                                        </button>
                                    </c:if>
                                    <c:if test="${card.status ==true}">
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                data-bs-toggle="modal"
                                                data-bs-target="#updateCardModal"
                                                data-bs-cardId="${card.cardId}">
                                            Return
                                        </button>
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${card.status == false}">
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                data-bs-toggle="modal"
                                                data-bs-target="#delCardModal"
                                                data-bs-cardId="${card.cardId}">
                                            Del
                                        </button>
                                    </c:if>
                                    <c:if test="${card.status == true}">
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                                disabled>
                                            Del
                                        </button>
                                    </c:if>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <!--KẾT THÚC PHẦN HIỂN THỊ BẢNG CARD-->

                    <!--TẠO MODAL TRẢ SÁCH-->
                    <div class="modal fade"
                         id="updateCardModal"
                         tabindex="-1"
                         aria-labelledby="updateCardModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updateCardModalLabel">Trả sách</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="updateCard">
                                        <div class="mb-3">
                                            <input type="hidden" class="form-control" id="updateCardId"
                                                   name="updateCardId"/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL TRẢ SÁCH-->

                    <!--TẠO MODAL XÓA THẺ MƯỢN SÁCH-->
                    <div class="modal fade"
                         id="delCardModal"
                         tabindex="-1"
                         aria-labelledby="delCardModalLabel"
                         aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="delCardModalLabel">Xóa thẻ mượn sách</h5>
                                    <button type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close">
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="/library" method="post">
                                        <input type="hidden" name="action" value="delCard">
                                        <div class="mb-3">
                                            <input type="hidden" class="form-control" id="delCardId"
                                                   name="delCardId"/>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary"
                                                    data-bs-dismiss="modal">
                                                Close
                                            </button>
                                            <input type="submit" class="btn btn-secondary" value="OK">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--KẾT THÚC TẠO MODAL TRẢ SÁCH-->

                </div>
                <!--KẾT THÚC NỘI DUNG CHÍNH CỦA TRANG CARD-->

            </div>
        </c:if>
        <!--KẾT THÚC PHẦN NỘI DUNG CỦA TRANG CARD-->

        <!--HIỂN THỊ DANH SÁCH SÁCH ĐÃ ĐƯỢC MƯỢN-->
        <c:if test="${select==4}">
            <div class="content">
                <div class="main-content">
                    <h2>Danh sách sách đã được mượn</h2>
                    <table class="table">
                        <thread>
                            <tr>
                                <th scope="col">Mã sách</th>
                                <th scope="col">Tên sách</th>
                                <th scope="col">Tác giả</th>
                                <th scope="col">Mô tả</th>
                                <th scope="col">Số lượng</th>
                            </tr>
                        </thread>
                        <tbody>
                        <c:forEach items="${books}" var="book">
                            <tr class="col">
                                <td>${book.bookId}</td>
                                <td>${book.bookName}</td>
                                <td>${book.author}</td>
                                <td>${book.description}</td>
                                <td>${book.quantity}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
        <!--KẾT THÚC PHẦN NỘI DUNG CỦA TRANG BOOK-->

    </div>
    <!--KẾT THÚC THÂN TRANG GỒM CÁC NÚT SERVICE VÀ NỘI DUNG CHÍNH CỦA TRANG-->

</div>
</body>
<script src="/asset/bootstrap.bundle.min.js"></script>
<script src="/asset/book.js"></script>
<script src="/asset/student.js"></script>
<script src="/asset/cards.js"></script>
</html>
