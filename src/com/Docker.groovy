#!/usr/bin/env groovy
package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildImage(String ImageName) {
        script.sh "docker build -t $ImageName ."
    }
    def dockerLogin(){
        script.withCredentials([
            script.usernamePassword(credentialsId:'dockerhub-credentials',
                usernameVariable: 'USER',
                passwordVariable: 'PASSWD'
            )
        ]) {
            script.sh "echo $script.PASSWD | docker login -u $script.USER --password-stdin"
        }
    }
    def dockerPush(String ImageName){
        script.sh "docker push $ImageName"
    }
}


