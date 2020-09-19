/*
 * Copyright 2015-2020 Austin Keener, Michael Ritter, Florian Spieß, and the JDA contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This class was taken (and modified) from DV8FromTheWorld's project JDA.
 * https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/internal/utils/Checks.java
 * All credit goes to the original authors.
 */

package net.azzerial.jmgur.internal.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Check {

    /* --- Expression Checks --- */

    @Contract("false, _ -> fail")
    public static void check(final boolean expression, final String message) {
        if (!expression)
            throw new IllegalArgumentException(message);
    }

    @Contract("false, _, _ -> fail")
    public static void check(final boolean expression, final String message, final Object... args) {
        if (!expression)
            throw new IllegalArgumentException(String.format(message, args));
    }

    @Contract("false, _, _ -> fail")
    public static void check(final boolean expression, final String message, final Object arg) {
        if (!expression)
            throw new IllegalArgumentException(String.format(message, arg));
    }

    /* --- Object Checks --- */

    @Contract("null, _ -> fail")
    public static void notNull(final Object argument, final String name) {
        if (argument == null)
            throw new IllegalArgumentException(name + " may not be null");
    }

    /* --- String Checks --- */

    @Contract("null, _ -> fail")
    public static void notEmpty(final CharSequence argument, final String name) {
        notNull(argument, name);
        if (Helper.isEmpty(argument))
            throw new IllegalArgumentException(name + " may not be empty");
    }

    @Contract("null, _ -> fail")
    public static void notBlank(final CharSequence argument, final String name) {
        notNull(argument, name);
        if (Helper.isBlank(argument))
            throw new IllegalArgumentException(name + " may not be blank");
    }

    @Contract("null, _ -> fail")
    public static void noWhitespace(final CharSequence argument, final String name) {
        notNull(argument, name);
        if (Helper.containsWhitespace(argument))
            throw new IllegalArgumentException(name + " may not contain blanks");
    }

    /* --- Array & Collection Checks --- */

    @Contract("null, _ -> fail")
    public static void notEmpty(final Collection<?> argument, final String name) {
        notNull(argument, name);
        if (argument.isEmpty())
            throw new IllegalArgumentException(name + " may not be empty");
    }

    @Contract("null, _ -> fail")
    public static void notEmpty(final Object[] argument, final String name) {
        notNull(argument, name);
        if (argument.length == 0)
            throw new IllegalArgumentException(name + " may not be empty");
    }

    @Contract("null, _ -> fail")
    public static void noneNull(final Collection<?> argument, final String name) {
        notNull(argument, name);
        argument.forEach(it -> notNull(it, name));
    }

    @Contract("null, _ -> fail")
    public static void noneNull(final Object[] argument, final String name) {
        notNull(argument, name);
        for (Object it : argument)
            notNull(it, name);
    }

    @Contract("null, _ -> fail")
    public static <T extends CharSequence> void noneEmpty(final Collection<T> argument, final String name) {
        notNull(argument, name);
        argument.forEach(it -> notEmpty(it, name));
    }

    @Contract("null, _ -> fail")
    public static <T extends CharSequence> void noneBlank(final Collection<T> argument, final String name) {
        notNull(argument, name);
        argument.forEach(it -> notBlank(it, name));
    }

    @Contract("null, _ -> fail")
    public static <T extends CharSequence> void noneContainBlanks(final Collection<T> argument, final String name) {
        notNull(argument, name);
        argument.forEach(it -> noWhitespace(it, name));
    }

    /* --- Numeric Checks --- */

    public static void positive(final int n, final String name) {
        if (n <= 0)
            throw new IllegalArgumentException(name + " may not be negative or zero");
    }

    public static void positive(final long n, final String name) {
        if (n <= 0)
            throw new IllegalArgumentException(name + " may not be negative or zero");
    }

    public static void notNegative(final int n, final String name) {
        if (n < 0)
            throw new IllegalArgumentException(name + " may not be negative");
    }

    public static void notNegative(final long n, final String name) {
        if (n < 0)
            throw new IllegalArgumentException(name + " may not be negative");
    }
}
