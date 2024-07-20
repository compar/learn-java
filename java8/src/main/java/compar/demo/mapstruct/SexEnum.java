package compar.demo.mapstruct;

public enum SexEnum {
    man(1, "男"),
    woman(0, "女");

    private Integer code;

    private String name;

    SexEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static SexEnum of(Integer code){
        for(SexEnum sexEnum:SexEnum.values()){
            if(sexEnum.code.equals(code)){
                return sexEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
