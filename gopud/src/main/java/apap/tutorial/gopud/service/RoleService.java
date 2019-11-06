package apap.tutorial.gopud.service;

import java.util.List;

import apap.tutorial.gopud.model.RoleModel;

public interface RoleService {
	void addRole(RoleModel role);
	List<RoleModel> findAll();
}
