package apap.tutorial.gopud.service;

import apap.tutorial.gopud.model.UserModel;

public interface UserService {
	UserModel addUser(UserModel user);
	public String encrypt(String password);
	UserModel getUserByUsername(String username);
	void updatePassword(UserModel user, String newPassword);
	boolean checkPassword(UserModel user, String inputPass);
	boolean validPass(String pass);
}
