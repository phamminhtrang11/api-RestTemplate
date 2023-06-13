package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.RequestDataValueProcessor;

@RestController
public class apiController {
    @RestController
    class StudentController {

        private final RestTemplate restTemplate;
        private final String apiUrl = "http://localhost:8080/students";

        public StudentController(RestTemplate restTemplate) {
            this.restTemplate = restTemplate;
        }

        @GetMapping("/students/{class}")
        public ResponseEntity<?> getStudentsByClass1(@PathVariable("class") String className) {
            String apiUrl = "http://localhost:8080/students/" + className;
            ResponseEntity<?> responseEntity = restTemplate.getForEntity(apiUrl, Object.class);
            return responseEntity;
        }

        @PostMapping("/my-api/students")
        @ResponseBody
        public ResponseEntity<?> getStudentsByClass(@RequestBody String className) {
            String apiUrl = "http://localhost:8080/api/getStudentByClass";
            Student[] students = restTemplate.postForObject(apiUrl, className, Student[].class);

            if (students == null || students.length == 0) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(Arrays.asList(students), HttpStatus.OK);
        }
    }
}