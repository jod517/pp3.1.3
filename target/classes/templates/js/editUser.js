
$(document).ready(function () {
    $('#editUserButton').click(function (event) {
        editUser($('#editId').val());
        event.preventDefault();
    })
})

function editUser(id) {
    fetch('/admin/rest/' + id, {
        method: 'PUT',
        body: JSON.stringify({
            id: $('#editId').val(),
            username: $('#editName').val(),
            password: $('#editPassword').val(),
            roles: [
                $('#editRole').val()
            ]
        }),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => response.status === 200 ? response.json() : console.log(response.status))
        .then(user => renderChangedUserInUsersTable(user));
}

function renderChangedUserInUsersTable(user) {
    let tr = document.querySelector(`tr[data-rowId="${user.id}"]`);
    tr.insertAdjacentHTML("beforebegin", row(user));
    tr.remove();

    $('#editModal').modal('hide');
    document.forms["editUserForm"].reset();
}