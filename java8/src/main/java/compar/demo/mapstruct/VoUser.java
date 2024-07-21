package compar.demo.mapstruct;



public class VoUser {
    private Long id;
    private String name;
    private Integer sex;
    private String csrqStr;
    private String createTime;
    public String getCsrqStr() {
        return csrqStr;
    }
    public void setCsrqStr(String csrqStr) {
        this.csrqStr = csrqStr;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getCreateTime() {
        return createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
