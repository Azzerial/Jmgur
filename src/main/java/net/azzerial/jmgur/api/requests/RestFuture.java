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
 * https://github.com/DV8FromTheWorld/JDA/blob/master/src/main/java/net/dv8tion/jda/api/requests/RestFuture.java
 * All credit goes to the original authors.
 */

package net.azzerial.jmgur.api.requests;

import net.azzerial.jmgur.internal.requests.Route;
import net.azzerial.jmgur.internal.requests.restaction.RestActionImpl;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public final class RestFuture<T> extends CompletableFuture<T> {

    final Request<T> request;

    /* Constructors */

    public RestFuture(@NotNull RestActionImpl<T> restAction, @Nullable RequestBody data, long deadline, @NotNull Route.CompiledRoute route) {
        this.request = new Request<>(restAction, this::complete, this::completeExceptionally, data, deadline, route);
        restAction.getApi().getRequester().request(this.request);
    }

    public RestFuture(@Nullable T t) {
        this.request = null;
        complete(t);
    }

    public RestFuture(@NotNull Throwable t) {
        this.request = null;
        completeExceptionally(t);
    }

    /* Methods */

    @Override
    public boolean cancel(boolean mayInterrupt) {
        if (this.request != null)
            this.request.cancel();
        return (!isDone() && !isCancelled()) && super.cancel(mayInterrupt);
    }
}