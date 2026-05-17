package com.demonlab.lune.ui

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
}

sealed class LibraryState {
    object Loading : LibraryState()
    data class Success(val songs: List<com.demonlab.lune.data.entity.SongOverride>) : LibraryState()
    data class Error(val message: String) : LibraryState()
}
