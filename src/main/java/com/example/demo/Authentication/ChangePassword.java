package com.example.demo.Authentication;

public class ChangePassword {
	private String username;

	public ChangePassword(String username, String password, String newPassword) {
		super();
		this.username = username;
		this.password = password;
		this.newPassword = newPassword;
	}

	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ChangePassword [username=" + username + ", password=" + password + ", newPassword=" + newPassword + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	private String password;
	private String newPassword;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
