package compar.demo.mapstruct;

import java.time.LocalDate;

public class VoUser {
    private Long id;
    private String name;
    private Integer sex;
    private LocalDate csrq;
    private String createTime;

    public LocalDate getCsrq() {
        return csrq;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setCsrq(LocalDate csrq) {
        this.csrq = csrq;
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
