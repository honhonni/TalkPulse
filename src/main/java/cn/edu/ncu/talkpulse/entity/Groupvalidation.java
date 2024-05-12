package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 加群验证
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Groupvalidation implements Serializable {
	// id
	private Integer groupvalidationId;

	// 邀请者id
	private Integer groupvalidationSenderid;

	// 受邀者id
	private Integer groupvalidationReceiverid;

	// 群聊id
	private Integer groupvalidationGroupid;

	// 验证状态
	private Integer groupvalidationStatus;

	// 是否已读
	private Integer groupvalidationReadstatus;


}
