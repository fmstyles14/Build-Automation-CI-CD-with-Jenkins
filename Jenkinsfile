#!/user/bin/env groovy
@Library('jenkins-shared-library')
def gv

pipeline {   
    agent any
    tools {
        maven 'maven-3.9.9'
    }
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            steps {
                script {
            buildJar'fmstyles/demo-app:jma-3.1'
                }
            }
        }

        stage("build image") {
            steps {
                script {
                   buildImage'fmstyles/demo-app:jma-3.1'
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }               
    }
} 
