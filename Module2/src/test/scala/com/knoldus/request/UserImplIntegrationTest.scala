package com.knoldus.request

import com.knoldus.db.CompanyReadDto
import com.knoldus.models.User
import com.knoldus.validator.{EmailValidator, UserValidator}
import org.scalatest.flatspec.AnyFlatSpec

class UserImplIntegrationTest extends AnyFlatSpec {

  val companyName = new CompanyReadDto
  val validateEmail = new EmailValidator
  val userValidator = new UserValidator(companyName,validateEmail)

  val userImpl = new UserImpl(userValidator)

  "User" should "not be created as company does not exists in DB" in {
    val ramUser: User = User("Ram","Raj",22,"Google","ram.raj@gmail.com")

    val result = userImpl.createUser(ramUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as email id is not valid" in {
    val amitUser: User = User("Amit","Rai",30,"Knoldus","amit.rai@knoldus")

    val result = userImpl.createUser(amitUser)
    assert(result.isEmpty)
  }

  "User" should "not be created as company does not exists in DB and email id is not valid" in {
    val riyaUser: User = User("Riya","Singh",23,"Microsoft","riya.singh@microsoft")

    val result = userImpl.createUser(riyaUser)
    assert(result.isEmpty)
  }

  "User" should "be created" in {
    val sakshiUser: User = User("Sakshi","Sneha",22,"Knoldus","sakshi.sneha@knoldus.com")

    val result = userImpl.createUser(sakshiUser)
    assert(!result.isEmpty)
  }

}