package apap.tutorial.gopud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.tutorial.gopud.model.UserModel;
import apap.tutorial.gopud.repository.UserDb;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDb userDb;
	
	@Override
	public UserModel addUser(UserModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}
	
	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	@Override
	public UserModel getUserByUsername(String username) {
		return userDb.findByUsername(username);
	}
	
	@Override
	public void updatePassword(UserModel user, String newPass) {
		newPass = this.encrypt(newPass);
		user.setPassword(newPass);
		userDb.save(user);
	}
	
	@Override
	public boolean checkPassword(UserModel user, String inputPass) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(inputPass, user.getPassword());
	}
	
	@Override
	public boolean validPass(String pass) {
		if(pass.length()<10||!pass.matches(".*\\d+.*")||!pass.matches(".*[a-zA-Z]+.*")) {
			return false;
		}
		return true;
	}
	
}
