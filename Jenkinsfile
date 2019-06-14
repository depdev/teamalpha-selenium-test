def Application="Team_Alpha_Web"
def PackageName ="testpackage"
def silo="NONE"
pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
        skipDefaultCheckout()
    }
    stages {
        stage('SCM Checkout'){
		agent {label 'ecs-javascript'}
		steps {
		checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/depdev/teamalpha-selenium-test']]]
		}
		}
        stage('Build') {
            steps {
                echo "Application Build is Successfull"
            }
        }
        stage('Deploy') { 
            steps {
                echo "Deployment completed" 
            }
        }
		stage('Automated Test') {
            steps {
            sh 'mvn clean verify -Dbrowser=firefox'
            }

            post {
                success {
                    archiveArtifacts artifacts: "**/*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                }
            }
        }
		
    }
}
