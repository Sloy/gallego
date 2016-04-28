/*
 * Copyright (C) 2011 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sloydev.gallego;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

/**
 * Unit test for {@link Optional}.
 *
 * @author Kurt Alfred Kluever
 */
public final class OptionalTest {
    @Test
    public void testAbsent() {
        Optional<String> optionalName = Optional.absent();
        assertFalse(optionalName.isPresent());
    }

    @Test
    public void testOf() {
        assertEquals("training", Optional.of("training").get());
    }


    @Test(expected = NullPointerException.class)
    public void testOf_null() {
        Optional.of(null);
    }

    @Test
    public void testFromNullable() {
        Optional<String> optionalName = Optional.fromNullable("bob");
        assertEquals("bob", optionalName.get());
    }

    @Test
    public void testFromNullable_null() {
        // not promised by spec, but easier to test
        assertSame(Optional.absent(), Optional.fromNullable(null));
    }

    @Test
    public void testIsPresent_no() {
        assertFalse(Optional.absent().isPresent());
    }

    @Test
    public void testIsPresent_yes() {
        assertTrue(Optional.of("training").isPresent());
    }

    @Test(expected = IllegalStateException.class)
    public void testGet_absent() {
        Optional<String> optional = Optional.absent();
        optional.get();
    }

    @Test
    public void testGet_present() {
        assertEquals("training", Optional.of("training").get());
    }

    @Test
    public void testOr_T_present() {
        assertEquals("a", Optional.of("a").or("default"));
    }

    @Test
    public void testOr_T_absent() {
        assertEquals("default", Optional.absent().or("default"));
    }

    @Test
    public void testOr_supplier_present() {
        assertEquals("a", Optional.of("a").or(Suppliers.ofInstance("fallback")));
    }

    @Test
    public void testOr_supplier_absent() {
        assertEquals("fallback", Optional.absent().or(Suppliers.ofInstance("fallback")));
    }

    @Test(expected = NullPointerException.class)
    public void testOr_nullSupplier_absent() {
        Supplier<Object> nullSupplier = Suppliers.ofInstance(null);
        Optional<Object> absentOptional = Optional.absent();
        absentOptional.or(nullSupplier);
    }

    @Test
    public void testOr_nullSupplier_present() {
        Supplier<String> nullSupplier = Suppliers.ofInstance(null);
        assertEquals("a", Optional.of("a").or(nullSupplier));
    }

    @Test
    public void testOr_Optional_present() {
        assertEquals(Optional.of("a"), Optional.of("a").or(Optional.of("fallback")));
    }

    @Test
    public void testOr_Optional_absent() {
        assertEquals(Optional.of("fallback"), Optional.absent().or(Optional.of("fallback")));
    }

    @Test
    public void testOrNull_present() {
        assertEquals("a", Optional.of("a").orNull());
    }

    @Test
    public void testOrNull_absent() {
        assertNull(Optional.absent().orNull());
    }

    @Test
    public void testAsSet_present() {
        Set<String> expected = Collections.singleton("a");
        assertEquals(expected, Optional.of("a").asSet());
    }

    @Test
    public void testAsSet_absent() {
        assertTrue("Returned set should be empty", Optional.absent().asSet().isEmpty());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsSet_presentIsImmutable() {
        Set<String> presentAsSet = Optional.of("a").asSet();
        presentAsSet.add("b");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAsSet_absentIsImmutable() {
        Set<Object> absentAsSet = Optional.absent().asSet();
        absentAsSet.add("foo");
    }

    @Test
    public void testTransform_absent() {
        assertEquals(Optional.absent(), Optional.absent().transform(Functions.identity()));
        assertEquals(Optional.absent(), Optional.absent().transform(Functions.toStringFunction()));
    }

    @Test
    public void testTransform_presentIdentity() {
        assertEquals(Optional.of("a"), Optional.of("a").transform(Functions.identity()));
    }

    @Test
    public void testTransform_presentToString() {
        assertEquals(Optional.of("42"), Optional.of(42).transform(Functions.toStringFunction()));
    }

    @Test(expected = NullPointerException.class)
    public void testTransform_present_functionReturnsNull() {
        Optional<String> unused =
                Optional.of("a")
                        .transform(
                                new Function<String, String>() {
                                    @Override
                                    public String apply(String input) {
                                        return null;
                                    }
                                });
        fail("Should throw if Function returns null.");
    }

    @Test
    public void testTransform_abssent_functionReturnsNull() {
        assertEquals(Optional.absent(),
                Optional.absent().transform(
                        new Function<Object, Object>() {
                            @Override
                            public Object apply(Object input) {
                                return null;
                            }
                        }));
    }

    // TODO(kevinb): use EqualsTester

    @Test
    public void testEqualsAndHashCode_absent() {
        assertEquals(Optional.<String>absent(), Optional.<Integer>absent());
        assertEquals(Optional.absent().hashCode(), Optional.absent().hashCode());
        assertThat(Optional.absent().hashCode())
                .isNotEqualTo(Optional.of(0).hashCode());
    }

    @Test
    public void testEqualsAndHashCode_present() {
        assertEquals(Optional.of("training"), Optional.of("training"));
        assertFalse(Optional.of("a").equals(Optional.of("b")));
        assertFalse(Optional.of("a").equals(Optional.absent()));
        assertEquals(Optional.of("training").hashCode(), Optional.of("training").hashCode());
    }

    @Test
    public void testToString_absent() {
        assertEquals("Optional.absent()", Optional.absent().toString());
    }

    @Test
    public void testToString_present() {
        assertEquals("Optional.of(training)", Optional.of("training").toString());
    }

    @Test
    public void testPresentInstances_allPresent() {
        List<Optional<String>> optionals =
                ImmutableList.of(Optional.of("a"), Optional.of("b"), Optional.of("c"));
        assertThat(Optional.presentInstances(optionals)).containsExactly("a", "b", "c").inOrder();
    }

    @Test
    public void testPresentInstances_allAbsent() {
        List<Optional<Object>> optionals =
                ImmutableList.of(Optional.absent(), Optional.absent());
        assertThat(Optional.presentInstances(optionals)).isEmpty();
    }

    @Test
    public void testPresentInstances_somePresent() {
        List<Optional<String>> optionals =
                ImmutableList.of(Optional.of("a"), Optional.<String>absent(), Optional.of("c"));
        assertThat(Optional.presentInstances(optionals)).containsExactly("a", "c").inOrder();
    }

    @Test
    public void testPresentInstances_callingIteratorTwice() {
        List<Optional<String>> optionals =
                ImmutableList.of(Optional.of("a"), Optional.<String>absent(), Optional.of("c"));
        Iterable<String> onlyPresent = Optional.presentInstances(optionals);
        assertThat(onlyPresent).containsExactly("a", "c").inOrder();
        assertThat(onlyPresent).containsExactly("a", "c").inOrder();
    }

    @Test
    public void testPresentInstances_wildcards() {
        List<Optional<? extends Number>> optionals =
                ImmutableList.<Optional<? extends Number>>of(Optional.<Double>absent(), Optional.of(2));
        Iterable<Number> onlyPresent = Optional.presentInstances(optionals);
        assertThat(onlyPresent).containsExactly(2).inOrder();
    }

}