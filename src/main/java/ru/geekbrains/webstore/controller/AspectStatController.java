package ru.geekbrains.webstore.controller;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.webstore.service.AspectStatService;

@RestController
@AllArgsConstructor
@RequestMapping("/statistic")
public class AspectStatController {

  private AspectStatService aspectStatService;

  @GetMapping
  public Map<String, Long> getDurationMap() {
    return aspectStatService.getDurationMap();
  }

}
