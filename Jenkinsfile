pipeline{
    agent any
    environment{
        JIRAPASSWORD = "${params.JIRAPASSWORD}"
        JIRAUSERNAME = "${params.JIRAUSERNAME}"
        GRIDURL = "${params.GRIDURL}"
        BASEURL = "${params.BASEURL}"
        TIMEOUT = "${params.TIMEOUT}"

    }
    stages {
        stage('git'){
            steps {
                git branch: 'main', credentialsId: 'git-user-pw', url: 'https://github.com/tothbenceimre/jiraTestWithJenkins.git'
            }
        }
        stage('compile'){
            steps {
                sh "mvn compile"
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
                        sh "mvn test -Dtest=LogoutTest"
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
