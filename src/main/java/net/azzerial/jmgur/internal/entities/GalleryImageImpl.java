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
import net.azzerial.jmgur.api.entities.GalleryImage;
import net.azzerial.jmgur.api.entities.subentities.Vote;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

import static net.azzerial.jmgur.internal.utils.Helper.print;

@Setter
public final class GalleryImageImpl implements GalleryImage {

    private final transient Jmgur api;

    private String id;
    private String title;
    private String description;
    private OffsetDateTime datetime;
    private String type;
    private boolean animated;
    private int width;
    private int height;
    private int size;
    private int views;
    private int bandwidth;
    private Vote vote;
    private boolean favorite;
    private boolean nsfw;
    private String section;
    private String accountUrl;
    private long accountId;
    private boolean inMostViral;
    private boolean hasSound;
    private int edited;
    private boolean inGallery;
    private String link;
    private String mp4;
    private String gifv;
    private String hls;
    private int mp4Size;
    private boolean looping;
    private int commentCount;
    private int favoriteCount;
    private int ups;
    private int downs;
    private int points;
    private int score;
    private boolean isAlbum;

    /* Constructors */

    GalleryImageImpl(@NotNull Jmgur api) {
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
    public String getHash() {
        return id;
    }

    @Nullable
    @Override
    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public String getDescription() {
        return description;
    }

    @NotNull
    @Override
    public OffsetDateTime getCreationDate() {
        return datetime;
    }

    @NotNull
    @Override
    public String getMimeType() {
        return type;
    }

    @Override
    public boolean isAnimated() {
        return animated;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getViews() {
        return views;
    }

    @Override
    public int getBandwidth() {
        return bandwidth;
    }

    @Nullable
    @Override
    public Vote getVote() {
        return vote;
    }

    @Override
    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public boolean isNSFW() {
        return nsfw;
    }

    @Nullable
    @Override
    public String getSection() {
        return section;
    }

    @Nullable
    @Override
    public String getAuthorName() {
        return accountUrl;
    }

    @Override
    public long getAuthorIdLong() {
        return accountId;
    }

    @Override
    public boolean isInMostViral() {
        return inMostViral;
    }

    @Override
    public boolean hasSound() {
        return hasSound;
    }

    @Override
    public int getEditCount() {
        return edited;
    }

    @Override
    public boolean isInGallery() {
        return inGallery;
    }

    @NotNull
    @Override
    public String getUrl() {
        return link;
    }

    @Nullable
    @Override
    public String getMp4Url() {
        return mp4;
    }

    @Nullable
    @Override
    public String getGifvUrl() {
        return gifv;
    }

    @Nullable
    @Override
    public String getHlsUrl() {
        return hls;
    }

    @Override
    public int getMp4Size() {
        return mp4Size;
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public int getCommentCount() {
        return commentCount;
    }

    @Override
    public int getFavoriteCount() {
        return favoriteCount;
    }

    @Override
    public int getUps() {
        return ups;
    }

    @Override
    public int getDowns() {
        return downs;
    }

    @Override
    public int getPoints() {
        return points;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean isAlbum() {
        return isAlbum;
    }

    /* Methods */

    @Override
    public String toString() {
        return "GalleryImage{" +
            "hash=" + print(id) +
            ", title=" + print(title) +
            ", description=" + print(description) +
            ", creationDate=" + datetime +
            ", mimiType=" + print(type) +
            ", animated=" + animated +
            ", width=" + width +
            ", height=" + height +
            ", size=" + size +
            ", views=" + views +
            ", bandwidth=" + bandwidth +
            ", vote=" + vote +
            ", favorite=" + favorite +
            ", nsfw=" + nsfw +
            ", section=" + print(section) +
            ", authorName=" + print(accountUrl) +
            ", authorId=" + accountId +
            ", inMostViral=" + inMostViral +
            ", hasSound=" + hasSound +
            ", editCount=" + edited +
            ", inGallery=" + inGallery +
            ", url=" + print(link) +
            ", mp4Url=" + print(mp4) +
            ", gifvUrl=" + print(gifv) +
            ", hlsUrl=" + print(hls) +
            ", mp4Size=" + mp4Size +
            ", looping=" + looping +
            ", commentCount=" + commentCount +
            ", favoriteCount=" + favoriteCount +
            ", ups=" + ups +
            ", downs=" + downs +
            ", points=" + points +
            ", score=" + score +
            '}';
    }
}