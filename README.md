# Gallego
Optionals for Java 7

> It depens...
>
> — *Someone in Galicia*

This a lightweight copy of [Guava's Optional](https://github.com/google/guava/blob/master/guava/src/com/google/common/base/Optional.java) interface.

For more awesome information, check out Guava **wiki** on [Using/avoiding null](https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained).

## Basic usage
```java
Optional<Integer> possible = Optional.of(5);
possible.isPresent(); // returns true
possible.get(); // returns 5
```

More info in [Guava's wiki](https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained#optional)

## Download
The library is available in *jCenter*.

Gradle:
```
compile 'com.sloydev:gallego:1.0.0'
```

Maven:
```
<dependency>
  <groupId>com.sloydev</groupId>
  <artifactId>gallego</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
