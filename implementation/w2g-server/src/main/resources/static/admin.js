function updateFields(id, name, email, password, role) {
    // alert(id);
    document.getElementById("adminUpdate").elements["id"].value = id;
    document.getElementById("adminUpdate").elements["username"].value = name;
    document.getElementById("adminUpdate").elements["email"].value = email;
    document.getElementById("adminUpdate").elements["password"].value = password;
    document.getElementById("adminUpdate").elements["role"].value = role;
}
