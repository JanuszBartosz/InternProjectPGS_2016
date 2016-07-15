package com.pgs.soft.dto;

public class ChangePasswordRequestDTO {
	
	private String oldPassword;
	private String newPassword;
	private String newPasswordRepeat;
	
	public ChangePasswordRequestDTO(){
		
	}
	
	public ChangePasswordRequestDTO(String oldPassword, String newPassword, String newPasswordRepeat) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
		this.newPasswordRepeat = newPasswordRepeat;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}
	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}
	
	
	
	

}
