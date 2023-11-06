package com.nighthawk.spring_portfolio.mvc.meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/meetings")
public class MeetingApiController {

    @Autowired
    private MeetingJpaRepository repository;    

    @GetMapping("/")
    public ResponseEntity<List<Meeting>> getMeetings() {
        List<Meeting> meetings = repository.findAll();
        return new ResponseEntity<>(meetings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable long id) {
        Optional<Meeting> optional = repository.findById(id);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/")
    public ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting) {
        Meeting savedMeeting = repository.save(meeting);
        return new ResponseEntity<>(savedMeeting, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable long id, @RequestBody Meeting updatedMeeting) {
        Optional<Meeting> optional = repository.findById(id);
        if (optional.isPresent()) {
            Meeting existingMeeting = optional.get();
            // Update meeting properties with the provided values
            existingMeeting.setMeetingName(updatedMeeting.getMeetingName());
            existingMeeting.setDescription(updatedMeeting.getDescription());
            // Add more properties as needed
            Meeting updated = repository.save(existingMeeting);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable long id) {
        Optional<Meeting> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
