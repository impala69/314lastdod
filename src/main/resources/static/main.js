const linkGetAllUsers = "http://localhost:8080/api/users";
const linkGetUser = "http://localhost:8080/api/user/";
const linkDeleteUser = "http://localhost:8080/api/delete/";
const linkUpdateUser = "http://localhost:8080/api/update/user";
const linkNewUser = "http://localhost:8080/api/new/user";

let userFormNew = {
    firstName: "testing",
    lastName: "1",
    age: 1,
    email: "1",
    password: "1",
    roles: [
        {
            role: "ROLE_ADMIN"
        }
    ]
}

let userForm = {
    id: 2,
    firstName: "testing",
    lastName: "1",
    age: 1,
    email: "1",
    password: "1",
    roles: [
        {
            role: "ROLE_ADMIN"
        }
    ]
}

allUsers();

async function allUsers() {
    console.log("Executing request!");

    try {
        const response = await fetch(linkGetAllUsers);
        const users = await response.json();

        if (users.length > 0) {
            let temp = "";
            users.forEach((u) => {
                temp += "<tr><td>" + u.id + "</td>";
                temp += "<td>" + u.firstName + "</td>";
                temp += "<td>" + u.lastName + "</td>";
                temp += "<td>" + u.age + "</td>";
                temp += "<td>" + u.email + "</td>";
                temp += "<td>" + printRoles(u.roles) + "</td>";
                temp += "<td>" + `<button onclick='updateUserForm(${u.id})' type='button' class='btn btn-info'>Edit</button>` + "</td>";
                temp += `<td><button onclick="deletedUser(${u.id})" class=\"btn btn-danger delBtn\">Delete</button></td></tr>`;
            });
            document.getElementById("allUsers").innerHTML = temp;
        }
    } catch (e) {
        console.error(e);
    }
}

async function updateUserForm(id) {
    $(".editUser #exampleModal").modal();

    let user = await (await fetch(linkGetUser + id)).json();

    $('#id').val(user.id);
    $('#edit-firstname').val(user.firstName);
    $('#edit-lastname').val(user.lastName);
    $('#edit-age').val(user.age);
    $('#edit-email').val(user.email);
    $('#edit-password').val(user.password);
    $('#edit-roles').val(user.roles);

    upbtn.onclick = function () {
        userForm.id = id;
        userForm.firstName = $('#edit-firstname').val();
        userForm.lastName = $('#edit-lastname').val();
        userForm.age = $('#edit-age').val();
        userForm.email = $('#edit-email').val();
        userForm.password = $('#edit-password').val();
        userForm.roles = $('#edit-roles').val();
        updateUser();
    }
}

async function updateUser() {
    try {
        await fetch(linkUpdateUser, {
            method: "PATCH",
            body: JSON.stringify(userForm),
            headers: {
                "Content-Type": "application/json"
            }
        });
    } catch (e) {
        console.error(e);
    }
}

async function newUser() {
    try {
        await fetch(linkNewUser, {
            method: "POST",
            body: JSON.stringify(userFormNew),
            headers: {
                "Content-Type": "application/json"
            }
        });
    } catch (e) {
        console.error(e);
    }
}

function deletedUser(id) {
    fetch(linkDeleteUser + id, {
        method: "DELETE",
    }).then(() => allUsers());
}

function printRoles(roles) {
    let print = "";
    for (let i = 0; i < roles.length; i++) {
        print += roles[i].role;
        if (roles.length > 1 && i !== 1) {
            print += ", ";
        }
    }
    return print;
}

newbtn.onclick = function () {
    userFormNew.firstName = $('#new-firstname').val();
    userFormNew.lastName = $('#new-lastname').val();
    userFormNew.age = $('#new-age').val();
    userFormNew.email = $('#new-email').val();
    userFormNew.password = $('#new-password').val();
    userFormNew.roles = $('#new-roles').val();
    newUser().then(allUsers);
}