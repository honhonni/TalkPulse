package cn.edu.ncu.talkpulse.group.entity;

import java.time.LocalDateTime;

public class groupapply {
    private int groupapply_id;
    private int groupapply_senderid;
    private LocalDateTime groupapply_time;
    private int groupapply_groupid;
    private int groupapply_hostid;
    private String groupapply_introduce;
    private boolean groupapply_status;
    private boolean groupapply_readstatus;

    public int getGroupapply_id() {
        return groupapply_id;
    }

    public void setGroupapply_id(int groupapply_id) {
        this.groupapply_id = groupapply_id;
    }

    public int getGroupapply_senderid() {
        return groupapply_senderid;
    }

    public void setGroupapply_senderid(int groupapply_senderid) {
        this.groupapply_senderid = groupapply_senderid;
    }

    public LocalDateTime getGroupapply_time() {
        return groupapply_time;
    }

    public void setGroupapply_time(LocalDateTime groupapply_time) {
        this.groupapply_time = groupapply_time;
    }

    public int getGroupapply_groupid() {
        return groupapply_groupid;
    }

    public void setGroupapply_groupid(int groupapply_groupid) {
        this.groupapply_groupid = groupapply_groupid;
    }

    public int getGroupapply_hostid() {
        return groupapply_hostid;
    }

    public void setGroupapply_hostid(int groupapply_hostid) {
        this.groupapply_hostid = groupapply_hostid;
    }

    public String getGroupapply_introduce() {
        return groupapply_introduce;
    }

    public void setGroupapply_introduce(String groupapply_introduce) {
        this.groupapply_introduce = groupapply_introduce;
    }

    public boolean isGroupapply_status() {
        return groupapply_status;
    }

    public void setGroupapply_status(boolean groupapply_status) {
        this.groupapply_status = groupapply_status;
    }

    public boolean isGroupapply_readstatus() {
        return groupapply_readstatus;
    }

    public void setGroupapply_readstatus(boolean groupapply_readstatus) {
        this.groupapply_readstatus = groupapply_readstatus;
    }
}
