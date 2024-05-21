package cn.edu.ncu.talkpulse.dto;

import cn.edu.ncu.talkpulse.friends.entity.Validation;

import java.time.LocalDateTime;

public class ValidationSenderDTO extends Validation {
    // 发送者头像
    private String validation_sender_photo;

    // 发送者昵称
    private String validation_sender_name;

    public ValidationSenderDTO(Integer validation_id, Integer validation_senderid, Integer validation_receiverid, Integer validation_status, Integer validation_readstatus, LocalDateTime validation_time, String validation_sender_photo, String validation_sender_name) {
        super(validation_id, validation_senderid, validation_receiverid, validation_status, validation_readstatus, validation_time);
        this.validation_sender_photo = validation_sender_photo;
        this.validation_sender_name = validation_sender_name;
    }

    public ValidationSenderDTO(String validation_sender_photo, String validation_sender_name) {
        this.validation_sender_photo = validation_sender_photo;
        this.validation_sender_name = validation_sender_name;
    }

    public ValidationSenderDTO(Integer validationId, Integer validationSenderid, Integer validationReceiverid, Integer validationStatus, Integer validationReadstatus, String validation_sender_photo, String validation_sender_name) {
        super(validationId, validationSenderid, validationReceiverid, validationStatus, validationReadstatus);
        this.validation_sender_photo = validation_sender_photo;
        this.validation_sender_name = validation_sender_name;
    }

    public ValidationSenderDTO(Validation validation,String validation_sender_photo, String validation_sender_name){
        // 使用super关键字调用父类构造器初始化继承自父类的属性
        super(validation.getValidation_id(),
                validation.getValidation_senderid(),
                validation.getValidation_receiverid(),
                validation.getValidation_status(),
                validation.getValidation_readstatus(),
                validation.getValidation_time());

        // 设置当前类特有的属性
        this.validation_sender_photo = validation_sender_photo;
        this.validation_sender_name = validation_sender_name;
    }

    public String getValidation_sender_photo() {
        return validation_sender_photo;
    }

    public void setValidation_sender_photo(String validation_sender_photo) {
        this.validation_sender_photo = validation_sender_photo;
    }

    public String getValidation_sender_name() {
        return validation_sender_name;
    }

    public void setValidation_sender_name(String validation_sender_name) {
        this.validation_sender_name = validation_sender_name;
    }
}
