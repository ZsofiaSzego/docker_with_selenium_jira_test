pipeline{
    agent any
    environment{
        JIRAPASSWORD = "${env.JIRAPASSWORD}"
        JIRAUSERNAME = "${env.JIRAUSERNAME}"
        GRIDURL = "${env.GRIDURL}"
        BASEURL = "${env.BASEURL}"
        TIMEOUT = "${env.TIMEOUT}"

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
                        echo JIRAUSERNAME
                        sh "touch /src/main/resources/.env"
                        sh "echo 'JIRAUSERNAME=${env.JIRAUSERNAME}\n  JIRAPASSWORD = ${env.JIRAPASSWORD} \n GRIDURL = ${env.GRIDURL} \n BASEURL = ${env.BASEURL} \n TIMEOUT = ${env.TIMEOUT}' > .env"
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
            sh "rm .env "
        }
    }
}
