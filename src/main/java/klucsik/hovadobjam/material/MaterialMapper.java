package klucsik.hovadobjam.material;

import klucsik.hovadobjam.config.BaseMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = BaseMapperConfig.class)
public interface MaterialMapper {
    MaterialMapper INSTANCE = Mappers.getMapper(MaterialMapper.class);

    @Mapping(source = "trashes", target="trashesDto")
    MaterialDto materialToDto(Material material);

    Material dtoToMaterial(MaterialDto materialDto);

}
