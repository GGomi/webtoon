# JPA Note
---

- resources/META-INF/persistence.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence
 http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">

    <persistence-unit name="persistence">
        <class>com.essri.webtoonService.web.data.Toons</class>
        <properties>
            <property name="javax.persistence.jdbc.Driver" value="com.mysql.jdbc.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/webtoon" />
            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="root" />
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
            <property name="hibernate.show_sql" value="true" />
            <property name="hibernate.connection.useUnicode" value="true" />
            <property name="hibernate.connection.characterEncoding" value="UTF-8" />
        </properties>
    </persistence-unit>
</persistence>
```
- persistence-unit name은 ```Persistence.createEntityManagerFactory``` 생성할때 필요하다.



