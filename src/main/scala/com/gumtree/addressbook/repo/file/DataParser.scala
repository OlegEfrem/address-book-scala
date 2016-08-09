package com.gumtree.addressbook.repo.file

import com.github.tototoshi.csv.CSVReader
import com.gumtree.addressbook.model.{DateOfBirth, Gender, Name, Person}
import scala.io.Source
import scala.util.{Failure, Success, Try}

trait DataParser {

  val readPersons: Stream[Person]

}

class CsvDataParser(source: Source) extends DataParser {
  private val nameColumnIndex = 0
  private val genderColumnIndex = 1
  private val dobColumnIndex = 2
  private val nameSeparator = " "
  private val firstNameIndex = 0
  private val lastNameIndex = 1

  override lazy val readPersons: Stream[Person] =
    CSVReader.open(source).toStream.map(mapRow)
/**
  * This is another way of handling exceptions in Scala, only for sample purposes,
  * mostly used to force client calls to handle the exception, in that case return
  * type should be changed to Try[Person]
  * */
  private def mapRow(columns: List[String]): Person = {
    Try {
      require(columns.size == 3 && columns.forall(_.nonEmpty), s"Columns: $columns must have 3 non empty elements")
      val name = parseName(columns.apply(nameColumnIndex).trim)
      val gender = Gender.withName(columns.apply(genderColumnIndex).trim)
      val date = DateOfBirth.from(columns.apply(dobColumnIndex).trim)
      Person(name, gender, date)
    } match {
      case Success(person) => person
      case Failure(err) => throw new DataParserException(s"Non parsable csv line: $columns", err)
    }

  }


  private def parseName(str: String): Name = {
    val splitStr = str.split(nameSeparator)
    require(splitStr.size == 2 && splitStr.forall(_.nonEmpty), s"String representing name: $str should have two parts split by separator: {$nameColumnIndex}")
    Name(splitStr.apply(firstNameIndex), Some(splitStr.apply(lastNameIndex)))
  }

}
