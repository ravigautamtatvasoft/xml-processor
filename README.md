## Requirement

- Java 17
- Docker Desktop
- IntelliJ
- Postman

## Run the app

- Build the project
```sh
mvn clean package
```
- Build docker container
```sh
cd xml-processor
```
```sh
 docker-compose -f docker-compose.yaml up -d --build
```
## API Endpoints
- POST API: http://localhost:8080/api/processXml
    - in form-data add key "xmlFile" change type to file and attach the file.
    - xml will be processed.
````
curl --location 'http://localhost:8080/api/processXml' \
--form 'xmlFile=@"Epaper.xml"'
````

- GET API: http://localhost:8080/api/getEpapers
    - parameters for this API:
        - search: if need search data based on newspaper name or filename.
        - sortBy: need to sort by any specific column(By default: id)
        - order: true for Ascending | false for Descending order.
        - pageNo: starts from 0
        - pageSize: no of record need to get in result. 
        - fromDate: It's start date can be in long milliseconds format
        - toDate: It's end date can be in long milliseconds format
````
curl --location 'http://localhost:8080/api/getEpapers?search=null&sortBy=null&order=null&pageNo=null&pageSize=null&fromDate=null&toDate=null'
````