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
                        sh "touch .env"
                        sh '''echo \"JIRAPASSWORD = ${JIRAPASSWORD}\" >> .env'''
                        sh '''echo \"JIRAUSERNAME = ${JIRAUSERNAME}\" >> .env'''
                        sh '''echo \"GRIDURL = ${GRIDURL}\" >> .env'''
                        sh '''echo \"BASEURL = ${BASEURL}\" >> .env'''
                        sh '''echo \"TIMEOUT = ${TIMEOUT}\" >> .env'''
                        sh "pwd"
                        sh "ls -a"
                        sh "cat .env"
                        sh "mvn test -Dtest=LogoutTest"
                    }
               
                }
                stage('Third stage') {
                    agent {
                        label "agent1"
                    }
                    steps {
                       
                       sh "ls -a"
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
        stage('Fourth Stage'){
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
