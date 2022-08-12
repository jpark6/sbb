package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
  private final QuestionService questionService;

  @GetMapping("/list")
  public String list(Model model, @RequestParam(value="page", defaultValue="1") int page) {
    
    Page<Question> paging =  this.questionService.getList(page > 1 ? page-1 : 0);
    int totalPages = paging.getTotalPages();
    if(totalPages != 0 && totalPages <= page) {
      page = totalPages - 1;
      paging =  this.questionService.getList(page);
    }
    model.addAttribute("paging", paging);
    return "question_list";
  }
  @GetMapping("/detail/{id}")
  public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
    Question question = this.questionService.getQuestion(id);
    model.addAttribute("question", question);
    return "question_detail";
  }

  @GetMapping("/create")
  public String questionCreate(QuestionForm questionForm) {
    return "question_form";
  }
  
  @PostMapping("/create")
  public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "question_form";
    }
    this.questionService.create(questionForm.getSubject(), questionForm.getContent());
    return "redirect:/question/list";
  }
}
