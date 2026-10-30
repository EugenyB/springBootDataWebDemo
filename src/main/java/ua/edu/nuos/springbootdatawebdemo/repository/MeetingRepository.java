package ua.edu.nuos.springbootdatawebdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.springbootdatawebdemo.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
}