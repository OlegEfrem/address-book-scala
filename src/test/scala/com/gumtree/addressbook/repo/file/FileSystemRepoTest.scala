package com.gumtree.addressbook.repo.file

import com.gumtree.addressbook.UnitSpec
import com.gumtree.addressbook.model.Gender
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
      val expected = Stream(newPerson)
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

    "return 2 persons if there are 2 males in the repo" in {
      val expected = Stream(newPerson, newPerson)
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findBy(Gender.Male) shouldBe expected
    }

  }

  "findOldestPersons" should {
    "return 0 persons if no one is in the repo" in {
      when(dataParser.readPersons).thenReturn(Stream())
      fileSystemRepo.findOldestPersons shouldBe empty
    }

    "return 1 person if only 1 is in the repo" in {
      val expected = Stream(newPerson)
      when(dataParser.readPersons).thenReturn(expected)
      fileSystemRepo.findOldestPersons shouldBe expected
    }

    "return 2 persons if there are to multiple persons in the repo" in {
      val expected = Stream(newPerson(1000), newPerson(1000))
      val returnedStream = ((1 to 100).map(e => newPerson(e)) ++ Seq(newPerson(1000), newPerson(1000)) ++ (101 to 200).map(e => newPerson(e))).reverse.toStream
      when(dataParser.readPersons).thenReturn(returnedStream)
      fileSystemRepo.findOldestPersons shouldBe expected
    }

  }
}
