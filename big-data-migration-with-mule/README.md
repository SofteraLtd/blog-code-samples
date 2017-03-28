# Big Data Migration with Mule

* For context / details about this project see the Softera blog at: 
[https://www.softera.io/blog/january-2017/big-data-migration-with-mule](https://www.softera.io/blog/january-2017/big-data-migration-with-mule)

>This project is developed using Mule Studio 6.2.1, with Mule Server 3.8.3 EE.

## Overview
* In this example, a MySQL database is used to read data. The MySQL script *mysql.sql* is available under *src/test/resources* folder.
* The MySql connection settings are configured in *mule-app.properties* file.
* The application is booted from the *main.xml* file which has a fixed-time scheduler set to tick once every 24 hours. 
* There are multiple batch files - one per Entity/Table. These files have the ETL logic for each job.
* The key here is to enable streaming while reading the data to ensure we don't run into memory issues with large/big volumes.
* DataWeave is used to transform / map the inbound messages to the outbound (Reservoir's) schema spec.
* The batch jobs use Mule's Batch Processing module, specifically designed for similar use cases!
* The main flow has a ScatterGather router that efficiently spin-up multiple parallel worker threads executing each batch job.

## Project Setup

* Download and setup AnypointStudio, MySQL Community Server and Apache Kafka using their respective quick start guides. 
* Download / clone this project.
* Maven compile the project e.g.
```
cd big-data-migration-with-mule
mvn compile
```
* Start MySQL server and create the sample Database using the *mysql.sql* script i.e.
```
mysql -u user -p < big-data-migration-with-mule/src/test/resources/mysql.sql
```
* Create Kafka Topic *reservoir_in*, typically using e.g.
```
$KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic reservoir_in
```      
* In the Package Explorer, right-click on the *project*, then select *Run As* > *Mule Application with Maven* . Studio runs the application on the embedded server. If all goes well you should see the processing kickstart straightaway.
* Run a Kafka Consumer to receive messages sent to the topic, e.g.
```
$KAFKA_HOME/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic reservoir_in --from-beginning
```

Enjoy!
