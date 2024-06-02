package cn.edu.ncu.talkpulse.group.entity;

public class Corre {
   private Integer correuser_id;
   private String correuser_name;
   private Integer corregroup_id;
   private Integer newinform;

    public Corre() {
    }

    public Corre(Integer correuser_id, Integer corregroup_id, Integer newinform) {
        this.correuser_id = correuser_id;
        this.corregroup_id = corregroup_id;
        this.newinform = newinform;
    }

    public Corre(Integer correuser_id, Integer corregroup_id){
        this.correuser_id=correuser_id;
        this.corregroup_id=corregroup_id;
    }

    public String getCorreuser_name() {
        return correuser_name;
    }

    public void setCorreuser_name(String correuser_name) {
        this.correuser_name = correuser_name;
    }

    public Integer getCorreuser_id() {
        return correuser_id;
    }

    public void setCorreuser_id(Integer correuser_id) {
        this.correuser_id = correuser_id;
    }

    public Integer getCorregroup_id() {
        return corregroup_id;
    }

    public void setCorregroup_id(Integer corregroup_id) {
        this.corregroup_id = corregroup_id;
    }

    public Integer isNewinform() {
        return newinform;
    }

    public void setNewinform(Integer newinform) {
        this.newinform = newinform;
    }
}
