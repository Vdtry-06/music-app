package com.example.colorphone.domain.model

sealed class LibraryItem(
    open val id: String,
    open val title: String,
    open val subtitle: String,
    open val imageUrl: String? = null,
    open val type: LibraryItemType
) {
    data class Playlist(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val imageUrl: String? = null
    ) : LibraryItem(id, title, subtitle, imageUrl, LibraryItemType.PLAYLIST)

    data class Artist(
        override val id: String,
        override val title: String,
        override val imageUrl: String? = null
    ) : LibraryItem(id, title, "", imageUrl, LibraryItemType.ARTIST)

    data class Album(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val imageUrl: String? = null
    ) : LibraryItem(id, title, subtitle, imageUrl, LibraryItemType.ALBUM)

    data class Folder(
        override val id: String,
        override val title: String,
        val playlistCount: Int
    ) : LibraryItem(id, title, "$playlistCount playlists", null, LibraryItemType.FOLDER)

    data class Podcast(
        override val id: String,
        override val title: String,
        override val subtitle: String,
        override val imageUrl: String? = null
    ) : LibraryItem(id, title, subtitle, imageUrl, LibraryItemType.PODCAST)
}

enum class LibraryItemType {
    PLAYLIST, ARTIST, ALBUM, FOLDER, PODCAST
}
