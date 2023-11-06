package com.nighthawk.spring_portfolio.mvc.meeting;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingJpaRepository extends JpaRepository<Meeting, Long> {

    List<Meeting> findAllByOrderByMeetingNameAsc(); 

    List<Meeting> findByMeetingNameIgnoreCase(String meetingName);
}

