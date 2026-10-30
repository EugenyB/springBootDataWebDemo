package ua.edu.nuos.springbootdatawebdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.nuos.springbootdatawebdemo.repository.MeetingRepository;

@Service
@AllArgsConstructor
public class MeetingService {
    private MeetingRepository meetingRepository;

}
