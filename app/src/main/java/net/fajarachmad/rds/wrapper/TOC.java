package net.fajarachmad.rds.wrapper;

/**
 * Created by Mahsyaa on 7/5/2016.
 */
public class TOC {

    private String code;
    private String name;
    private String description;

    public TOC(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
