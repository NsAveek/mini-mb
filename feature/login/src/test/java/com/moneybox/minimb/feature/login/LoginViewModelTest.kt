package com.moneybox.minimb.feature.login

import android.content.SharedPreferences
import android.text.TextUtils
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moneybox.minimb.feature.login.data.LoginRepository
import com.moneybox.minimb.feature.login.domain.models.LoginResponse
import com.moneybox.minimb.feature.login.domain.models.SessionDataResponse
import com.moneybox.minimb.feature.login.domain.models.UserResponse
import com.moneybox.minimb.feature.login.presentation.login.LoginViewModel
import com.moneybox.minimb.network.ApiResponseResult
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @Mock
    lateinit var loginRepository : LoginRepository
    @Mock
    lateinit var sharedPreferences: SharedPreferences
    @Mock
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor
    @Mock
    lateinit var loginResponse: LoginResponse
    @Mock
    lateinit var sessionDataResponse: SessionDataResponse
    @Mock
    lateinit var userResponse: UserResponse

    lateinit var loginViewModel : LoginViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val email = "jaeren+androidtest@moneyboxapp.com"
    private val password = "P455word12"

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(loginRepository, sharedPreferences)

        loginResponse = LoginResponse(sessionDataResponse, userResponse)

    }
    @Test
    fun `empty email and password fields should return false`(){
        loginViewModel.email.value = ""
        loginViewModel.password.value = ""

        assertFalse(loginViewModel.isValid())
    }
    @Test
    fun `empty email field should return false`(){
        loginViewModel.email.value = ""
        loginViewModel.password.value = password

        assertFalse(loginViewModel.isValid())
    }
    @Test
    fun `empty password field should return false`(){
        loginViewModel.email.value = email
        loginViewModel.password.value = ""

        assertFalse(loginViewModel.isValid())
    }
    @Test
    fun `Non empty email and password fields should return true`(){
        loginViewModel.email.value = email
        loginViewModel.password.value = password

        assertTrue(loginViewModel.isValid())
    }
    @Test
    fun `Non empty Login Response should return loginResultStored as True`(){
        Mockito.`when`(sharedPreferences.edit()).thenReturn(sharedPreferencesEditor)
        with(loginViewModel){
            storeLoginResponse(loginResponse).apply {
                assertTrue(loginViewModel.loginResultStored.value!!)
            }
        }
    }
    @Test
    fun `Unauthorized credentials should return error`() =
        coroutinesTestRule.testDispatcher.runBlockingTest {

            val userMap = HashMap<String,String>().apply {
                put(email, "")
                put(password, "")
            }
            Mockito.`when`(loginRepository.login(userMap)).thenReturn(flowOf(ApiResponseResult.success(loginResponse)))
            with(loginViewModel){
                login()
                Assert.assertNull(loginResult.value)
//                Assert.assertEquals(ApiResponseResult.Status.LOADING, loginResult.value!!.status)
            }
    }
}