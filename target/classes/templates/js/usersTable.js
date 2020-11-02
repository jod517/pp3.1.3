getUsersTable();

function getUsersTable() {
    fetch('/admin/rest')
        .then(response => response.status === 200 ? response.json() : console.log(response.status))
        .then(users => renderUsersTable(users));
}

function renderUsersTable(users) {
    let rows = "";
    for (let user of users) {
        rows += row(user);
    }

    let tbody = document.querySelector("tbody");
    tbody.insertAdjacentHTML("afterbegin", rows);
}

function row(user) {
    let roles = "";

    for (let role of user.roles) {
        roles += role + " ";
    }
    return `<tr data-rowId="${user.id}"> 
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${roles}</td>
                <td><button type="button" class="btn btn-info" data-toggle="modal" data-target="#editModal" onclick="getUser(${user.id})">Edit</button></td>
                <td><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteModal" onclick="getUser(${user.id})">Delete</button></td>
            </tr>`
}