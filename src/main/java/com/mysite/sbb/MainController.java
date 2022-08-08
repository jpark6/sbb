package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
@GetMapping("/")
public String index() {
  return "redirect:/question/list";
}

@GetMapping("/sbb")
@ResponseBody
public String sbb() {
  return "안녕하세요 sbb에 오신 것을 환영합니다.";
}
}

