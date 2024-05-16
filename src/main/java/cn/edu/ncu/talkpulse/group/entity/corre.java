package cn.edu.ncu.talkpulse.group.entity;

public class corre {
   private Integer correuser_id;
   private Integer corregroup_id;

    private boolean newinform;

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
