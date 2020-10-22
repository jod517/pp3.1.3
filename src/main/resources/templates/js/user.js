function getUser(id) {
    fetch('/admin/rest/' + id)
        .then(response => response.status === 200 ? response.json() : console.log(response.status))
        .then(user => renderModal(user));
}

function renderModal(user) {
    let editForm = document.forms['editUserForm'];
    editForm.elements['editId'].value = user.id;
    editForm.elements['editName'].value = user.username;
    editForm.elements['editPassword'].value = user.password;
    editForm.elements['editRole'].value = user.roles;

    let deleteForm = document.forms['deleteUserForm'];
    deleteForm.elements['deleteId'].value = user.id;
    deleteForm.elements['deleteName'].value = user.username;
    deleteForm.elements['deletePassword'].value = user.password;
    deleteForm.elements['deleteRole'].value = user.roles;
}
