/*
 * Copyright 2020 Robin Mercier
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.azzerial.jmgur.api.entities.subentities;

import net.azzerial.jmgur.internal.utils.Check;
import org.jetbrains.annotations.NotNull;

public enum GalleryTimeWindow {
    DAY("day", true),
    WEEK("week"),
    MONTH("month"),
    YEAR("year"),
    ALL("all"),
    UNKNOWN("unknown");

    private final String key;
    private final boolean isDefault;

    /* Constructors */

    GalleryTimeWindow(@NotNull String key) {
        this.key = key;
        this.isDefault = false;
    }

    GalleryTimeWindow(@NotNull String key, boolean isDefault) {
        this.key = key;
        this.isDefault = isDefault;
    }

    /* Getters & Setters */

    public boolean isDefault() {
        return isDefault;
    }

    @NotNull
    public String getKey() {
        return key;
    }

    /* Static Methods */

    @NotNull
    public static GalleryTimeWindow fromKey(@NotNull String key) {
        Check.notNull(key, "key");
        for (GalleryTimeWindow gtw : values()) {
            if (gtw.key.equalsIgnoreCase(key))
                return gtw;
        }
        return UNKNOWN;
    }
}
