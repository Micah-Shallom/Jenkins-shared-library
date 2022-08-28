#!/usr/bin/env groovy


import com.example.Docker


def call(String ImageName){
    return new Docker(this).dockerPush(ImageName)
}