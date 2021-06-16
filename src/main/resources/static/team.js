'use strict';

const output = document.getElementById("output");

const getTeam = async () => {
    const res = await axios.get("/team/getTeam");
    output.innerHTML = "";
    res.data.forEach(team => {
        console.log(team);
        renderTeam(team);
    });
}

const renderTeam = ({ teamId, teamName}) => {
    const column = document.createElement("div");
    column.className = "col";

    const card = document.createElement("div");
    card.className = "card";
    column.appendChild(card);

    const cardBody = document.createElement("div");
    cardBody.className = "card-body";
    card.appendChild(cardBody);

    const teamIdText = document.createElement("p");
    teamIdText.className = "card-text";
    teamIdText.innerText = `Team Id: ${teamId}`;
    cardBody.appendChild(teamIdText);

    const teamNameText = document.createElement("p");
    teamNameText.className = "card-text";
    teamNameText.innerText = `Team Name: ${teamName}`;
    cardBody.appendChild(teamNameText);

    

    const cardFooter = document.createElement("div");
    cardFooter.className = "card-footer";
    card.appendChild(cardFooter);

    const deleteButton = document.createElement("a");
    deleteButton.innerText = "Delete";
    deleteButton.className = "card-link";
    deleteButton.addEventListener("click", function () {
        deletePlayer(teamId);
    })
        
    const updateButton = document.createElement("a");
    updateButton.innerText = "Update";
    updateButton.className = "card-link";
    updateButton.addEventListener("click", function () {
        document.getElementById("updateForm").style.display = "inline";
        
        document.getElementById("updateForm").addEventListener("submit", function (event) {
            event.preventDefault();
            const data = {                        
                    teamId: teamId,
                    teamName:this.teamName.value     
            }
        
            axios.put(`/team/update/${teamId}`, data)
                .then(res => {
                    getTeam();
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

getTeam();



document.getElementById("createForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const data = {
        
        teamName:this.teamName.value   

    }

    axios.post("/team/create", data)
        .then(res => {
            getTeam();
            this.reset();
            
        }).catch(err => console.log(err));

    console.log(this);
});






const deleteTeam = async (id) => {
    const res = await axios.delete(`/team/remove/${id}`);
    getPlayer();
};