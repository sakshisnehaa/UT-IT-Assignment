package com.knoldus.request
import com.knoldus.models.User
import com.knoldus.validator.UserValidator
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplUnitTest extends AnyFlatSpec {

  val mockedUserValidator = mock[UserValidator]
  val sakshiUser: User = User("Sakshi","Sneha",22,"knoldus","sakshi.sneha@knoldus.com")

  "User" should "be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(sakshiUser)) thenReturn(true)
    val result = userImpl.createUser(sakshiUser)
    assert(!result.isEmpty)
  }

  "User" should "not be created" in {
    val userImpl = new UserImpl(mockedUserValidator)

    when(mockedUserValidator.userIsValid(sakshiUser)) thenReturn(false)
    val result = userImpl.createUser(sakshiUser)
    assert(result.isEmpty)
  }

}