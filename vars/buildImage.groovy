#!/usr/bin/env groovy

def call(String ImageName){
    echo "Building the docker image from the dockerfile"
    withCredentials([
        usernamePassword(credentialsId:'dockerhub-credentials',
            usernameVariable: "USER",
            passwordVariable: "PASSWD"
        )
    ]) {
        sh "docker build -t $ImageName ."
        sh "echo $PASSWD | docker login -u $USER --password-stdin"
        sh "docker push $ImageName"
    }
}