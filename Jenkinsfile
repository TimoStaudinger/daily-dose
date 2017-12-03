pipeline {
  agent {
    dockerfile {
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Build Service') {
      steps {
        sh 'mvn -f ./service clean package'
      }
    }
  }
}