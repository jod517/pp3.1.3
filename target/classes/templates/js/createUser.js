$(document).ready(function () {
    $('#createUserButton').click(function (event) {
        createUser();
        event.preventDefault();
    })
})

function createUser() {
    fetch('/admin/rest', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: $('#inputName').val(),
            password: $('#inputPassword').val(),
            roles: [
                $('#inputRole').val()
            ]
        })
    }).then((response) => response.status === 201 ? response.json() : console.log(response.status))
        .then(user => renderUserInUsersTable(user));
}

function renderUserInUsersTable(user) {
    let tbody = document.querySelector("tbody");
    tbody.insertAdjacentHTML("beforeend", row(user));

    document.forms["addUserForm"].reset();
}