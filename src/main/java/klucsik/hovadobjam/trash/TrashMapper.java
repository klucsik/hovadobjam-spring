package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrashMapper {
    TrashMapper INSTANCE = Mappers.getMapper(TrashMapper.class);

    TrashDto trashToDto(Trash trash);

    Trash dtoToTrash(TrashDto trashDto);
}
