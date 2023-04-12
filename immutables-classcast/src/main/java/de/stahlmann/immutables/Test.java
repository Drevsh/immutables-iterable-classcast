package de.stahlmann.immutables;

import de.stahlmann.immutables.model.ImmutablePerson;
import de.stahlmann.immutables.model.ImmutablePet;
import de.stahlmann.immutables.model.Person;
import de.stahlmann.immutables.model.PersonCriteria;
import de.stahlmann.immutables.model.PersonRepository;
import de.stahlmann.immutables.model.Pet;
import org.immutables.criteria.backend.WriteResult;
import org.immutables.criteria.inmemory.InMemoryBackend;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        InMemoryBackend backend = new InMemoryBackend();
        PersonRepository repository = new PersonRepository(backend);

        List<Pet> pets = List.of(//
                ImmutablePet.builder()
                        .name("Gecko")
                        .type(Pet.PetType.gecko)
                        .build(), //
                ImmutablePet.builder()
                        .name("Panda")
                        .type(Pet.PetType.panda)
                        .build());
        ImmutablePerson person = ImmutablePerson.builder()
                .id("1")
                .fullName("John")
                .age(23)
                .pets(pets)
                .build();

        WriteResult block = repository.insert(person)
                .block();

        PersonCriteria criteria = PersonCriteria.person
                .pets.any().name.contains("Panda");

        // Exception in thread "main" java.lang.IllegalArgumentException: object is not an instance of declaring class
        Person person1 = repository.find(criteria)
                .one()
                .block();

        System.out.println(person1);
    }
}
