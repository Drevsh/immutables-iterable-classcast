package de.stahlmann.immutables;

import de.stahlmann.immutables.model.ImmutablePerson;
import de.stahlmann.immutables.model.ImmutablePet;
import de.stahlmann.immutables.model.Person;
import de.stahlmann.immutables.model.PersonCriteria;
import de.stahlmann.immutables.model.PersonRepository;
import de.stahlmann.immutables.model.Pet;
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
        repository.insert(person);

        // Exception is thrown here
        // Exception in thread "main" java.lang.ClassCastException: class org.immutables.criteria.matcher.IterableMatcher$1Local cannot be cast to class de.stahlmann.immutables.model.PetCriteriaTemplate
        PersonCriteria criteria = PersonCriteria.person.pets.any().name.contains("Panda");

        Person person1 = repository.find(criteria)
                .one();

        System.out.println(person1);
    }
}
