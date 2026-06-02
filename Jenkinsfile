pipeline {
    agent any
 
    stages {
        stage('Checkout') {
            steps {
                // Pulls down your code along with the new Dockerfile
                git branch: 'master',
                    url: 'https://github.com/AYUSH-YL/TutorialNinja.git'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Building Docker Image from Dockerfile...'
                // Builds the container image and compiles code inside it
                bat 'docker build -t cucumber-framework .'
            }
        }
 
        stage('Docker Run') {
            steps {
                echo 'Launching Container to execute Automation Tests...'
                // Runs the container which automatically triggers TestRunner
                bat 'docker run --rm cucumber-framework'
            }
        }

        /* NOTE: These local host stages are no longer needed because 
        the Docker container handles compilation, testing, and packaging internally.
        
        stage('Build') { steps { bat 'mvn clean' } }
        stage('Compile') { steps { bat 'mvn compile' } }
        stage('Test') { steps { bat 'mvn test' } }
        stage('Package') { steps { bat 'mvn package' } }
        */
    }
 
    post {
        success {
            echo 'Pipeline Execution Completed Successfully!'
        }
        failure {
            echo 'Pipeline Execution Failed.'
        }
    }
}