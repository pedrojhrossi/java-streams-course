package lectures;


import static org.assertj.core.api.Assertions.assertThat;

import beans.Car;
import beans.Person;
import beans.PersonDTO;
import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import mockdata.MockData;
import org.junit.Test;

public class Lecture5 {

  final Predicate<Car> carPredicate = car -> car.getPrice() < 6500;

  @Test
  public void understandingFilter() throws Exception {
    ImmutableList<Car> cars = MockData.getCars();
    List<Car> carsFiltered = cars.stream()
        .filter(carPredicate)
        .collect(Collectors.toList());

    carsFiltered.forEach(System.out::println);
    System.out.println(carsFiltered.size());
  }

  @Test
  public void ourFirstMapping() throws Exception {
    // transform from one data type to another
    List<Person> people = MockData.getPeople();

    Function<Person, PersonDTO> personPersonDTOFunction = person -> new PersonDTO(person.getId(),
        person.getFirstName(), person.getAge());

    List<PersonDTO> dtos = people.stream()
//        .map(person -> {
//          PersonDTO dto = new PersonDTO(person.getId(), person.getFirstName(), person.getAge());
//          return dto;
//        })
//        .map(personPersonDTOFunction)
        .map(PersonDTO::map)
        .collect(Collectors.toList());

    dtos.forEach(System.out::println);
    assertThat(dtos).hasSize(1000);
  }

  @Test
  public void averageCarPrice() throws Exception {
    // calculate average of car prices

    double average = MockData.getCars().stream()
        .filter(car -> car.getPrice() < 1000)
        .mapToDouble(Car::getPrice)
        .average()
        .orElse(0);

    System.out.println(average);
  }

  @Test
  public void test() throws Exception {

  }
}



