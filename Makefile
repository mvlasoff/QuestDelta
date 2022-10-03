r:
	docker run -d -p 8888\:8080 -v /Users/akhmelev/IdeaProjects/QuestDelta/target/quest-delta-1.0-SNAPSHOT\:/usr/local/tomcat/webapps/ROOT --rm --name quest khmelov/quest
s:
	docker stop quest
a:
	docker container ps -all
i:
	docker images