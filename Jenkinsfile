pipeline{
    agent any
    stages {
        stage('git'){
            steps {
                git branch: 'main', credentialsId: 'git-user-pw', url: 'https://github.com/tothbenceimre/jiraTestWithJenkins.git'
            }
            withMaven {
                sh "mvn clean verify"
            } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs report
        }
        stage('clean'){
            steps {
                cleanWs()
            }
        }
        stage('compile'){
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                    '''
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
