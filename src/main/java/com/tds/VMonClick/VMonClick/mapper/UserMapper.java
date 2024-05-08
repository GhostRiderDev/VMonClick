package com.tds.VMonClick.VMonClick.mapper;

import org.springframework.beans.BeanUtils;
import com.tds.VMonClick.VMonClick.dto.UserDto;
import com.tds.VMonClick.VMonClick.model.UserEntity;

public class UserMapper {
  public static UserEntity dtoToDB(UserDto userDto) {
    UserEntity userEntity = new UserEntity();
    BeanUtils.copyProperties(userDto, userEntity);
    return userEntity;
  }

  public static UserDto DBtoDto(UserEntity userEntity) {
    UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userEntity, userDto, "password");
    return userDto;
  }
}
