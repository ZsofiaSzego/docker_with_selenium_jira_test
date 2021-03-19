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
                        sh "touch src/main/resources/.env"
                        sh '''echo \"JIRAPASSWORD = ${JIRAPASSWORD}\" >> src/main/resources/.env'''
                        sh '''echo \"JIRAUSERNAME = ${JIRAUSERNAME}\" >> src/main/resources/.env'''
                        sh '''echo \"GRIDURL = ${GRIDURL}\" >> src/main/resources/.env'''
                        sh '''echo \"BASEURL = ${BASEURL}\" >> src/main/resources/.env'''
                        sh '''echo \"TIMEOUT = ${TIMEOUT}\" >> src/main/resources/.env'''
                       
                    }
               
                }
                stage('Third stage') {
                    steps {
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
