// 미리 작성된 영역 - 수정하지 않으셔도 됩니다.
// 사용자가 내용을 올바르게 입력하였는지 확인합니다.
function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents.trim().length > 140) {
        alert('공백 포함 140자 이하로 입력해주세요');
        return false;
    }
    return true;
}

// 수정 버튼을 눌렀을 때, 기존 작성 내용을 textarea 에 전달합니다.
// 숨길 버튼을 숨기고, 나타낼 버튼을 나타냅니다.
function editPost(id) {
    showEdits(id);
    let contents = $(`#${id}-contents`).text().trim();
    $(`#${id}-textarea`).val(contents);
}

function showEdits(id) {
    $(`#${id}-editarea`).show();
    $(`#${id}-submit`).show();
    $(`#${id}-delete`).show();

    $(`#${id}-contents`).hide();
    $(`#${id}-edit`).hide();
}

function hideEdits(id) {
    $(`#${id}-editarea`).hide();
    $(`#${id}-submit`).hide();
    $(`#${id}-delete`).hide();

    $(`#${id}-contents`).show();
    $(`#${id}-edit`).show();
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// 여기서부터 코드를 작성해주시면 됩니다.

$(document).ready(function () {
    // HTML 문서를 로드할 때마다 실행합니다.
    getMessages();
})

// 메모를 불러와서 보여줍니다.
function getMessages() {
    // 1. 기존 메모 내용을 지웁니다.
    // $('#cards-box').empty();
    // 2. 메모 목록을 불러와서 HTML로 붙입니다.
    $.ajax({
        type: 'GET',
        url: '/api/cheers',
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let cheer = response[i];
                let id = cheer.id;
                let username = cheer.username;
                let title = cheer.title;
                let contents = cheer.contents;
                let modifiedAt = cheer.modifiedAt;
                addHTML(id, username, title, contents, modifiedAt);
            }
        }
    })
}

// 메모를 수정합니다.
function submitEdit(id) {
    // 1. 작성 대상 메모의 username과 contents 를 확인합니다.
    let username = $(`#${id}-username`).text().trim();
    let title = $(`#${id}-title`).text().trim();
    let contents = $(`#${id}-textarea`).val().trim();
    // 2. 작성한 메모가 올바른지 isValidContents 함수를 통해 확인합니다.
    if (isValidContents(contents) == false) {
        return;
    }
    // 3. 전달할 data JSON으로 만듭니다.
    let data = {'username': username, 'title': title, 'contents': contents};
    // 4. PUT /api/cheers/{id} 에 data를 전달합니다.
    $.ajax({
        type: "PUT",
        url: `/api/cheers/${id}`,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            alert('변경이 완료되었습니다.');
            window.location.reload();
        }
    });
}

// 메모를 삭제합니다.
function deleteOne(id) {
    // 1. DELETE /api/cheers/{id} 에 요청해서 메모를 삭제합니다.
    $.ajax({
        type: "DELETE",
        url: `/api/cheers/${id}`,
        success: function () {
            alert('삭제가 완료되었습니다.');
            window.location.reload();
        }
    })
}

function moveToDetail(id) {
    window.location.href = "detail.html?id=" + id;
}

// 메모 하나를 HTML로 만들어서 body 태그 내 원하는 곳에 붙입니다.
function addHTML(id, username, title, contents, modifiedAt) {
    // 1. HTML 태그를 만듭니다.
    let tempHtml = `<div class="card">
                                <!-- date/username 영역 -->
                      <div class="metadata">
                      <div class="date">
                      ${modifiedAt}
                      </div>
                      <div id="${id}-username" class="username">
                      ${username}
                      </div>
                      </div>
                              <!-- contents 조회/수정 영역-->
                      <div class="contents" onclick=moveToDetail(${id})>
                      <div id="${id}-title" class="text">${title}</div>
                      <div id="${id}-contents" class="text">
                      ${contents}
                      </div>
                      <div id="${id}-editarea" class="edit">
                      <textarea id="${id}-textarea" class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                      </div>
                      </div>
                              <!-- 버튼 영역-->
                <!--  <div class="footer">
                      <img id="${id}-edit" class="icon-start-edit" src="image/edit.png" alt="" onclick="editPost('${id}')">
                      <img id="${id}-delete" class="icon-delete" src="image/delete.png" alt="" onclick="deleteOne('${id}')">
                      <img id="${id}-submit" class="icon-end-edit" src="image/done.png" alt="" onclick="submitEdit('${id}')">
                      </div>     -->
                      </div>`;
    // 2. #cards-box 에 HTML을 붙인다.
    $('#cards-box').append(tempHtml);
}