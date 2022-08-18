#!/usr/bin/env groovy

def call() {
    echo "Building the maven application"
    sh "mvn package"
}