package de.stahlmann.immutables.model;

import org.immutables.criteria.Criteria;
import org.immutables.value.Value;

@Criteria
@Value.Immutable
public interface Pet {
    enum PetType {parrot, panda, iguana, gecko}
    PetType type();
    String name();
}
