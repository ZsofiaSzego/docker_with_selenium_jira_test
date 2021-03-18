pipeline{
    agent any
    stages {
        stage('git'){
            steps {
                git branch: 'main', credentialsId: 'git-credentials', url: 'https://github.com/tothbenceimre/jiraTestWithJenkins.git'
            }
        }
        stage('clean'){
            steps {
                cleanWs()
            }
        }
        stage('compile'){
            steps {
                pwd()
                sh "mvn clean install"
                
            }
        }
        stage('Second Stage'){
            parallel {
                stage('Parallel 1'){
                    agent {
                        label "agent1"
                    }
                    steps{
                        echo "Stage 2 - Parallel 1"
                    }
                }
                stage('Parallel 2'){
                    agent {
                        label "agent2"
                    }
                    steps{
                        echo 'Stage 2 - Parallel 2'
                    }
                }
            }    
        }
        stage('Third Stage'){
            steps{
                echo 'Stage 3'
            }
        }
    }
    post {
        always {
            echo 'finished'
            cleanWs()
        }
    }
}
