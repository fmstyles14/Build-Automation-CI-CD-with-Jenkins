def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentials: 'jenkin-server-credentials', passwordVariable: PASSWORD, usernameVariable: USERNAME)]) {
        sh 'docker build -t fmstyles/demo-app:jma-2.0 .'
        sh 'echo $PASSWORD | docker login -u $USERNAME --password-stdin'
        sh 'docker push fmstyles/demo-app:jma-2.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
