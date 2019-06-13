def Application="Team_Alpha_Web"
def PackageName ="testpackage"
def silo="NONE"
pipeline {
    options {
        skipStagesAfterUnstable()
    }
    stages {
	    stage('SCM Checkout'){
		steps {
	        agent {label 'ecs-java'}
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
            agent {label 'ecs-java'}
            sh 'mvn clean install'
            }
        }
		
    }
}
