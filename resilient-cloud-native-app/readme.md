# Cloud-native Application Design

* For context and details about this project see the Softera blog at: 
[https://www.softera.io/blog/april-2017/cloud-native-application-design](https://www.softera.io/blog/april-2017/cloud-native-application-design)

## Project Setup
> Pre-req: Requires JDK 8 or higher installed on the host

1) Open a command prompt in the project's root directory (resilient-cloud-native-app)

2) Type: `mvn spring-boot:run`
    This installs the dependencies as defined in the pom.xml file.
    
3) Test with Curl: `curl -XPOST 'localhost:8080/api/v1/invoices?accountId=2&orderId=1'`
    This calls the REST API to create an invoice object.
    See accompanied blog post to learn more about the sample project.
