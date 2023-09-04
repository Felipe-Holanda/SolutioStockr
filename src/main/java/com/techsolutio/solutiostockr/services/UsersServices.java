package com.techsolutio.solutiostockr.services;

import java.util.List;
import java.util.UUID;

import com.techsolutio.solutiostockr.models.dto.UsersDto;
import com.techsolutio.solutiostockr.models.entity.Users;

public interface UsersServices {

    public Users createUser(UsersDto userData);

    public List<Users> listUsers();

    public Users retrieveUser(UUID id);

    public Users updateUser(UUID id, UsersDto userData);

    public void deleteUser(UUID id);

    public Users togglePrivileges(UUID id);

}
