# SweetyAppDemo
Using ANT to build the project.
1) Make sure JDK 1.8 and ANT are installed and JAVA_HOME and ANT_HOME are set.
2) Make sure MyLib contains all(16 jar files) dependencies.
3)Installation and configuration of Postgre 9.3
Install Postgres 9.3. Add a new  PATH variable <Posgre Home directory>\bin
4) Initialize Database parameters in web.xml.  Enter Postgre Database URL,UserName and Password.
Currently all values set to default in web.xml. Default port- 5433 with default login/password as 
postgres. Below are the contents in web.xml
<context-param>
    <param-name>DBUrl</param-name>
    <param-value>jdbc:postgresql://localhost:5433/SweetyDBDB</param-value>
    </context-param>
    <context-param>
    <param-name>DBUserName</param-name>
    <param-value>postgres</param-value>
    </context-param>
    <context-param>
    <param-name>DBPassword</param-name>
    <param-value>postgres</param-value>
    </context-param>
5) Create an empty database using following command from command prompt
>createdb -T template0 -h localhost -p 5433 SweetyDB
6)Restore the  database from the given sql dump:
SweetyDB can be restored using  the command- 
psql -U postgres -h localhost -p 5433 SweetyDB < SweetyDBDump.sql

7) Go to root(SweetyAppDemo) directory and type "ant clean" and then "ant"
8) war file will be created in dist folder: SweetyApp.war
7)Deploy the war file in Tomcat.
8) Browse http://localhost://SweetyApp
9) Two Users- DemoUser1(password as DemoUser1) and DemoUser2(password as DemoUser2) are already added in userinfo table.


