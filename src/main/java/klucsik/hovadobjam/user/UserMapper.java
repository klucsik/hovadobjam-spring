package klucsik.hovadobjam.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto usertToDto(User user);

    User dtoToUser(UserDto userDto);
}
