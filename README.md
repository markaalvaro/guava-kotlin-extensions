# Guava Kotlin Extensions

Extensions for Guava collections (Multimap, Multiset, Table) that allows them to be used like Kotlin's collection API. For example, to create an immutable list-based multimap, you could just either of the following:

```kotlin
val teamMemberLanguages = listMultimapOf("Blake" to "Kotlin", "Blake" to "Java", "Alex" to "Kotlin")
// or
val teamMemberLanguages = buildMultimap {
    putAll("Blake", listOf("Kotlin", "Java"))
    put("Alex", "Kotlin")
}
```

## :building_construction: Adding Guava Kotlin Extensions to a Project

*Coming soon!*

This library is not yet publishable and therefore is not available yet.

## :tada: Features

### Aliases

Aliases are provided for the collection types to give consistency with Kotlin naming. Note that the inheritence hierarchy is same as in Guava, and so the immutable `ListMultimap` extends the mutable `MutableListMultimap` instead of being the other way around like `List` and `MutableList`.

| Alias               | Java Type                                       |
|---------------------|-------------------------------------------------|
| Multimap            | com.google.common.collect.Multimap              |
| ListMultimap        | com.google.common.collect.ImmutableListMultimap |
| MutableListMultimap | com.google.common.collect.ListMultimap          |

Support for tables and multisets is *coming soon*.

### Creators

You can create collections using factory methods:

```kotlin
val favoriteNumbers = listMultimapOf("Jane" to 4, "Jane" to 5, "Eric" to 13)
```

or, for immutable types, using a builder:

```kotlin
val favoriteNumbers = buildListMultimap {
    putAll("Jane", listOf(4, 5))
    put("Eric" to 13)
    if (todayIsAMonday) put("Nicki" to 100)
}
```

### Operators

Several operators are supported for clean syntax for accessing and modifying (mutable only) collections.

```kotlin
val favoriteNumbers = mutableListMultimapOf("Jane" to 4, "Jane" to 5)
favoriteNumbers += "Eric" to 13
favoriteNumbers += listOf("Nicki" to 100, "Nicki" to 12)
if ("Eric" to 13 in favoriteNumbers) println("Eric's favorite number is 13!")
println(favoriteNumbers["Jane"])
favoriteNumbers["Jane"] = listOf(1, 3, 3) // easy as ABC!
```