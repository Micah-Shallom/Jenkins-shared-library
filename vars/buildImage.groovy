#!/usr/bin/env groovy

def call(){
    echo "Building the docker image from the dockerfile"
    withCredentials([
        usernamePassword(credentialsId:'dockerhub-credentials',
            usernameVariable: "USER",
            passwordVariable: "PASSWD"
        )
    ]) {
        sh "docker build -t mshallom/java-app:jma-2.0 ."
        sh "echo $PASSWD | docker login -u $USER --password-stdin"
        sh "docker push mshallom/java-app:jma-2.0"
    }
}