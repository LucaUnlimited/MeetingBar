package com.lucaunlimited.meeting.controllers;

import com.lucaunlimited.meeting.services.HabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class HabController {
        @Autowired
        private HabService roomService;

        @PostMapping("/initialize")
        public ResponseEntity<Void> initializeRooms() {
            roomService.initializeRooms();
            return ResponseEntity.ok().build();
        }


}
