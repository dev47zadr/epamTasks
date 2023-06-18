FROM openjdk:17
COPY ./out/production/epamTasks/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","com.my.task1.App"]