run:
	docker run -p 8888\:8080 \
	-d -v ${PWD}/target/quest-delta-1.0-SNAPSHOT\:/usr/local/tomcat/webapps/ROOT \
	--rm --name quest khmelov/quest
stop:
	docker stop quest
ps:
	docker container ps -all
images:
	docker images