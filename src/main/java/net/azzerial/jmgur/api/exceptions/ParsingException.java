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
 * https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/api/exceptions/ParsingException.java
 * All credit goes to the original authors.
 */

package net.azzerial.jmgur.api.exceptions;

import net.azzerial.jmgur.internal.utils.Check;
import org.jetbrains.annotations.NotNull;

public final class ParsingException extends IllegalStateException {

    /* Constructors */

    public ParsingException(@NotNull String message) {
        super(message);
        Check.notNull(message, "message");
    }

    public ParsingException(@NotNull Exception cause) {
        super(cause);
        Check.notNull(cause, "cause");
    }

    public ParsingException(@NotNull String message, @NotNull Exception cause) {
        super(message, cause);
        Check.notNull(message, "message");
        Check.notNull(cause, "cause");
    }
}