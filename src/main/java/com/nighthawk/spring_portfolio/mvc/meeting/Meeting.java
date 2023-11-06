package com.nighthawk.spring_portfolio.mvc.meeting;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data  
@NoArgsConstructor
@AllArgsConstructor
@Entity 
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique=true)
    private String meetingName;

    private String description;


    public static Meeting init() {
        Meeting meeting = new Meeting();
        meeting.setMeetingName("Meeting 1");
        meeting.setDescription("Creating the database for meeting notes");
        return meeting;
    }
}

