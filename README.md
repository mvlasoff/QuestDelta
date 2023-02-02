# Mike Vlasoff, Quest Delta

This is pet project website. It has 2 quests out of the box.
1. Create MySQL database schema questdelta. url: //localhost:3306/questdelta, username: root, password: root.
2. Use Tomcat 10.
3. Start URL: http://localhost:8080/start
4. Artifact: quest-delta:war exploded
5. Application context: /

App will automatically deploy tables for its work if they don't exist.

When website is started you need to log in or register own user before start playing. 
There are 3 pre-registered users (login/password/role): 
admin/admin/ADMIN, user/12345/USER, guest/00000/GUEST.
Only users with USER or ADMIN role can play and check statistics.

Tools used: Java 17, Intellij IDEA U, MySQL, Hibernate, Liquibase, JUnit 5, Log4j2.