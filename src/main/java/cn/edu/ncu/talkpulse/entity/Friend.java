package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * 好友关系表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friend implements Serializable {
	// 一号用户id
	private Integer firstuserId;

	// 二号用户id
	private Integer seconduserId;

	// 1给2设置的分组id
	private Integer givenfriendshipId;


}
