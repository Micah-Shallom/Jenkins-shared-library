//src/com/example/Docker.groovy

package com.example

class Docker implements Serializable {

    def script

    Docker(script) {
        this.script = script
    }

    def buildDockerImage(String ImageName) {
        echo 'Building the docker image from the dockerfile'
        script.withCredentials([
            script.usernamePassword(credentialsId:'dockerhub-credentials',
                usernameVariable: 'USER',
                passwordVariable: 'PASSWD'
            )
        ]) {
            script.sh "docker build -t $ImageName ."
            script.sh "echo $script.PASSWD | docker login -u $script.USER --password-stdin"
            script.sh "docker push $ImageName"
        }
    }

}
