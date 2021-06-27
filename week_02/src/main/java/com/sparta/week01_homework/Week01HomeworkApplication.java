package com.sparta.week01_homework;

import com.sparta.week01_homework.Service.PersonService;
import com.sparta.week01_homework.model.Person;
import com.sparta.week01_homework.model.PersonRepository;
import com.sparta.week01_homework.model.PersonRequestDto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class Week01HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week01HomeworkApplication.class, args);
    }
    @Bean
    public CommandLineRunner demo(PersonRepository personRepository, PersonService personService){
        return(agrs) -> {
            //#CREATE
            personRepository.save(new Person("전영진",27,"개발자","대전광역시 중구"));
            System.out.println("Create 완료");

            //#READ
            List<Person> personList = personRepository.findAll();
            for (Person person : personList){
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getJob());
                System.out.println(person.getAddress());
            }

            //#UPDATE
//            Person new_person = new Person("전혜진",33,"학생","대전광역시 중구");
//            personService.update(1L, new_person);
            PersonRequestDto requestDto = new PersonRequestDto("전혜진",33,"학생","대전광역시 중구");
            personService.update(1L,requestDto);
            System.out.println("Update 완료");
            personList = personRepository.findAll();
            for (Person person : personList) {
                System.out.println(person.getId());
                System.out.println(person.getName());
                System.out.println(person.getAge());
                System.out.println(person.getJob());
                System.out.println(person.getAddress());
            }

            //#DELETE
//            personRepository.deleteAll();
        };
    }
}
