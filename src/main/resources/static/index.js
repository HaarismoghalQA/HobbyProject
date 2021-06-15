'use strict';

const output = document.getElementById("output");

const getPlayer = async () => {
    const res = await axios.get("/player/getAll");
    output.innerHTML = "";
    res.data.forEach(player => renderPlayer(player));
}

const renderPlayer = ({ id, playerName, age }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const playerText = document.createElement("p");
    playerText.className = "card-text";
    playerText.innerText = `PlayerName: ${playerName}`;
    cardBody.appendChild(playerText);

    const AgeText = document.createElement("p");
    AgeText.className = "card-text";
    AgeText.innerText = `Age: ${age}`;
    cardBody.appendChild(AgeText);

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "card-link";
    deleteButton.addEventListener("click", function () {
        deletePlayer(id);
    })
        
    // const updateButton = document.createElement("a");
    // updateButton.innerText = "Update";
    // updateButton.className = "card-link";
    // updateButton.addEventListener("click", function () {
    //     updatePlayer(id);
    // });
    
    cardFooter.appendChild(deleteButton);

    output.appendChild(column);
}

getPlayer();

document.getElementById("createForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        playerName: this.playerName.value,
        age: this.age.value

    }

    axios.post("/player/create", data)
        .then(res => {
            getPlayer();
            this.reset();
            //this.Name.focus();
        }).catch(err => console.log(err));

    console.log(this);
});

// document.getElementById("updateForm").addEventListener("update", function (event) {
//     event.preventDefault();
//     const data = {
//         id: this.id.value,
//         player: this.player.value,
//         age: this.age.value,

//     }

//     axios.put("/player/update", data)
//         .then(res => {
//             getPlayer();
//             this.reset();
//             this.make.focus();
//         }).catch(err => console.log(err));

//     console.log(this);
// });

const deletePlayer = async (id) => {
    const res = await axios.delete(`/player/delete/${id}`);
    getPlayer();
};