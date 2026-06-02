pipeline {
    agent any
 
    stages {
        stage('Checkout Code') {
            steps {
                echo 'Pulling the latest code from GitHub...'
                // Clean plain-text URL to prevent git protocol errors
                git branch: 'master',
                    url: '[https://github.com/AYUSH-YL/TutorialNinja.git](https://github.com/AYUSH-YL/TutorialNinja.git)'
            }
        }

        stage('Stop Existing Containers') {
            steps {
                echo 'Cleaning up any running grid containers...'
                bat 'docker-compose down'
            }
        }

        stage('Docker Compose Run') {
            steps {
                echo 'Launching Selenium Grid and Running Maven Automation Test Suite...'
                
                /* --exit-code-from tutorialninja: 
                   Forces Jenkins to track the test execution outcome. If Maven tests fail, 
                   Jenkins marks the build as FAILED (RED). If tests pass, the build is SUCCESS (GREEN).
                   
                   --abort-on-container-exit: 
                   Tears down the Selenium browser container automatically when your maven tests finish.
                */
                bat 'docker-compose up --build --exit-code-from tutorialninja --abort-on-container-exit'
            }
        }
    }
 
    post {
        always {
            echo 'Finalizing execution: Cleaning up container networks...'
            bat 'docker-compose down'
        }
        success {
            echo '✅ Pipeline Execution Completed Successfully!'
        }
        failure {
            echo '❌ Pipeline Execution Failed. Check test logs above.'
        }
    }
}