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

package net.azzerial.jmgur.api;

import net.azzerial.jmgur.api.entities.Image;
import net.azzerial.jmgur.api.entities.dto.ImageInformationDTO;
import net.azzerial.jmgur.api.entities.dto.ImageUploadDTO;
import net.azzerial.jmgur.api.requests.restaction.RestAction;
import org.jetbrains.annotations.NotNull;

public interface ImageRepository {

    @NotNull
    Jmgur getApi();

    /* --- Core --- */

    @NotNull
    RestAction<Image> getImage(@NotNull String hash);

    @NotNull
    RestAction<Image> uploadImage(@NotNull ImageUploadDTO upload);

    @NotNull
    RestAction<Boolean> deleteImage(@NotNull String hash);

    @NotNull
    RestAction<Boolean> updateImageInformation(@NotNull String hash, @NotNull ImageInformationDTO information);

    @NotNull
    RestAction<Boolean> favoriteImage(@NotNull String hash);
}
