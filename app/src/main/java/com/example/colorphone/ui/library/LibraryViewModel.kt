package com.example.colorphone.ui.library

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorphone.domain.model.LibraryItem
import com.example.colorphone.domain.model.LibraryItemType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LibraryViewModel @Inject constructor() : ViewModel() {

    private val allItems = listOf(
        LibraryItem.Artist("1", "Conan Gray", null),
        LibraryItem.Playlist("2", "3:00am vibes", "18 songs", null),
        LibraryItem.Album("3", "Wiped Out!", "The Neighbourhood", null),
        LibraryItem.Album("4", "Extra Dynamic", "Updated Aug 10 • ur mom ashley", null),
        LibraryItem.Folder("5", "moods", 11),
        LibraryItem.Folder("6", "blends", 8),
        LibraryItem.Folder("7", "favs", 14),
        LibraryItem.Folder("8", "random?", 10),
        LibraryItem.Playlist("9", "Superache", "Conan Gray", null),
        LibraryItem.Album("10", "DAWN FM", "The Weeknd", null),
        LibraryItem.Album("11", "Planet Her", "Doja Cat", null),
        LibraryItem.Podcast("12", "Anything Goes", "Updated Aug 31 • Emma Chamberlain", null),
        LibraryItem.Podcast("13", "Ask Me Another", "Updated Aug 18 • NPR Studios", null)
    )

    private val _libraryItems = MutableStateFlow<List<LibraryItem>>(allItems)
    val libraryItems: StateFlow<List<LibraryItem>> = _libraryItems.asStateFlow()

    private val _currentFilter = MutableStateFlow<LibraryItemType?>(null)
    val currentFilter: StateFlow<LibraryItemType?> = _currentFilter.asStateFlow()

    fun setFilter(type: LibraryItemType?) {
        _currentFilter.value = type
        filterItems()
    }

    private fun filterItems() {
        viewModelScope.launch {
            val filter = _currentFilter.value
            if (filter == null) {
                _libraryItems.value = allItems
            } else {
                _libraryItems.value = allItems.filter { it.type == filter }
            }
        }
    }
}
