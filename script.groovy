def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'fmstyles/demo-app', passwordVariable: 'Geller@57', usernameVariable: 'fmstyles14@gmail.com')]) {
        sh 'docker build -t fmstyles/demo-app:jma-2.0 .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push fmstyles/demo-app:jma-2.0'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
