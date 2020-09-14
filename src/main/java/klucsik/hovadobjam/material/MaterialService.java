package klucsik.hovadobjam.material;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialDto find(Long id){
        Material material = materialRepository.findById(id).orElse(null);
        return MaterialMapper.INSTANCE.materialToDto(material);
    }
}
