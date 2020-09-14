package klucsik.hovadobjam.trash;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrashMapper {
    TrashMapper INSTANCE = Mappers.getMapper(TrashMapper.class);

    @Mapping(source = "material", target="materialDto")
    TrashDto trashToDto(Trash trash);

    Trash dtoToTrash(TrashDto trashDto);
}
