package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 群聊记录表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Grouprecord implements Serializable {
	// id
	private Integer grouprecordId;

	// 群聊内容
	private String grouprecordContent;

	// 发送时间
	private LocalDateTime grouprecordTime;

	// 发送用户id
	private Integer grouprecordSenderid;

	// 发送群聊号
	private Integer grouprecordGroupid;


}
