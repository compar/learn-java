package compar.demo.mapstruct;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper( 
    uses = {
    InstantStringMapper.class
})
public interface UserMapper {
    UserMapper INSTANST = Mappers.getMapper(UserMapper.class);
    
 @Mappings({
            @Mapping(source = "sex", target = "sex",qualifiedByName = {"sexEnumByInteger"}, defaultExpression = "java(SexEnum.man)"),
            @Mapping(source = "createTime", target = "createTime",qualifiedByName = {"InstantStringMapper", "instantByString"}),
            @Mapping(source = "csrqStr" ,target="csrq", dateFormat ="yyyy-MM-dd" )

    })
    DtoUser voTodto(VoUser u);

    @Mappings({
        @Mapping(source = "sex", target = "sex",qualifiedByName = {"integerBySexEnum"}),
        @Mapping(source = "createTime", target = "createTime",qualifiedByName = {"InstantStringMapper", "stringByInstant"}),
        @Mapping(source = "csrq" ,target="csrqStr", dateFormat ="yyyy-MM-dd" )
       

    })
    VoUser toVo(DtoUser u);

    List<DtoUser> voTodto(List<VoUser>  user);

    @Named("sexEnumByInteger")
    default SexEnum sexEnumByInteger(Integer intParam){
        return SexEnum.of(intParam);
    }

    @Named("integerBySexEnum")
    default Integer integerBySexEnum(SexEnum sexEnum){
        return sexEnum.getCode();
    }
}
