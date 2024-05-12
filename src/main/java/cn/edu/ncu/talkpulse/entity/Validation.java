package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 加好友验证
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Validation implements Serializable {
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


}
