package com.horsefeed.controller;

import com.horsefeed.model.Horse;
import com.horsefeed.service.HorseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/horses")
public class HorseController {

  @Autowired
  private HorseService horseService;

  @GetMapping
  public String listHorses(Model model) {
    List<Horse> horses = horseService.getAllHorses();
    model.addAttribute("horses", horses);
    return "horses/list";
  }

  @GetMapping("/new")
  public String showAddForm(Model model) {
    model.addAttribute("horse", new Horse());
    model.addAttribute("activityLevels", Horse.ActivityLevel.values());
    return "horses/form";
  }

  @GetMapping("/{id}")
  public String showHorse(@PathVariable Long id, Model model) {
    Horse horse = horseService.getHorseById(id);
    if (horse == null) {
      return "redirect:/horses";
    }
    model.addAttribute("horse", horse);
    return "horses/detail";
  }

  @GetMapping("/{id}/edit")
  public String showEditForm(@PathVariable Long id, Model model) {
    Horse horse = horseService.getHorseById(id);
    if (horse == null) {
      return "redirect:/horses";
    }
    model.addAttribute("horse", horse);
    model.addAttribute("activityLevels", Horse.ActivityLevel.values());
    return "horses/form";
  }

  @PostMapping
  public String saveHorse(@Valid @ModelAttribute Horse horse,
      BindingResult result,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      model.addAttribute("activityLevels", Horse.ActivityLevel.values());
      return "horses/form";
    }

    try {
      Horse savedHorse = horseService.saveHorse(horse);
      redirectAttributes.addFlashAttribute("successMessage",
          "Horse " + savedHorse.getName() + " saved successfully!");
      return "redirect:/horses";
    } catch (Exception e) {
      model.addAttribute("errorMessage", e.getMessage());
      model.addAttribute("activityLevels", Horse.ActivityLevel.values());
      return "horses/form";
    }
  }

  @PostMapping("/{id}/delete")
  public String deleteHorse(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      Horse horse = horseService.getHorseById(id);
      horseService.deleteHorse(id);
      redirectAttributes.addFlashAttribute("successMessage",
          "Horse " + (horse != null ? horse.getName() : "with ID " + id) + " deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
    }
    return "redirect:/horses";
  }

  @GetMapping("/activity/{activityLevel}")
  public String listHorsesByActivity(@PathVariable String activityLevel, Model model) {
    try {
      Horse.ActivityLevel level = Horse.ActivityLevel.valueOf(activityLevel.toUpperCase());
      List<Horse> horses = horseService.getHorsesByActivityLevel(level);
      model.addAttribute("horses", horses);
      model.addAttribute("activityLevel", level);
      return "horses/by-activity";
    } catch (IllegalArgumentException e) {
      return "redirect:/horses";
    }
  }
}
