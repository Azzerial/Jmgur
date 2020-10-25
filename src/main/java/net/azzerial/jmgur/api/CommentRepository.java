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

import net.azzerial.jmgur.api.entities.Comment;
import net.azzerial.jmgur.api.entities.dto.CommentInformationDTO;
import net.azzerial.jmgur.api.entities.subentities.ReportReason;
import net.azzerial.jmgur.api.entities.subentities.Vote;
import net.azzerial.jmgur.api.requests.restaction.RestAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommentRepository {

    @NotNull
    Jmgur getApi();

    /* --- Core --- */

    @NotNull
    RestAction<Comment> getComment(long id);

    @NotNull
    default RestAction<Long> postComment(@NotNull String hash, @NotNull String content) {
        return postComment(CommentInformationDTO.create().setPostHash(hash).setContent(content));
    }

    @NotNull
    RestAction<Long> postComment(@NotNull CommentInformationDTO dto);

    @NotNull
    RestAction<Boolean> deleteComment(long id);

    @NotNull
    RestAction<Comment> getCommentWithReplies(long id);

    @NotNull
    default RestAction<Long> replyToComment(@NotNull String hash, long id, @NotNull String content) {
        return postComment(CommentInformationDTO.create().setPostHash(hash).setParentId(id).setContent(content));
    }

    @NotNull
    RestAction<Long> replyToComment(@NotNull CommentInformationDTO dto);

    @NotNull
    RestAction<Boolean> updateCommentVote(long id, @NotNull Vote vote);

    @NotNull
    default RestAction<Boolean> reportComment(long id) {
        return reportComment(id, null);
    }

    @NotNull
    RestAction<Boolean> reportComment(long id, @Nullable ReportReason reason);
}
