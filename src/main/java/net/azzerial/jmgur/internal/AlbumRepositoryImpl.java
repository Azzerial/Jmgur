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

import net.azzerial.jmgur.api.AlbumRepository;
import net.azzerial.jmgur.api.Jmgur;
import net.azzerial.jmgur.api.entities.Album;
import net.azzerial.jmgur.api.entities.Image;
import net.azzerial.jmgur.api.requests.restaction.RestAction;
import net.azzerial.jmgur.api.utils.data.DataArray;
import net.azzerial.jmgur.api.utils.data.DataObject;
import net.azzerial.jmgur.internal.entities.EntityBuilder;
import net.azzerial.jmgur.internal.requests.Route;
import net.azzerial.jmgur.internal.requests.restaction.RestActionImpl;
import net.azzerial.jmgur.internal.utils.Check;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlbumRepositoryImpl implements AlbumRepository {

    private final Jmgur api;

    /* Constructors */

    public AlbumRepositoryImpl(@NotNull Jmgur api) {
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
    public RestAction<Album> getAlbum(@NotNull String hash) {
        Check.notBlank(hash, "hash");
        return new RestActionImpl<>(
            api,
            Route.AlbumEndpoints.GET_ALBUM.compile(hash),
            (req, res) -> {
                final EntityBuilder builder = api.getEntityBuilder();
                final DataObject obj = res.getObject().getObject("data");
                return builder.createAlbum(obj);
            }
        );
    }

    @NotNull
    @Override
    public RestAction<List<Image>> getAlbumImages(@NotNull String hash) {
        Check.notBlank(hash, "hash");
        return new RestActionImpl<>(
            api,
            Route.AlbumEndpoints.GET_ALBUM_IMAGES.compile(hash),
            (req, res) -> {
                final EntityBuilder builder = api.getEntityBuilder();
                final DataArray arr = res.getObject().getArray("data");
                final List<Image> images = new ArrayList<>();

                for (int i = 0; i < arr.length(); i += 1) {
                    final DataObject imageObj = arr.getObject(i);
                    images.add(builder.createImage(imageObj));
                }
                return images;
            }
        );
    }

    @NotNull
    @Override
    public RestAction<Image> getAlbumImage(@NotNull String albumHash, @NotNull String imageHash) {
        Check.notBlank(albumHash, "albumHash");
        Check.notBlank(imageHash, "imageHash");
        return new RestActionImpl<>(
            api,
            Route.AlbumEndpoints.GET_ALBUM_IMAGE.compile(albumHash, imageHash),
            (req, res) -> {
                final EntityBuilder builder = api.getEntityBuilder();
                final DataObject obj = res.getObject().getObject("data");
                return builder.createImage(obj);
            }
        );
    }
}