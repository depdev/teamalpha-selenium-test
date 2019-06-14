def Application="Team_Alpha_Web"
def PackageName ="testpackage"
def silo="NONE"
pipeline {
    agent {label 'ecs-javascript'}

    options {
        skipStagesAfterUnstable()
        skipDefaultCheckout()
    }

    environment {
        pomfilepath = ''
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
                sshPublisher(publishers: [sshPublisherDesc(configName: 'Team Alpha ECS', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'git checkout ls -l', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'teamalpha-webapp', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

		stage('Automated Test') {
            agent {label 'ecs-javascript'}
            steps {
                sh 'mvn -f pom.xml clean verify install -Dbrowser=firefox'
            }
            post {
                success {
                    archiveArtifacts artifacts: "**/*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                }
            }
        }
		
    }
}
