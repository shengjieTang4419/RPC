package bean;

import java.io.Serializable;

/**
 * @author ：shengjie.tang
 * @date ：Created in 2019/9/14 22:12
 * @description：
 * @modified By：
 * @version: $
 */
public class BusinessPO implements Serializable {
    private static final long serialVersionUID = 5046948069699824716L;
    private Long id;
    private String code;
    private String name;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("businessPO{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public BusinessPO(Long id, String code, String name, String status) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.status = status;
    }
}
