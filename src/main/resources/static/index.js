'use strict';

const output = document.getElementById("output");

const select = document.getElementById("teamId");

 

const getTeam = async () => {
    const res = await axios.get("/team/getTeam");
    output.innerHTML = "";
    res.data.forEach(team => {
        console.log(team);
        renderPlayer(team);
    });
}

 

// const renderTeam = ({teamId, teamName}) => {
//     const option = document.createElement("option");
//     column.setProperty("teamId", teamId);
//     option.innerText = `${teamName}`;
//     select.appendChild(option);
// }

const getPlayer = async () => {
    const res = await axios.get("/player/getAll");
    output.innerHTML = "";
    res.data.forEach(player => {
        console.log(player);
        renderPlayer(player);
    });
}

const renderPlayer = ({ playerId, playerName, age }) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    //trying something

    // const option = document.createElement("option");
    // column.setProperty("teamId", teamId);
    // option.innerText = `${teamName}`;
    // select.appendChild(option);


    

    // const teamIdText = document.createElement("p");
    // teamIdText.className = "card-text";
    // teamIdText.innerText = `teamId: ${teamId}`;
    // cardBody.appendChild(teamIdText);

    // const teamNameText = document.createElement("p");
    // teamNameText.className = "card-text";
    // teamNameText.innerText = `teamName: ${teamName}`;
    // cardBody.appendChild(teamNameText);

    const PlayerIdText = document.createElement("p");
    PlayerIdText.className = "card-text";
    PlayerIdText.innerText = `PlayerId: ${playerId}`;
    cardBody.appendChild(PlayerIdText);

    const playerText = document.createElement("p");
    playerText.className = "card-text";
    playerText.innerText = `playerName: ${playerName}`;
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
        deletePlayer(playerId);
    })
        
    const updateButton = document.createElement("a");
    updateButton.innerText = "Update";
    updateButton.className = "card-link";
    updateButton.addEventListener("click", function () {
        document.getElementById("updateForm").style.display = "inline";
        
        document.getElementById("updateForm").addEventListener("submit", function (event) {
            event.preventDefault();
            const data = {
                playerId: playerId,
                playerName: this.playerName.value,
                age: this.age.value,
                team: {           
                    teamId: teamId,
                    teamName:this.teamName.value        
                }
        
            }
        
            axios.put(`/player/update/${playerId}`, data)
                .then(res => {
                    getPlayer();
                    this.reset();
                   
                }).catch(err => console.log(err));
                console.log(this);
        });
       // updatePlayer(playerId);
    });

    cardFooter.appendChild(deleteButton);
    cardFooter.appendChild(updateButton);
    output.appendChild(column);
}

getPlayer();



document.getElementById("createForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        
        playerName: this.playerName.value,
        age: this.age.value,
        team: {           
            teamId: 1,
            teamName:"arsenal"           
        }

    }

    axios.post("/player/create", data)
        .then(res => {
            getPlayer();
            this.reset();
            //this.PlayerId.focus();
        }).catch(err => console.log(err));

    console.log(this);
});


// const updatePlayer = async (id) => {
//    // const res = await axios.put("/player/update/${id}");

//     const data = {
//         playerId: id,
//         playerName: "test",
//         age: 67,
//         team: {           
//             teamId: 1,
//             teamName:"arsenal"           
//         }

//     }

//     axios.put(`/player/update/${id}`, data)
//         .then(res => {
//             getPlayer();
//             this.reset();
//            // this.PlayerId.focus();
//         }).catch(err => console.log(err));
//         console.log(this);
// };



const deletePlayer = async (id) => {
    const res = await axios.delete(`/player/delete/${id}`);
    getPlayer();
};