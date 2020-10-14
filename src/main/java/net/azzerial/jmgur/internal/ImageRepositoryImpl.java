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

package net.azzerial.jmgur.internal;

import net.azzerial.jmgur.api.ImageRepository;
import net.azzerial.jmgur.api.Jmgur;
import net.azzerial.jmgur.api.entities.Image;
import net.azzerial.jmgur.api.entities.dto.ImageInformationDTO;
import net.azzerial.jmgur.api.entities.dto.ImageUploadDTO;
import net.azzerial.jmgur.api.entities.subentities.UploadFileType;
import net.azzerial.jmgur.api.requests.restaction.RestAction;
import net.azzerial.jmgur.api.utils.data.DataObject;
import net.azzerial.jmgur.internal.entities.EntityBuilder;
import net.azzerial.jmgur.internal.entities.ImageInformationDTOImpl;
import net.azzerial.jmgur.internal.entities.ImageUploadDTOImpl;
import net.azzerial.jmgur.internal.requests.Route;
import net.azzerial.jmgur.internal.requests.restaction.RestActionImpl;
import net.azzerial.jmgur.internal.utils.Check;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ImageRepositoryImpl implements ImageRepository {

    private final Jmgur api;

    /* Constructors */

    public ImageRepositoryImpl(@NotNull Jmgur api) {
        this.api = api;
    }

    /* Methods */

    @NotNull
    @Override
    public Jmgur getApi() {
        return api;
    }

    /* --- Core --- */

    @NotNull
    @Override
    public RestAction<Image> getImage(@NotNull String hash) {
        Check.notBlank(hash, "hash");
        return new RestActionImpl<>(
            api,
            Route.ImageEndpoints.GET_IMAGE.compile(hash),
            (req, res) -> {
                final EntityBuilder builder = api.getEntityBuilder();
                final DataObject obj = res.getObject().getObject("data");
                return builder.createImage(obj);
            }
        );
    }

    @NotNull
    @Override
    public RestAction<Image> uploadImage(@NotNull ImageUploadDTO dto) {
        Check.notNull(dto, "dto");
        final ImageUploadDTOImpl impl = (ImageUploadDTOImpl) dto;
        final MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);

        if (impl.getFileType() == null)
            throw new IllegalArgumentException("no image or video provided");
        if (impl.getFileType() == UploadFileType.BINARY_FILE) {
            body.addFormDataPart(
                impl.isFileVideo() ? "video" : "image",
                impl.getFile() != null ? impl.getFile().getName() : "",
                RequestBody.create(Objects.requireNonNull(impl.getFile()), null)
            );
        }
        if (impl.getFileType() == UploadFileType.BASE64 || impl.getFileType() == UploadFileType.URL)
            body.addFormDataPart("image", impl.getData());
        body.addFormDataPart("type", impl.getFileType().getKey());
        impl.getMap().forEach(body::addFormDataPart);

        return new RestActionImpl<>(
            api,
            Route.ImageEndpoints.POST_IMAGE.compile(),
            body.build(),
            (req, res) -> {
                final EntityBuilder builder = api.getEntityBuilder();
                final DataObject obj = res.getObject().getObject("data");
                return builder.createImage(obj);
            }
        );
    }

    @NotNull
    @Override
    public RestAction<Boolean> deleteImage(@NotNull String hash) {
        Check.notBlank(hash, "hash");
        return new RestActionImpl<>(
            api,
            Route.ImageEndpoints.DELETE_IMAGE.compile(hash),
            (req, res) -> {
                final DataObject obj = res.getObject();
                return obj.getBoolean("data");
            }
        );
    }

    @NotNull
    @Override
    public RestAction<Boolean> updateImageInformation(@NotNull String hash, @NotNull ImageInformationDTO dto) {
        Check.notBlank(hash, "hash");
        Check.notNull(dto, "dto");
        final ImageInformationDTOImpl impl = (ImageInformationDTOImpl) dto;
        final MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);

        impl.getMap().forEach(body::addFormDataPart);

        return new RestActionImpl<>(
            api,
            Route.ImageEndpoints.POST_IMAGE_INFORMATION.compile(hash),
            impl.isEmpty() ? null : body.build(),
            (req, res) -> {
                final DataObject obj = res.getObject();
                return obj.getBoolean("data");
            }
        );
    }

    @NotNull
    @Override
    public RestAction<Boolean> favoriteImage(@NotNull String hash) {
        Check.notBlank(hash, "hash");
        return new RestActionImpl<>(
            api,
            Route.ImageEndpoints.POST_IMAGE_FAVORITE.compile(hash),
            (req, res) -> {
                final DataObject obj = res.getObject();
                return obj.getString("data").equals("favorited");
            }
        );
    }
}
