const url = 'http://localhost:8080/api/users/'
const userText = document.querySelector('#oneUser tbody')

const showOneUser = (current_user) => {
    userText.innerHTML = `
             <tr id=${'tr' + current_user.id}>
                <td>${current_user.id}</td>
                <td id=${'name' + current_user.id}>${current_user.name}</td>
                <td id=${'lastname' + current_user.id}>${current_user.lastname}</td>
                <td id=${'age' + current_user.id}>${current_user.age}</td>
                <td id=${'username' + current_user.id}>${current_user.username}</td>
                <td>
                    <div id=${'roles' + current_user.id}>
                        ${current_user.roles.map(role => role.name).join(" ")}
                    </div>
                </td>
            </tr>`;
};

fetch(url + 'current_user')
    .then(res => res.json())
    .then(data => showOneUser(data))
    .catch(error => console.log(error))