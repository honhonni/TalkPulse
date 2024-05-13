package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

/**
 * 用户所在群
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Corre implements Serializable {
	// 用户id
	private Integer correuserId;

	// 群聊id
	private Integer corregroupId;

	// 是否有新消息
	private Integer newinform;


}
