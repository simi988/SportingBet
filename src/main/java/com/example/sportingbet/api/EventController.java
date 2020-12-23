package com.example.sportingbet.api;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.model.Event;
import com.example.sportingbet.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/event")
@RestController
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Object> addEvent(@Valid @NonNull @RequestBody Event event) throws EventException {
        eventService.addEvent(event);
        return new ResponseEntity<>("Event is added", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Event> getAllEvent() {
        return eventService.getAllEvents();
    }

}
