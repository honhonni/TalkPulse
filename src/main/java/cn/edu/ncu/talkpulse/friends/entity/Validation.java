package cn.edu.ncu.talkpulse.friends.entity;

// 加好友验证表
public class Validation {
    // id
    private Integer validationId;

    // 发送者id
    private Integer validationSenderid;

    // 接受者id
    private Integer validationReceiverid;

    // 验证状态
    private Integer validationStatus;

    // 是否已读
    private Integer validationReadstatus;

    public Validation() {
    }

    public Validation(Integer validationId, Integer validationSenderid, Integer validationReceiverid, Integer validationStatus, Integer validationReadstatus) {
        this.validationId = validationId;
        this.validationSenderid = validationSenderid;
        this.validationReceiverid = validationReceiverid;
        this.validationStatus = validationStatus;
        this.validationReadstatus = validationReadstatus;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "validationId=" + validationId +
                ", validationSenderid=" + validationSenderid +
                ", validationReceiverid=" + validationReceiverid +
                ", validationStatus=" + validationStatus +
                ", validationReadstatus=" + validationReadstatus +
                '}';
    }

    public Integer getValidationId() {
        return validationId;
    }

    public void setValidationId(Integer validationId) {
        this.validationId = validationId;
    }

    public Integer getValidationSenderid() {
        return validationSenderid;
    }

    public void setValidationSenderid(Integer validationSenderid) {
        this.validationSenderid = validationSenderid;
    }

    public Integer getValidationReceiverid() {
        return validationReceiverid;
    }

    public void setValidationReceiverid(Integer validationReceiverid) {
        this.validationReceiverid = validationReceiverid;
    }

    public Integer getValidationStatus() {
        return validationStatus;
    }

    public void setValidationStatus(Integer validationStatus) {
        this.validationStatus = validationStatus;
    }

    public Integer getValidationReadstatus() {
        return validationReadstatus;
    }

    public void setValidationReadstatus(Integer validationReadstatus) {
        this.validationReadstatus = validationReadstatus;
    }
}
