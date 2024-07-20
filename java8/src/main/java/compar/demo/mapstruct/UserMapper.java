package compar.demo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {
    SexEnumIntegerMapper.class,
    InstantStringMapper.class
})
public interface UserMapper {
    UserMapper INSTANST = Mappers.getMapper(UserMapper.class);
    
 @Mappings({
            @Mapping(source = "sex", target = "sex",qualifiedByName = {"SexEnumIntegerMapper", "sexEnumByInteger"}),
            @Mapping(source = "createTime", target = "createTime",qualifiedByName = {"InstantStringMapper", "instantByString"})

    })
    DtoUser voTodto(VoUser u);

    @Mappings({
        @Mapping(source = "sex", target = "sex",qualifiedByName = {"SexEnumIntegerMapper", "integerBySexEnum"}),
        @Mapping(source = "createTime", target = "createTime",qualifiedByName = {"InstantStringMapper", "stringByInstant"})

    })
    VoUser toVo(DtoUser u);

    List<DtoUser> voTodto(List<VoUser>  user);
}
