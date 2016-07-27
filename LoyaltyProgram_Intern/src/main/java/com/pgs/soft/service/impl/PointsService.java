package com.pgs.soft.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.pgs.soft.domain.Points;
import com.pgs.soft.domain.UserProfile;
import com.pgs.soft.dto.NewPointsRequestDTO;
import com.pgs.soft.repository.PointsRepository;
import com.pgs.soft.repository.UserProfileRepository;

@Service
public class PointsService extends GenericServiceImpl<Points, NewPointsRequestDTO, Long> {

	@Autowired
	PointsRepository pointsRepository;

	@Autowired
	UserProfileRepository userProfileRepository;

	@Override
	protected CrudRepository<Points, Long> getCrudRepository() {

		return pointsRepository;
	}

	@Override
	protected Points mapDtoToEntity(NewPointsRequestDTO newPointsRequestDTO) {

		Points points = new Points();
		points.setCardNumber(newPointsRequestDTO.getCardNumber());
		points.setDate(newPointsRequestDTO.getDate());
		points.setPointsAmount(newPointsRequestDTO.getPoints());
		// points.setUserProfile(userProfileRepository.findOneByUser_Cards_Number(newPointsRequestDTO.getCardNumber()));
		return points;
	}

	@Override
	protected NewPointsRequestDTO mapEntityToDto(Points points) {
		NewPointsRequestDTO newPointsRequestDTO = new NewPointsRequestDTO();
		newPointsRequestDTO.setCardNumber(points.getCardNumber());
		newPointsRequestDTO.setDate(points.getDate());
		newPointsRequestDTO.setPoints(points.getPointsAmount());
		return newPointsRequestDTO;
	}

	public void updateUserPoints(NewPointsRequestDTO newPointsRequestDTO) {

		UserProfile userProfile = userProfileRepository.findOneByUser_Cards_Number(newPointsRequestDTO.getCardNumber());
		userProfile.setPointsSum(userProfile.getPointsSum() + newPointsRequestDTO.getPoints());
		Points points = mapDtoToEntity(newPointsRequestDTO);
		points.setUserProfile(userProfile);
		userProfileRepository.save(userProfile);
		pointsRepository.save(points);
	}

}
