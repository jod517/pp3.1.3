
$(document).ready(function () {
    $('#deleteUserButton').click(function (event) {
        deleteUser($('#deleteId').val());
        event.preventDefault();
    })
})

function deleteUser(id) {
    fetch('/admin/rest/' + id, {
        method: 'DELETE'
    }).then(response => response.status === 204 ? deleteUserFromUserTable(id) : console.log(response.status));
}

function deleteUserFromUserTable(id) {
    let tr = document.querySelector(`tr[data-rowId="${id}"]`);
    tr.remove();

    $('#deleteModal').modal('hide');
    document.forms["deleteUserForm"].reset();
}