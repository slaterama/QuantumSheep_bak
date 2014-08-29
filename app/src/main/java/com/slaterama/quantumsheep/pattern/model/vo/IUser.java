package com.slaterama.quantumsheep.pattern.model.vo;

import java.util.Date;

public interface IUser {

	public void setFirstName(String firstName);

	public void setLastName(String lastName);

	public void setFullName(String fullName);

	public void setUsername(String username);

	public void setActive(boolean active);

	public void setCreatedAt(Date createdAt);

	public void setUpdatedAt(Date updatedAt);

}
