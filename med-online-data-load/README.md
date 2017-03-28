# Google-like Searches for Sites - Data Load (ETL) Project Part

* For context / details about this project see the Softera blog at: 
[https://www.softera.io/blog/march-2017/google-like-searches-for-sites](https://www.softera.io/blog/march-2017/google-like-searches-for-sites)
* This is a companion project of [Sample Web App](/med-online-webapp)

## Overview

* Read sample (test) data from CSV files and store into Elasticsearch indexes.
* There are two indexes - *products* and *keywords* - the first holds all product data for search and display while the latter is a special Completion index built for fast typeahead suggestions support.

## Project Setup

> This project is developed using Mule Studio 6.2 using embedded Mule Server 3.8.3 EE, and Elasticsearch 5.

> Download Studio if required from https://www.mulesoft.com/platform/studio

> Download Elasticsearch from https://www.elastic.co/products/elasticsearch

* Download / clone this project.
* Maven compile and generate Eclipse project artifacts to open inside the Studio e.g.
```
cd med-online-data-load
mvn eclipse:eclipse
mvn compile
```
* Open Anypoint Studio and import this project (*File->Import->General->Existing projects into workspace*). 
* Open Elasticsearch configuration *./config/elasticsearch.yml* and append below lines to enable CORS.
```
http.cors.enabled: true
http.cors.allow-origin: /https?:\/\/localhost(:[0-9]+)?/
```
* Start Elasticsearch server
```
$ES_HOME/bin/elasticsearch 
```      
* In the Package Explorer, right-click on the *project*, then select *Run As* > *Mule Application with Maven*. Studio runs the application on the embedded server. If all goes well you should see the indexes are created and sample data is populated. 
* Test using Browser or Curl as below to verify both indexes exist and have 100 items each.
```
curl -XGET 'http://localhost:9200/products/product/_count'
curl -XGET 'http://localhost:9200/keywords/keyword/_count'
```

Done!
