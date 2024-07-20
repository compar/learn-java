package compar.demo.mapstruct;

import org.mapstruct.Named;


@Named("SexEnumIntegerMapper")
public class SexEnumIntegerMapper {
        @Named("sexEnumByInteger")
    public SexEnum sexEnumByInteger(Integer intParam){
        return SexEnum.of(intParam);
    }

    @Named("integerBySexEnum")
    public Integer integerBySexEnum(SexEnum sexEnum){
        return sexEnum.getCode();
    }
}
