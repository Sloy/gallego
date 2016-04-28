# Gallego
Optionals for Java 7

> It depens...
>
> — *Someone in Galicia*

[![Bintray](https://img.shields.io/bintray/v/sloy/maven/gallego.svg?maxAge=2592000)](https://bintray.com/sloy/maven/gallego/)

This a lightweight copy of [Guava's Optional](https://github.com/google/guava/blob/master/guava/src/com/google/common/base/Optional.java) interface.

For more awesome information, check out Guava **wiki** on [Using/avoiding null](https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained).

## Basic usage
```java
Optional<Integer> possible = Optional.of(5);
possible.isPresent(); // returns true
possible.get(); // returns 5
```

More info in [Guava's wiki](https://github.com/google/guava/wiki/UsingAndAvoidingNullExplained#optional)

## Why?
Java 8 Optionals are nice, but some of us can't enjoy them just yet*(Android)*.

Guava Optionals are great, but it's such a big dependency to have just for the Optional interface if you don't want to be messing around with Proguard and such.

This little library lets you use Optionals without adding more code than needed.

## Download
The library is available in *JCenter*.

Gradle:
```groovy
compile 'com.sloydev:gallego:1.0.0'
```

Maven:
```xml
<dependency>
  <groupId>com.sloydev</groupId>
  <artifactId>gallego</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```
