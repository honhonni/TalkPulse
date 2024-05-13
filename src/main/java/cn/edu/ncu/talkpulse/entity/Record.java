package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 私聊记录表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Record implements Serializable {
	// id
	private Integer recordId;

	// 私聊内容
	private String recordContent;

	// 发送时间
	private LocalDateTime recordTime;

	// 发送者id
	private Integer recordSenderid;

	// 接受者id
	private Integer recordRecipientid;

	// 是否已读
	private Integer readStatus;


}
