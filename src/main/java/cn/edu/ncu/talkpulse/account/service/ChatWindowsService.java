package cn.edu.ncu.talkpulse.account.service;

import cn.edu.ncu.talkpulse.dto.ChatWindows;

import java.time.LocalDateTime;
import java.util.List;

public interface ChatWindowsService {
    List<ChatWindows> chatwindowset(Integer uid);
}
