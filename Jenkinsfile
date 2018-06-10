pipeline {
  agent none
  stages {
    stage('Build') {
      steps {
        build(job: 'test', quietPeriod: -1)
      }
    }
  }
}