package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CalendarController {

    private final EventRepository eventRepository;

    public CalendarController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping(value="/staticcalendar", method= RequestMethod.GET)
    public ModelAndView staticcalendar() {
        return new ModelAndView("staticcalendar");
    }

    @RequestMapping(value="/calendar", method=RequestMethod.GET)
    public ModelAndView calendar() {
        return new ModelAndView("calendar");
    }

    @RequestMapping(value="/jsoncalendar", method=RequestMethod.GET)
    public ModelAndView jsoncalendar() {
        return new ModelAndView("jsoncalendar");
    }

    @RequestMapping(value="/eventlist", method=RequestMethod.GET)
    public String events(HttpServletRequest request, Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }
}
