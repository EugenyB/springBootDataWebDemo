package ua.edu.nuos.springbootdatawebdemo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.nuos.springbootdatawebdemo.entity.Meeting;
import ua.edu.nuos.springbootdatawebdemo.service.PersonService;
import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class MainController {

    private PersonService personService;

    @GetMapping("/people")
    public String people(Model model) {
        model.addAttribute("people", personService.getPeople());
        return "people";
    }

    @GetMapping("/person/{id}/meetings")
    public String meetingsForPerson(Model model, @PathVariable Integer id) {
        List<Meeting> meetings = personService.findMeetings(id) ;
        List<Meeting> available = personService.findAvailableMeetings(id);
        model.addAttribute("meetings", meetings);
        model.addAttribute("available", available);
        model.addAttribute("person", personService.findById(id));
        return "meetings_by_person";
    }

    @GetMapping("/people/meeting/add/{pid}/{mid}")
    public String addMeetingForPerson(@PathVariable Integer pid, @PathVariable Integer mid) {
        personService.addMeeting(pid,mid);
        return "redirect:/person/" + pid + "/meetings";
    }

    @GetMapping("/people/meeting/remove/{pid}/{mid}")
    public String removeMeetingForPerson(@PathVariable Integer pid, @PathVariable Integer mid) {
        personService.deleteMeeting(pid,mid);
        return "redirect:/person/" + pid + "/meetings";
    }

    @GetMapping("/people/delete/{id}")
    public String delete(@PathVariable Integer id) {
        personService.deleteById(id);
        return "redirect:/people";
    }
}

