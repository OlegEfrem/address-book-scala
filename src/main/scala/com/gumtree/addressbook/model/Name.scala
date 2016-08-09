package com.gumtree.addressbook.model

case class Name(first: String, last: Option[String] = None) {
  override def toString: String = s"$first ${last.getOrElse("")}".trim
}