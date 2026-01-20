pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
    }

    environment {
        APP_NAME = 'mon-app-springboot'
        SONAR_PROJECT_KEY = 'demo-devops-aymen-wajdi'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                    mvn sonar:sonar \
                      -Dsonar.projectKey=${SONAR_PROJECT_KEY} \
                      -Dsonar.sources=src/main/java \
                      -Dsonar.tests=src/test/java \
                      -Dsonar.java.binaries=target/classes \
                      -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    """
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo ' Build SUCCESS + Quality Gate OK'
        }
        failure {
            echo ' Build FAILED or Quality Gate FAILED'
        }
    }
}
