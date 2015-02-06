# org.lappsgrid.core

### Build Status

[![Master Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.core?branch=master)](https://travis-ci.org/lapps/org.lappsgrid.core)
[![Develop Status](http://grid.anc.org:9080/travis/svg/lapps/org.lappsgrid.core?branch=develop)](https://travis-ci.org/lapps/org.lappsgrid.core)

Currently the `core` module only provides the `DataFactory` class that is used
to create the JSON representations of common LAPPS objects.

```java
    String json = DataFactory.error("Fatal error encountered.");
    ...
    DataSource source = ...
    String json = source.execute(DataFactory.get(documentId));
```

## Maven

```xml
<dependency>
	<groupId>org.lappsgrid</groupId>
	<artifactId>core</artifactId>
	<version>2.0.0</version>
</dependency>
```
