package com.budget.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserRoles {

	@EmbeddedId
	private UserRolesPK userRolePK;

	public UserRolesPK getUserRolePK() {
		return userRolePK;
	}

	public void setUserRolePK(UserRolesPK userRolePK) {
		this.userRolePK = userRolePK;
	}

}
