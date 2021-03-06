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
 * https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/internal/utils/config/flags/ConfigFlag.java
 * All credit goes to the original authors.
 */

package net.azzerial.jmgur.api.utils.config.flags;

import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;

public enum ConfigFlag {
    RETRY_TIMEOUT(true);

    private final boolean isDefault;

    /* Constructors */

    ConfigFlag() {
        this(false);
    }

    ConfigFlag(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /* Static Methods */

    @NotNull
    public static EnumSet<ConfigFlag> getDefault() {
        final EnumSet<ConfigFlag> set = EnumSet.noneOf(ConfigFlag.class);
        for (ConfigFlag flag : values()) {
            if (flag.isDefault)
                set.add(flag);
        }
        return set;
    }
}
