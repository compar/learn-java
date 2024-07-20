package compar.demo.mapstruct;

import java.time.Instant;
import java.time.LocalDate;

public class DtoUser {
    private String id;
    private String name;
      // 性别
    private SexEnum sex;
    private LocalDate csrq;
    private Instant createTime;

    public LocalDate getCsrq() {
        return csrq;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setCsrq(LocalDate csrq) {
        this.csrq = csrq;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }    
    public SexEnum getSex() {
        return sex;
    }
    public void setSex(SexEnum sex) {
        this.sex = sex;
    }
    public Instant getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }
}
