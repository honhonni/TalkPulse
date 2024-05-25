package cn.edu.ncu.talkpulse.group.entity;

public class corre {
   private Integer correuser_id;
   private String correuser_name;
   private Integer corregroup_id;

    private boolean newinform;

    public String getCorreuser_name() {
        return correuser_name;
    }

    public void setCorreuser_name(String correuser_name) {
        this.correuser_name = correuser_name;
    }

    public corre(Integer correuser_id, Integer corregroup_id){
        this.correuser_id=correuser_id;
        this.corregroup_id=corregroup_id;
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

    public boolean isNewinform() {
        return newinform;
    }

    public void setNewinform(boolean newinform) {
        this.newinform = newinform;
    }
}
