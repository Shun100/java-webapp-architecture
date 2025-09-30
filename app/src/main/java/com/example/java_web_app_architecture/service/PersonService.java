package com.example.java_web_app_architecture.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.java_web_app_architecture.dto.Person;

public class PersonService {
  private static final List<Person> personList = new ArrayList<>();

  public Optional<Person> getPerson(int personId) {
    Optional<Person> optionalPerson = personList.stream()
      .filter(person -> person.personId() == personId)
      .findFirst();
    return optionalPerson;
  }

  public void addPerson(Person person) {
    if (!isRegistered(person.personId())) {
      personList.add(person);
    }
  }

  public void updatePerson(Person person) {
    int personId = person.personId();

    if (isRegistered(personId)) {
      personList.removeIf(p -> p.personId() == personId);
      personList.add(person);
    }
  }

  public List<Person> getPersonList() {
    return personList;
  }

  private boolean isRegistered(int personId) {
    return personList.stream()
      .anyMatch(person -> person.personId() == personId);
  }

  
}
