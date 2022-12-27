# HealthTrio
Coding Exercise

## Meaningful Use of CEHRT by State in 2014
This application connects to the Centers for Medicare and Medicaid Services (CMS) EHR Incentive Program Measures API (https://www.healthit.gov/data/open-api?source=Meaningful-Use-Acceleration-Scorecard.csv&period=2014), retrieves data on the percentage of eligible and critical access hospitals that have demonstrated Meaningful Use of Certified Electronic Health Record Technology (CEHRT) in the year 2014, and prints the percentage results in descending order along with corresponding states.

### Prerequisites
* Java 11 or later
* Maven 
### Dependencies & Plugins
This application uses the following **Maven dependencies:** `junit`, `jackson-databind`, and `mockito-core`<br>
Copy and paste below lines to your project in your `pom.xml` file under the `<dependencies>` element.
```pom.xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13.2</version>
  <scope>test</scope>
</dependency>
<dependency>
  <groupId>com.fasterxml.jackson.core</groupId>
  <artifactId>jackson-databind</artifactId>
  <version>2.14.0</version>
</dependency>
<dependency>
  <groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>4.10.0</version>
</dependency>
```


Add following maven plugins: `maven-shade-plugin` for packaging single jar file with dependencies and `maven-jar-plugin` to specify the main class of the application<br> 
Copy and paste below lines to your project in your `pom.xml` file under the `<plugins>` element. 
```pom.xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-shade-plugin</artifactId>
  <version>3.4.1</version>
  <configuration>
    <finalName>${artifactId}-${version}-with-dependencies</finalName>
  </configuration>
  <executions>
    <execution>
      <phase>package</phase>
      <goals>
        <goal>shade</goal>
      </goals>
    </execution>
  </executions>
</plugin>
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-jar-plugin</artifactId>
  <configuration>
    <archive>
      <manifest>
        <addClasspath>true</addClasspath>
        <mainClass>com.healthtrio.codingexercise.App</mainClass>
      </manifest>
    </archive>
  </configuration>
</plugin>
```

___
## Building and Running the App
To build and run the app, follow these steps:

1. Clone the repository:
```
git clone https://github.com/smashyou/healthtrio.git
```
2. Navigate to the root directory of the project:
```
cd healthtrio
```
3. Build the project using Maven:
```
mvn package
```
4. Execute the app:
```
java -cp target/healthtrio-1.0.1-with-dependencies.jar com.healthtrio.codingexercise.App
```
The app will connect to the API, retrieve the data, and print the results as described above.
___
## Containerizing the App (How I did it)
Docker is used to containerize this app using following `Dockerfile`
```Dockerfile
FROM openjdk:19
COPY target/healthtrio-1.0.1-with-dependencies.jar healthtrioApp.jar
ENTRYPOINT ["java","-jar","healthtrioApp.jar"]
```
To build the Docker image, run the following command in the root directory of the project:
```
docker build -t healthtrio-image .
```
This will build a Docker image with the name "healthtrio-image" based on the instructions in the `Dockerfile` located in the current directory (indicated by the `.` at the end of the command).

___
## Pull Docker image & Run containerized application

Pull the Docker image of this docker containerized application ([link to my Docker hub repo](https://hub.docker.com/repositories/smashyou)) by running following command: 
```
docker pull smashyou/healthtrio:<tag name>
```
Once you have done pulling from the repo, you can run the following command to start a container based on the "healthtrio-image" image:
```
docker run -p 8080:8080 healthtrio-image:<tag name>
```
Docker will always apply the latest by default if `tag name` if left out. Current `smashyou/healthtrio` repo has `1.0.0` & `1.0.1` version tags
