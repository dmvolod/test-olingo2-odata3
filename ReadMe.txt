Test Camel Olingo2 component with OData 3.0 service
This test demoinstrates that current camel-olingo2 component 
doesn't support OData 3.0 compliant services

The error Error reading EDM: Invalid or missing namespace for 'Schema' 
means that OData schema 3.0 http://schemas.microsoft.com/ado/2009/11/edm 
is not defined inside Olingo2 library
===========================

To run the test use

    mvn clean test


For more help see the Apache Camel documentation

    http://camel.apache.org/

