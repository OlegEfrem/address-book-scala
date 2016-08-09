package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.UnitSpec
import com.gumtree.addressbook.model.{Gender, Name}
import org.mockito.Mockito._

class FileSystemRepoTest extends UnitSpec {

  val dataParser = mock[DataParser]

  def fileSystemRepo = new FileSystemRepo(dataParser)

  "findBy(Gender)" should {

    "return 0 persons if no males are in the repo" in {
      when(dataParser.readPersons).thenReturn(Stream())
      fileSystemRepo.findBy(Gender.Male) shouldBe empty
    }

    "return 1 person if 1 male is in the repo" in {
      val expected = Stream(newPerson())
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

    "return 2 persons if there are 2 males in the repo" in {
      val expected = Stream(newPerson(), newPerson())
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

  }

  "findBy(Name)" should {

    "return 0 persons if no persons are in the repo" in {
      when(dataParser.readPersons).thenReturn(Stream())
      fileSystemRepo.findBy(Name("SomeName")) shouldBe empty
    }

    "return 0 person if there are no matching names in the repo" in {
      val searchName = Name("ExpectedName")
      val expectedPerson = newPerson(searchName)
      val returnedStream = Stream(newPerson(), expectedPerson, newPerson(), expectedPerson)
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findBy(Name("NotMatchingName")) shouldBe empty
    }

    "return 1 person if only 1 person with given First Name is found in the repo" in {
      val searchName = Name("ExpectedName")
      val expectedPerson = newPerson(searchName)
      val returnedStream = Stream(newPerson(), expectedPerson, newPerson())
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findBy(searchName) shouldBe Stream(expectedPerson)
    }

    "return 2 persons if two with given First Name are found in the repo" in {
      val searchName = Name("ExpectedName")
      val expectedPerson = newPerson(searchName)
      val returnedStream = Stream(newPerson(), expectedPerson, newPerson(), expectedPerson)
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findBy(searchName) shouldBe Stream(expectedPerson, expectedPerson)
    }

    "return a person matching Full Name" in {
      val searchName = Name("ExpectedName", Some("ExpectedLastName"))
      val expectedPerson = newPerson(searchName)
      val returnedStream = Stream(newPerson(), expectedPerson, newPerson())
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findBy(searchName) shouldBe Stream(expectedPerson)
    }

  }

  "findOldestPersons" should {
    "return 0 persons if no one is in the repo" in {
      when(dataParser.readPersons).thenReturn(Stream())
      fileSystemRepo.findOldestPersons shouldBe empty
    }

    "return 1 person if only 1 is in the repo" in {
      val expected = Stream(newPerson())
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findOldestPersons shouldBe expected
    }

    "return 2 persons if there are to multiple persons in the repo" in {
      val expected = Stream(newPerson(minusDays = 1000), newPerson(minusDays = 1000))
      val returnedStream = ((1 to 100).map(e => newPerson(minusDays = e)) ++ Seq(newPerson(minusDays = 1000), newPerson(minusDays = 1000)) ++ (101 to 200).map(e => newPerson(minusDays = e))).reverse.toStream
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findOldestPersons shouldBe expected
    }

  }
}
