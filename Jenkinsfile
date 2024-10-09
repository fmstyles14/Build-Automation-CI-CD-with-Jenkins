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
            stage("increment version") {
                        steps {
                            script {
                                echo ' incrementing app version....'
                                sh 'mvn build-helper:parse-version versions:set-DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} versions:commit'
                                  def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                                  def version = matcher[0] [1]
                                  env.IMAGE_NAME = "$version-$BUILD_NUMBER"
                            }
                        }
                    }
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
            sh 'mvn clean package'
                }
            }
        }

        stage("build and push image") {
            steps {
                script {
                   buildImage "fmstyles/demo-app:${IMAGE_NAME}"
                   dockerLogin()
                   dockerPush "fmstyles/demo-app:${IMAGE_NAME}"
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
