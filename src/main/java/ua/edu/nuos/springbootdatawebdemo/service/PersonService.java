package ua.edu.nuos.springbootdatawebdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.nuos.springbootdatawebdemo.entity.Meeting;
import ua.edu.nuos.springbootdatawebdemo.entity.Person;
import ua.edu.nuos.springbootdatawebdemo.repository.MeetingRepository;
import ua.edu.nuos.springbootdatawebdemo.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;
    private MeetingRepository meetingRepository;

    public List<Person> getPeople() {
        return personRepository.findAll();
    }

    public List<Meeting> findMeetings(Integer personId) {
        Optional<Person> optionalPerson = personRepository.findById(personId);
        if (optionalPerson.isPresent()) {
            return new ArrayList<>(optionalPerson.get().getMeetings());
        }
        return Collections.emptyList();
    }

    public List<Meeting> findAvailableMeetings(Integer personId) {
        return personRepository.findAvailableMeetings(personId);
    }

    public void addMeeting(Integer pid, Integer mid) {
        personRepository.findById(pid).ifPresent(person -> {
            meetingRepository.findById(mid).ifPresent(meeting -> {
                person.getMeetings().add(meeting);
                personRepository.save(person);
            });
        });
    }

    public void deleteMeeting(Integer pid, Integer mid) {
        personRepository.findById(pid).ifPresent(person -> {
            person.getMeetings().removeIf(meeting -> meeting.getId().equals(mid));
            personRepository.save(person);
        });
    }

    public Person findById(Integer personId) {
        return personRepository.findById(personId).orElse(null);
    }

    public void deleteById(Integer personId) {
        personRepository.deleteById(personId);
    }
}
