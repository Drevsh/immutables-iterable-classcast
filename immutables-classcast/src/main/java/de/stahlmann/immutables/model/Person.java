package de.stahlmann.immutables.model;

import org.immutables.criteria.Criteria;
import org.immutables.criteria.reactor.ReactorReadable;
import org.immutables.criteria.reactor.ReactorWatchable;
import org.immutables.criteria.reactor.ReactorWritable;
import org.immutables.value.Value;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Criteria
@Criteria.Repository(facets = { ReactorWatchable.class, ReactorReadable.class, ReactorWritable.class })
@Value.Immutable
public interface Person {
    @Criteria.Id
    String id();
    String fullName();
    Optional<String> nickName();
    int age();
    Collection<Pet> pets();
    Optional<Friend> bestFriend();
}
