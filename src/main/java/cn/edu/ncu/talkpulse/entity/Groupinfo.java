package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * 群聊表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Groupinfo implements Serializable {
	// 群聊id
	private Integer groupId;

	// 群聊名称
	private String groupName;

	// 群聊简介
	private String groupIntroduce;

	// 群主id
	private Integer groupHostid;


}
