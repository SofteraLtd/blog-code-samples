#Sending legacy data from RDBMS to Big Data Reservoir typically using Kafka Topic for data ingestion 

>This project us developed using Mule Studio 6.2.1, with Mule Server 3.8.3 EE (embedded/part of Studio installation).

##Project Overview
* In this example, a MySQL database is used to read data. The MySQL script *mysql.sql* is available under *src/test/resources* folder.
* The MySql connection settings are configured in *mule-app.properties* file.
* The application is booted from the *main.xml* file which has a fixed-time scheduler set to tick once every 24 hours. 
* There are multiple batch files - one per Entity/Table. These files have the ETL logic for each job.
* The highlights here are to enable streaming while reading data to ensure we don't run into memory issues with large/big volumes.
* DataWeave is used for the transformation / mapping of inbound message to the outbound schema's spec.
* The entire jobs make use of Mule's Batch Processing module, specifically built for this sort of use case.
* The main flow has a ScatterGather router to efficiently spin multiple parallel workers dedicated on each batch job.

##Set Up and Run the Example Locally

0. Download / setup AnypointStudio, MySQL Community Server and Apache Kafka locally. 
1. Download / clone this project from the Git repo.
2. Start MySQL server and create the sample Database using *mysql.sql* script.
3. Create Kafka Topic *reservoir_in*, typically using e.g.
```
$KAFKA_HOME/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic reservoir_in
```       
4. In the Package Explorer, right-click on the project, then select Run As > Mule Application or Mule Application with Maven . Studio runs the application on the embedded server. If all goes well you should see the processing kickstart straightaway.
5. Run a Kafka Consumer to receive messages sent to the topic, e.g.
```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic reservoir_in --from-beginning
```