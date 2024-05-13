package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * 好友分组表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Friendship implements Serializable {
	// 分组id
	private Integer friendshipId;

	// 分组名
	private String friendshipName;


}
