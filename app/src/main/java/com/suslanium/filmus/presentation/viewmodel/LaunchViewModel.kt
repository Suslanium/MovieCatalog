package com.suslanium.filmus.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.domain.usecase.CheckTokenExistenceUseCase
import com.suslanium.filmus.presentation.state.LaunchEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LaunchViewModel(
    private val checkTokenExistenceUseCase: CheckTokenExistenceUseCase
): ViewModel() {

    private val _launchEventChannel = Channel<LaunchEvent>()
    val launchEvents = _launchEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val hasToken = checkTokenExistenceUseCase()
            withContext(Dispatchers.Main) {
                if (hasToken) {
                    _launchEventChannel.send(LaunchEvent.Authorized)
                } else {
                    _launchEventChannel.send(LaunchEvent.Unauthorized)
                }
            }
        }
    }

}