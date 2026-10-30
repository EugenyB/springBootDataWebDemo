package ua.edu.nuos.springbootdatawebdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.nuos.springbootdatawebdemo.entity.Meeting;
import ua.edu.nuos.springbootdatawebdemo.entity.Person;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select m from Meeting m where not exists (select 1 from Person p where m member of p.meetings and p.id = ?1)")
    List<Meeting> findAvailableMeetings(Integer personId);
}