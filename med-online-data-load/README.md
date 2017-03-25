# Data Load (ETL) project for [Med Online Webapp](/med-online-webapp)

* Read sample (test) data from a CSV file, map and store into Elasticsearch indexes.
* There are two indexes - *products* and *keywords* - the first holds all product data and latter is a special index built-in with fast typeahead suggestions support.

> This project is developed using Mule Studio 6.2 using embedded Mule Server 3.8.3 EE, and Elasticsearch 5.
> Download Studio if required from https://www.mulesoft.com/platform/studio
> Download Elasticsearch from https://www.elastic.co/products/elasticsearch

## To run

* Download / clone this project.
* Maven compile and generate Eclipse project artifacts to open inside the Studio e.g.
```
cd med-online-data-load
mvn eclipse:eclipse
mvn compile
```
* Open Anypoint Studio and import this project (*File->Import->General->Existing projects into workspace*). 
* Open Elasticsearch configuration *./config/elasticsearch.yml* and append below lines to enable CORS
```
http.cors.enabled: true
http.cors.allow-origin: /https?:\/\/localhost(:[0-9]+)?/
```
* Start ElasticSearch server
```
$ES_HOME/bin/elasticsearch 
```      
* In the Package Explorer, right-click on the *project*, then select *Run As* > *Mule Application with Maven* . Studio runs the application on the embedded server. If all goes well you should see the indexes are created and sample data is populated. 
* Test using Browser or Curl as below to verify both indexes exist and have 100 items in each
```
curl -XGET 'http://localhost:9200/products/product/_count'
curl -XGET 'http://localhost:9200/keywords/keyword/_count'
```

Done!
