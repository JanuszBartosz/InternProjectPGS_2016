package com.pgs.soft.dto;

public class AwardRequestToValidateDTO {

	private AwardDTO awardDTO;

	private UserProfileDTO userProfileDTO;

	public AwardRequestToValidateDTO() {

	}

	public AwardRequestToValidateDTO(AwardDTO awardDTO, UserProfileDTO userProfileDTO) {
		super();
		this.awardDTO = awardDTO;
		this.userProfileDTO = userProfileDTO;
	}

	public AwardDTO getAwardDTO() {
		return awardDTO;
	}

	public void setAwardDTO(AwardDTO awardDTO) {
		this.awardDTO = awardDTO;
	}

	public UserProfileDTO getUserProfileDTO() {
		return userProfileDTO;
	}

	public void setUserProfileDTO(UserProfileDTO userProfileDTO) {
		this.userProfileDTO = userProfileDTO;
	}

}
