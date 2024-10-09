#!/user/bin/env groovy
// @Library('jenkins-shared-library') NOTE: When active 'jenkins-shared-library' need to be created/define in jenkins global shared >system>Global Trusted Pipeline Libraries
library identifier: 'jenkins-shared-library@main', retriever:modernSCM(
    [$class:'GitSCMSource',
    remote: 'https://github.com/fmstyles14/jenkins-shared-library.git',
    credentialsId:'jenkinsCred'])

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
            buildJar ()
                }
            }
        }

        stage("build and push image") {
            steps {
                script {
                   buildImage 'fmstyles/demo-app:jma-3.1'
                   dockerLogin()
                   dockerPush 'fmstyles/demo-app:jma-3.1'
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
