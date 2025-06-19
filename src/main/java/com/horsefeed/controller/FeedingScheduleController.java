package com.horsefeed.controller;

import com.horsefeed.model.FeedingSchedule;
import com.horsefeed.model.Horse;
import com.horsefeed.model.FeedType;
import com.horsefeed.service.FeedingScheduleService;
import com.horsefeed.service.HorseService;
import com.horsefeed.service.FeedTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/schedules")
public class FeedingScheduleController {

  @Autowired
  private FeedingScheduleService scheduleService;

  @Autowired
  private HorseService horseService;

  @Autowired
  private FeedTypeService feedTypeService;

  @GetMapping
  public String listSchedules(Model model) {
    List<FeedingSchedule> schedules = scheduleService.getAllSchedules();
    model.addAttribute("schedules", schedules);
    return "schedules/list";
  }

  @GetMapping("/new")
  public String showAddForm(Model model) {
    model.addAttribute("schedule", new FeedingSchedule());
    addFormData(model);
    return "schedules/form";
  }

  @GetMapping("/{id}")
  public String showSchedule(@PathVariable Long id, Model model) {
    FeedingSchedule schedule = scheduleService.getScheduleById(id);
    if (schedule == null) {
      return "redirect:/schedules";
    }
    model.addAttribute("schedule", schedule);
    return "schedules/detail";
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    FeedingSchedule schedule = scheduleService.getScheduleById(id);
    if (schedule == null) {
      return "redirect:/schedules";
    }
    model.addAttribute("schedule", schedule);
    addFormData(model);
    return "schedules/form";
  }

  @PostMapping
  public String saveSchedule(@Valid @ModelAttribute FeedingSchedule schedule,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      addFormData(model);
      return "schedules/form";
    }

    try {
      scheduleService.saveSchedule(schedule);
      redirectAttributes.addFlashAttribute("successMessage",
          "Feeding schedule saved successfully!");
      return "redirect:/schedules";
    } catch (Exception e) {
      model.addAttribute("errorMessage", e.getMessage());
      addFormData(model);
      return "schedules/form";
    }
  }

  @PostMapping("/{id}/delete")
  public String deleteSchedule(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      scheduleService.deleteSchedule(id);
      redirectAttributes.addFlashAttribute("successMessage", "Feeding schedule deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }
    return "redirect:/schedules";
  }

  @GetMapping("/horse/{horseId}")
  public String listSchedulesByHorse(@PathVariable Long horseId, Model model) {
    Horse horse = horseService.getHorseById(horseId);
    if (horse == null) {
      return "redirect:/schedules";
    }

    List<FeedingSchedule> schedules = scheduleService.getSchedulesByHorseId(horseId);
    model.addAttribute("schedules", schedules);
    model.addAttribute("horse", horse);
    return "schedules/by-horse";
  }

  private void addFormData(Model model) {
    List<Horse> horses = horseService.getAllHorses();
    List<FeedType> feedTypes = feedTypeService.getAllFeedTypes();

    model.addAttribute("horses", horses);
    model.addAttribute("feedTypes", feedTypes);
    model.addAttribute("frequencies", FeedingSchedule.Frequency.values());
  }
}
