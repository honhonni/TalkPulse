package cn.edu.ncu.talkpulse.friends.entity;

import java.time.LocalDateTime;

// 加好友申请表
public class Validation {
    // id
    private Integer validation_id;

    // 发送者id
    private Integer validation_senderid;

    // 接受者id
    private Integer validation_receiverid;

    // 验证状态
    private Integer validation_status;

    // 是否已读
    private Integer validation_readstatus;

    // 申请时间
    private LocalDateTime validation_time;

    // 发送者头像
    private String validation_sender_photo;

    // 接收者头像
    private String validation_receiver_photo;

    // 发送者头像
    private String validation_sender_name;

    // 接收者头像
    private String validation_receiver_name;


    public LocalDateTime getValidation_time() {
        return validation_time;
    }

    public void setValidation_time(LocalDateTime validation_time) {
        this.validation_time = validation_time;
    }

    public Validation(Integer validation_id, Integer validation_senderid, Integer validation_receiverid, Integer validation_status, Integer validation_readstatus, LocalDateTime validation_time) {
        this.validation_id = validation_id;
        this.validation_senderid = validation_senderid;
        this.validation_receiverid = validation_receiverid;
        this.validation_status = validation_status;
        this.validation_readstatus = validation_readstatus;
        this.validation_time = validation_time;
    }

    public Validation() {
    }

    public Validation(Integer validationId, Integer validationSenderid, Integer validationReceiverid, Integer validationStatus, Integer validationReadstatus) {
        this.validation_id = validationId;
        this.validation_senderid = validationSenderid;
        this.validation_receiverid = validationReceiverid;
        this.validation_status = validationStatus;
        this.validation_readstatus = validationReadstatus;
    }

    @Override
    public String toString() {
        return "Validation{" +
                "validation_id=" + validation_id +
                ", validation_senderid=" + validation_senderid +
                ", validation_receiverid=" + validation_receiverid +
                ", validation_status=" + validation_status +
                ", validation_readstatus=" + validation_readstatus +
                ", validation_time=" + validation_time +
                '}';
    }

    public Integer getValidation_id() {
        return validation_id;
    }

    public void setValidation_id(Integer validation_id) {
        this.validation_id = validation_id;
    }

    public Integer getValidation_senderid() {
        return validation_senderid;
    }

    public void setValidation_senderid(Integer validation_senderid) {
        this.validation_senderid = validation_senderid;
    }

    public Integer getValidation_receiverid() {
        return validation_receiverid;
    }

    public void setValidation_receiverid(Integer validation_receiverid) {
        this.validation_receiverid = validation_receiverid;
    }

    public Integer getValidation_status() {
        return validation_status;
    }

    public void setValidation_status(Integer validation_status) {
        this.validation_status = validation_status;
    }

    public Integer getValidation_readstatus() {
        return validation_readstatus;
    }

    public void setValidation_readstatus(Integer validation_readstatus) {
        this.validation_readstatus = validation_readstatus;
    }
}
