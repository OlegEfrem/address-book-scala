package com.gumtree.addressbook

class AddressBookException(message: String, cause: Throwable) extends Exception(message, cause) {
  def this(message: String) = this(message, null)

  def this(cause: Throwable) = this(null, cause)
}
