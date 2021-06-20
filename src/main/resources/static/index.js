'use strict';


const output = document.getElementById("output");

const select = document.getElementById("teamId");

const elem1=  document.getElementById("updateTeamId");
const elem2=  document.getElementById("teamName");
const elem3=  document.getElementById("playerId");

const getTeam = async () => {
	const res = await axios.get("/team/getTeam");
    output.innerHTML = "";
    select.innerHTML = "";
    elem1.innerHTML = "";
    elem2.innerHTML = "";
    elem3.innerHTML = "";
    
	res.data.forEach(team => {
        renderTeam(team);
        team.players.forEach(player => {          
            renderPlayer(player, team);
        })
       
	});
}

const renderTeam = ({ teamId, teamName }) => {
    
	const option = document.createElement("option");
	option.id= teamId;
	option.innerText = `${teamName}`;
    select.appendChild(option);
    
  
}

const renderPlayer = ({ playerId, playerName, age }, team) => {
    
	const column = document.createElement("div");
	column.className = "col";

	const card = document.createElement("div");
	card.className = "card";
	column.appendChild(card);

	const cardBody = document.createElement("div");
	cardBody.className = "card-body";
	card.appendChild(cardBody);

	//Adding TeamID

	const teamIdText = document.createElement("p");
	teamIdText.className = "card-text";
	teamIdText.innerText = `Team Id: ${team.teamId}`;
	cardBody.appendChild(teamIdText);

	const teamNameText = document.createElement("p");
	teamNameText.className = "card-text";
	teamNameText.innerText = `Team Name: ${team.teamName}`;
	cardBody.appendChild(teamNameText);



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
	deleteButton.addEventListener("click", function() {
		deletePlayer(playerId);
	})

	const updateButton = document.createElement("a");
	updateButton.innerText = "Update";
	updateButton.className = "card-link";
    updateButton.addEventListener("click", function () {        
        document.getElementById("updateForm").style.display = "inline";
        document.getElementById("updateTeamId").innerHTML = `Team Id :  ${team.teamId}`;
        document.getElementById("teamName").innerHTML= `Team Name: ${team.teamName}`;
        document.getElementById("playerId").innerHTML = `Player ID: ${playerId}`;
        const updateForm = document.getElementById("updateForm");
		updateForm.addEventListener("submit", function(event) {
            event.preventDefault();
            console.log(`playerID : ${playerId}`);
            const data = {
                
				playerId: parseInt(document.getElementById("playerId").textContent),
				playerName: this.playerName.value,
				age: this.age.value,
				team: {
                    teamId: parseInt(document.getElementById("updateTeamId").textContent),
					teamName: document.getElementById("teamName").textContent
				}

			}
            
			axios.put(`/player/update/${playerId}`, data)
				.then(res => {
					getTeam();
                    this.reset();     
                   location.reload();
                }).catch(err => console.log(err));
                
                
        });
		
	});

	cardFooter.appendChild(deleteButton);
	cardFooter.appendChild(updateButton);
    output.appendChild(column);
    
}


getTeam();


document.getElementById("createForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
	const data = {
        
		playerName: this.playerName.value,
        age: this.age.value,
        team: {
            teamId: this.teams.options[this.teams.selectedIndex].id,
		    teamName: this.teams.options[this.teams.selectedIndex].text
        }
	}

    console.log(data);
	axios.post("/player/create", data)
		.then(res => {
			getTeam();
			this.reset();
			//this.PlayerId.focus();
		}).catch(err => console.log(err));
        


	//console.log(this);
});

const deletePlayer = async (id) => {
	const res = await axios.delete(`/player/delete/${id}`);
	getTeam();
};