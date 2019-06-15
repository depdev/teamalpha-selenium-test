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
                sshPublisher(publishers: [sshPublisherDesc(configName: 'Team Alpha ECS', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'cd teamalpha-webapp && ls -l && docker-compose up --build -d', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

		stage('Automated Test') {
            agent {label 'ecs-javascript'}
            steps {
                dir("/home/jenkins/workspace/TeamAlpha_Job/"){
                    //sh 'mvn -f pom.xml clean verify install -Dbrowser=firefox'
                    sh 'ls -l'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: "/home/jenkins/workspace/TeamAlpha_Job/*.*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                }
            }
        }
		
    }
}
