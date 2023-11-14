package com.suslanium.filmus.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suslanium.filmus.domain.entity.user.UserProfile
import com.suslanium.filmus.domain.usecase.ChangeUserProfileUseCase
import com.suslanium.filmus.domain.usecase.GetUserProfileUseCase
import com.suslanium.filmus.domain.usecase.LogoutUseCase
import com.suslanium.filmus.domain.usecase.ValidateEmailUseCase
import com.suslanium.filmus.domain.usecase.ValidateNameUseCase
import com.suslanium.filmus.presentation.common.Constants
import com.suslanium.filmus.presentation.common.ErrorCodes
import com.suslanium.filmus.presentation.state.LogoutEvent
import com.suslanium.filmus.presentation.state.ProfileData
import com.suslanium.filmus.presentation.state.ProfileState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@OptIn(FlowPreview::class)
class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val changeUserProfileUseCase: ChangeUserProfileUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validateNameUseCase: ValidateNameUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val profileState: State<ProfileState>
        get() = _profileState
    private val _profileState = mutableStateOf<ProfileState>(ProfileState.Loading)

    val profileData: State<ProfileData>
        get() = _profileData
    private val _profileData = mutableStateOf(ProfileData())

    val avatarLink: StateFlow<String>
        get() = _avatarLinkFlow
    private val _avatarLinkFlow = MutableStateFlow(Constants.EMPTY_STRING)

    val isApplyingChanges: State<Boolean>
        get() = _isApplyingChanges
    private val _isApplyingChanges = mutableStateOf(false)

    private lateinit var unmodifiedProfile: UserProfile

    private val profileEditingExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is HttpException && throwable.code() == ErrorCodes.UNAUTHORIZED) {
            logout()
        } else {
            _profileState.value = ProfileState.Error
            _isApplyingChanges.value = false
        }
    }

    private val _logoutEventChannel = Channel<LogoutEvent>()
    val logoutEvents = _logoutEventChannel.receiveAsFlow()

    val canApplyChanges: Boolean
        get() = !profileIsNotModified && _profileData.value.emailValidationErrorType == null && _profileData.value.nameValidationErrorType == null

    private val profileIsNotModified: Boolean
        get() = _profileData.value.email == unmodifiedProfile.email && _avatarLinkFlow.value == unmodifiedProfile.avatarLink.orEmpty() && _profileData.value.name == unmodifiedProfile.name && _profileData.value.gender == unmodifiedProfile.gender && _profileData.value.birthDate == unmodifiedProfile.birthDate

    init {
        loadData()
        viewModelScope.launch {
            avatarLink.debounce(1000).collect {
                _profileData.value = _profileData.value.copy(avatarUri = it)
            }
        }
    }

    fun loadData() {
        _profileState.value = ProfileState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            //No exception handler here because changing state in exception handler sometimes causes crashes
            try {
                val profile = getUserProfileUseCase()
                unmodifiedProfile = profile
                withContext(Dispatchers.Main) {
                    _profileData.value = ProfileData(
                        id = profile.id,
                        nickName = profile.nickName.orEmpty(),
                        email = profile.email,
                        avatarUri = profile.avatarLink.orEmpty(),
                        name = profile.name,
                        birthDate = profile.birthDate,
                        gender = profile.gender
                    )
                    _avatarLinkFlow.value = profile.avatarLink.orEmpty()
                    _profileState.value = ProfileState.Content
                }
            } catch (e: Exception) {
                if (e is HttpException && e.code() == ErrorCodes.UNAUTHORIZED) {
                    logout()
                } else withContext(Dispatchers.Main) {
                    _profileState.value = ProfileState.Error
                }
            }
        }
    }

    fun setEmail(email: String) {
        val emailValidationResult = validateEmailUseCase(email)
        _profileData.value = _profileData.value.copy(
            email = email, emailValidationErrorType = emailValidationResult
        )
    }

    fun setName(name: String) {
        val nameValidationResult = validateNameUseCase(name)
        _profileData.value = _profileData.value.copy(
            name = name, nameValidationErrorType = nameValidationResult
        )
    }

    fun setGender(gender: Int) {
        _profileData.value = _profileData.value.copy(
            gender = gender
        )
    }

    fun setAvatarUri(avatarUri: String) {
        _avatarLinkFlow.value = avatarUri
    }

    fun setBirthDate(birthDate: Long?) {
        _profileData.value = _profileData.value.copy(birthDate = birthDate?.let {
            Instant.ofEpochMilli(it).atZone(
                ZoneId.of("UTC")
            ).toLocalDate()
        })
    }

    fun cancelChanges() {
        _profileData.value = ProfileData(
            id = unmodifiedProfile.id,
            nickName = unmodifiedProfile.nickName.orEmpty(),
            email = unmodifiedProfile.email,
            avatarUri = unmodifiedProfile.avatarLink.orEmpty(),
            name = unmodifiedProfile.name,
            birthDate = unmodifiedProfile.birthDate,
            gender = unmodifiedProfile.gender
        )
    }

    fun applyChanges() {
        _isApplyingChanges.value = true
        viewModelScope.launch(Dispatchers.IO + profileEditingExceptionHandler) {
            val user = UserProfile(
                id = _profileData.value.id,
                nickName = _profileData.value.nickName,
                email = _profileData.value.email,
                avatarLink = _avatarLinkFlow.value,
                name = _profileData.value.name,
                birthDate = _profileData.value.birthDate ?: LocalDate.now(),
                gender = _profileData.value.gender
            )
            changeUserProfileUseCase(
                user
            )
            unmodifiedProfile = user
            _isApplyingChanges.value = false
        }
    }

    fun logout() {
        _profileState.value = ProfileState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                logoutUseCase()
            } finally {
                withContext(Dispatchers.Main) {
                    _logoutEventChannel.send(LogoutEvent.Logout)
                }
            }
        }
    }

}