package com.sparta.week01_homework.controller;

import com.sparta.week01_homework.Service.PersonService;
import com.sparta.week01_homework.model.Person;
import com.sparta.week01_homework.model.PersonRepository;
import com.sparta.week01_homework.model.PersonRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
// RestController 만들기. Spring에게 [CourseController]가 자동응답기(RestController)임을 알려주기 위해서 @RestController 어노테이션 붙이기.
// 안 붙이면 일반적인 Class(빵틀)과 다르다는 것을 Spring은 모름.

    public class PersonController { // <<-- 아이콘 생기면 Spring이 "ㅇㅋ 알겠다" 하는 것임.
        private final PersonRepository personRepository;
        private final PersonService personService;

        @GetMapping("/api/courses")
        public List<Person> getPersons() {
            return personRepository.findAll();
        }

        @PostMapping("/api/courses")
        public Person createPerson(@RequestBody PersonRequestDto requestDto) {
            Person person = new Person(requestDto);
            return personRepository.save(person);
        }

        @PutMapping("/api/courses/{id}")
        public Long updatePerson(@PathVariable Long id, @RequestBody PersonRequestDto requestDto) {
            return personService.update(id, requestDto);
        }

        @DeleteMapping("/api/courses/{id}")
        public Long deletePerson(@PathVariable Long id) {
            personRepository.deleteById(id);
            return id;
        }
    }