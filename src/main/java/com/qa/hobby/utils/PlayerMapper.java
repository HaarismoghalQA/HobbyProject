package com.qa.hobby.utils;

import org.springframework.stereotype.Service;

import com.qa.hobby.domain.Player;
import com.qa.hobby.dto.PlayerDTO;

@Service
public class PlayerMapper implements Mapper<Player, PlayerDTO> {

	@Override
	public PlayerDTO mapToDTO(Player player) {
		PlayerDTO dto = new PlayerDTO();

		dto.setPlayerName(player.getPlayerName());
		dto.setPlayerId(player.getPlayerId());
		dto.setAge(player.getAge());

		return dto;
	}

	@Override
	public Player mapFromDTO(PlayerDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}
}