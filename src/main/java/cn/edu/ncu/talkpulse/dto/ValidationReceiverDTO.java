package cn.edu.ncu.talkpulse.dto;

import cn.edu.ncu.talkpulse.friends.entity.Validation;
import cn.edu.ncu.talkpulse.group.entity.GroupApplyWithGroupInfo;
import cn.edu.ncu.talkpulse.group.entity.Groupapply;

import java.time.LocalDateTime;

public class ValidationReceiverDTO extends Validation {
    // 接收者头像
    private String validation_receiver_photo;

    // 接收者昵称
    private String validation_receiver_name;

    public ValidationReceiverDTO(Integer validation_id, Integer validation_senderid, Integer validation_receiverid, Integer validation_status, Integer validation_readstatus, LocalDateTime validation_time, String validation_receiver_photo, String validation_receiver_name) {
        super(validation_id, validation_senderid, validation_receiverid, validation_status, validation_readstatus, validation_time);
        this.validation_receiver_photo = validation_receiver_photo;
        this.validation_receiver_name = validation_receiver_name;
    }

    public ValidationReceiverDTO(String validation_receiver_photo, String validation_receiver_name) {
        this.validation_receiver_photo = validation_receiver_photo;
        this.validation_receiver_name = validation_receiver_name;
    }

    public ValidationReceiverDTO(Integer validationId, Integer validationSenderid, Integer validationReceiverid, Integer validationStatus, Integer validationReadstatus, String validation_receiver_photo, String validation_receiver_name) {
        super(validationId, validationSenderid, validationReceiverid, validationStatus, validationReadstatus);
        this.validation_receiver_photo = validation_receiver_photo;
        this.validation_receiver_name = validation_receiver_name;
    }

    public ValidationReceiverDTO(Validation validation,String validation_receiver_photo, String validation_receiver_name){
        // 使用super关键字调用父类构造器初始化继承自父类的属性
        super(validation.getValidation_id(),
                validation.getValidation_senderid(),
                validation.getValidation_receiverid(),
                validation.getValidation_status(),
                validation.getValidation_readstatus(),
                validation.getValidation_time());

        // 设置当前类特有的属性
        this.validation_receiver_photo = validation_receiver_photo;
        this.validation_receiver_name = validation_receiver_name;
    }

    public ValidationReceiverDTO(Groupapply validation, String validation_receiver_photo, String validation_receiver_name) {
        super(validation.getGroupapply_groupid(),
                validation.getGroupapply_senderid(),
                validation.getGroupapply_groupid(),
                validation.getGroupapply_status(),
                validation.getGroupapply_readstatus(),
                validation.getGroupapply_time()
        );
        this.validation_receiver_photo = validation_receiver_photo;
        this.validation_receiver_name = validation_receiver_name;
    }

    public String getValidation_receiver_photo() {
        return validation_receiver_photo;
    }

    public void setValidation_receiver_photo(String validation_receiver_photo) {
        this.validation_receiver_photo = validation_receiver_photo;
    }

    public String getValidation_receiver_name() {
        return validation_receiver_name;
    }

    public void setValidation_receiver_name(String validation_receiver_name) {
        this.validation_receiver_name = validation_receiver_name;
    }
}
