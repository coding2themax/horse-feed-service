package com.horsefeed.controller;

import com.horsefeed.service.HorseService;
import com.horsefeed.service.FeedTypeService;
import com.horsefeed.service.FeedingScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private HorseService horseService;

  @Autowired
  private FeedTypeService feedTypeService;

  @Autowired
  private FeedingScheduleService scheduleService;

  @GetMapping("/")
  public String home(Model model) {
    // Add dashboard statistics
    model.addAttribute("horseCount", horseService.getHorseCount());
    model.addAttribute("feedTypeCount", feedTypeService.getFeedTypeCount());
    model.addAttribute("scheduleCount", scheduleService.getScheduleCount());

    // Add recent data for dashboard
    model.addAttribute("recentHorses", horseService.getAllHorses());
    model.addAttribute("recentSchedules", scheduleService.getAllSchedules());

    return "index";
  }

  @GetMapping("/dashboard")
  public String dashboard(Model model) {
    return home(model); // Redirect to home for now
  }
}
