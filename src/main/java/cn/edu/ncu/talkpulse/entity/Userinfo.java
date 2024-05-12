package cn.edu.ncu.talkpulse.entity;


import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户信息表
 * @author Kkwans
 * @date 2024-05-12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Userinfo implements Serializable {
	// id
	private Integer userId;

	// 用户名
	private String userName;

	// 密码
	private String userPwd;

	// 性别
	private String userGender;

	// 年龄
	private Integer userAge;

	// 简介
	private String userIntroduce;

	// 头像（地址）
	private String userPhoto;
}
