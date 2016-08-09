package com.gumtree.addressbook.model

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import com.gumtree.addressbook.helpers.TestData
import org.scalatest.{Matchers, WordSpec}

class PersonTest extends WordSpec with Matchers with TestData {

  "ageDays" should {

    "return 10000 for a person of this age in days" in {
      val person = Person(newName, Gender.Male, DateOfBirth(LocalDate.now().minus(10000, ChronoUnit.DAYS)))
      person.ageDays shouldBe 10000
    }

  }

  "ageYears" should {

    "return 10 years for a person of this age in years" in {
      val person = Person(newName, Gender.Male, DateOfBirth(LocalDate.now().minus(10, ChronoUnit.YEARS)))
      person.ageYears shouldBe 10
    }

  }
}
