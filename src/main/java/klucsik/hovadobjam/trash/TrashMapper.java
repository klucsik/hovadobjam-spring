package klucsik.hovadobjam.trash;

import klucsik.hovadobjam.config.BaseMapperConfig;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface TrashMapper {
    TrashMapper INSTANCE = Mappers.getMapper(TrashMapper.class);

    @Mapping(source = "material", target = "materialDto")
    TrashDto trashToDto(Trash trash);


    @Mapping(source = "materialDto", target = "material")
    Trash dtoToTrash(TrashDto trashDto);

    @Mapping(source = "material", target = "materialDto")
    void TrashToDto(Trash trash, @MappingTarget TrashDto trashDto);

    @Mapping(source = "materialDto", target = "material")
    void dtoToTrash(TrashDto trashDto, @MappingTarget Trash trash);
}
