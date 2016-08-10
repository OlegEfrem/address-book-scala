# Gumtree coding challenge
Complete assignment cand be found [here](https://github.com/gumtreeuk/address-book)

# Requirements
This is a scala sbt project, and was developed and tested to run with: Java 1.8, Scala 2.11.8 and Sbt 0.13.8

# Highlights
1. High level of abstraction with single Trait based point entries into the service/package. In this way it's easy to provide alternative implementations at any level and simple to unit test in isolation.
2. FileSystemRepo implementation of AddressBookRepository uses [scala-csv library](https://github.com/tototoshi/scala-csv) that parses Csv file from the provided source into a scala Stream, its elements being computed lazily, thus a stream can be infinitely long. To keep the lazy evaluation advantage all operations are performed using stream transformers which return another stream, and not using strict methods that are evaluated immediately and might cause OutOfMemoryError. More info on this can be found [Here](http://alvinalexander.com/scala/how-to-use-stream-class-lazy-list-scala-cookbook).
3. Code is tested at the following levels: Unit (having the repo mocked), Intergration (Accessing local system repo), Performance (using mocked data) and Manual via Application (reads the Csv from github [repo](https://github.com/gumtreeuk/address-book/blob/master/AddressBook)).
4. Operations are optimized for better performance, thus: search for the oldest person is done on par collection, while count by gender and age difference on sequential stream. Results on [performance test](https://github.com/OlegEfrem/address-book-scala/blob/master/src/test/scala/com/gumtree/addressbook/PerformanceTest.scala) can be found [here](https://github.com/OlegEfrem/address-book-scala/blob/master/src/test/resources/PerformanceTestResults.txt)
5. Because of number 4, number 3 is affected and not valid anymore for oldest person operation. 

# Application behavior
1. How many males are in the address book?
It will return:
 * the number of males in the repo;
 * zero if no males found or the repo is empty;
 
2. Who is the oldest person in the address book?
It Will return:
 * the oldest person by the number of days;
 * multiple persons if more with same amount of days exist;
 * empty list if repo is empty;
3. How many days older is Bill than Paul?
It will return: 
 * age in days of the person Bill minus age in days of person Paul;
 * last name is optional, meaning if it is present it will filter persons by FullName, if not only by the FirstName;
 * if FirstName is null or empty and IllegalArgumentException will be thrown;
 * if any of above the persons are not found a NotFoundDataException will be thrown;
 * if multiple persons with same name are found, the first from the list are picked up;