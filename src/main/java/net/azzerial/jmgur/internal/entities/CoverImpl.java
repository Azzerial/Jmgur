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

package net.azzerial.jmgur.internal.entities;

import lombok.Setter;
import net.azzerial.jmgur.api.Jmgur;
import net.azzerial.jmgur.api.entities.Cover;
import org.jetbrains.annotations.NotNull;

import static net.azzerial.jmgur.internal.utils.Helper.print;

@Setter
public final class CoverImpl implements Cover {

    private final transient Jmgur api;

    private String name;
    private String url;

    /* Constructors */

    CoverImpl(@NotNull Jmgur api) {
        this.api = api;
    }

    /* Getters & Setters */

    @NotNull
    @Override
    public Jmgur getApi() {
        return api;
    }

    @NotNull
    @Override
    public String getName() {
        return name;
    }

    @NotNull
    @Override
    public String getUrl() {
        return url;
    }

    /* Methods */

    @Override
    public String toString() {
        return "Cover{" +
            "name=" + print(name) +
            ", url=" + print(url) +
            '}';
    }
}
