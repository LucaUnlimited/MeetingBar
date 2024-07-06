package com.lucaunlimited.meeting.services;

import com.lucaunlimited.meeting.models.HabEntity;
import com.lucaunlimited.meeting.repositorys.HabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabService {
    @Autowired
    private HabRepository roomRepository;

    public void initializeRooms() {
        for (int i = 1; i <= 22; i++) {
            HabEntity room = new HabEntity();
            room.setNumber("Room " + i);
            roomRepository.save(room);
        }
    }

}
