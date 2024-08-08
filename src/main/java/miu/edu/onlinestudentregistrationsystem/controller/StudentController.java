package miu.edu.onlinestudentregistrationsystem.controller;

import jakarta.validation.Valid;
import miu.edu.onlinestudentregistrationsystem.Enum.InternationalStatus;
import miu.edu.onlinestudentregistrationsystem.model.Student;
import miu.edu.onlinestudentregistrationsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        return "student/list";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("statusOptions", InternationalStatus.values());
        return "student/register";
    }

    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statusOptions", InternationalStatus.values());
            return "student/register";
        }
        studentService.registerStudent(student);
        return "redirect:/api/v1/students/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Student student = studentService.findStudentById(id).orElse(null);
        if (student != null) {
            model.addAttribute("student", student);
            model.addAttribute("statusOptions", InternationalStatus.values());
            return "student/edit";
        }
        return "redirect:/api/v1/students/list"; // or handle not found
    }

    @PostMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Long id, @Valid @ModelAttribute Student student, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statusOptions", InternationalStatus.values());
            return "student/edit";
        }
        student.setStudentId(id);
        studentService.registerStudent(student);
        return "redirect:/api/v1/students/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        studentService.deleteStudent(id);
        return "redirect:/api/v1/students/list";
    }
    @PostMapping("/search")
    public String searchStudent(@RequestParam("query") String query, Model model) {
        List<Student> students = studentService.searchStudents(query);
        model.addAttribute("students", students);
        return "student/list"; // Make sure this view shows the search results
    }
    @GetMapping("/search")
    public String showSearchForm() {
        return "student/search"; // This should match the name of your search template
    }

}
