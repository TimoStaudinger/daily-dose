pipeline {
  agent { dockerfile true }
  stages {
    stage('Build Service') {
      steps {
        sh 'mvn -f ./service clean package'
      }
    }
  }
}