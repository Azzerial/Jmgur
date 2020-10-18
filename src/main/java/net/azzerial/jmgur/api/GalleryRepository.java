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

import net.azzerial.jmgur.api.entities.*;
import net.azzerial.jmgur.api.entities.dto.GalleryDTO;
import net.azzerial.jmgur.api.entities.dto.GallerySearchDTO;
import net.azzerial.jmgur.api.entities.dto.GalleryShareDTO;
import net.azzerial.jmgur.api.entities.subentities.CommentSort;
import net.azzerial.jmgur.api.entities.subentities.ReportReason;
import net.azzerial.jmgur.api.entities.subentities.Vote;
import net.azzerial.jmgur.api.requests.restaction.PagedRestAction;
import net.azzerial.jmgur.api.requests.restaction.RestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface GalleryRepository {

    @NotNull
    Jmgur getApi();

    /* --- Resources --- */

    @NotNull
    default PagedRestAction<List<GalleryElement>> getGallery() {
        return getGallery(GalleryDTO.create());
    }

    @NotNull
    PagedRestAction<List<GalleryElement>> getGallery(@NotNull GalleryDTO dto);

    @NotNull
    default PagedRestAction<List<GalleryElement>> searchGallery(@NotNull String query) {
        return searchGallery(GallerySearchDTO.create().query(query));
    }

    @NotNull
    PagedRestAction<List<GalleryElement>> searchGallery(@NotNull GallerySearchDTO dto);

    @NotNull
    RestAction<GalleryAlbum> getGalleryAlbum(@NotNull String hash);

    @NotNull
    RestAction<GalleryImage> getGalleryImage(@NotNull String hash);

    /* Sharing */

    @NotNull
    RestAction<Boolean> shareImage(@NotNull String hash, @NotNull GalleryShareDTO dto);

    @NotNull
    RestAction<Boolean> shareAlbum(@NotNull String hash, @NotNull GalleryShareDTO dto);

    @NotNull
    RestAction<Boolean> removeFromGallery(@NotNull String hash);

    /* Actions */

    @NotNull
    default RestAction<Boolean> reportGalleryElement(@NotNull String hash) {
        return reportGalleryElement(hash, null);
    }

    @NotNull
    RestAction<Boolean> reportGalleryElement(@NotNull String hash, @Nullable ReportReason reason);

    @NotNull
    RestAction<Votes> getGalleryElementVotes(@NotNull String hash);

    @NotNull
    RestAction<Boolean> voteForGalleryElement(@NotNull String hash, @NotNull Vote vote);

    /* Comments */

    @NotNull
    default RestAction<List<Comment>> getGalleryElementComments(@NotNull String hash) {
        return getGalleryElementComments(hash, CommentSort.BEST);
    }

    @NotNull
    RestAction<List<Comment>> getGalleryElementComments(@NotNull String hash, @NotNull CommentSort sort);

    @NotNull
    RestAction<Comment> getGalleryElementComment(@NotNull String hash, long id);
}
