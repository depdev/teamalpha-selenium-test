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
                dir("/var/jenkins_home/jobs/TeamAlpha_Job/workspace/"){
		            checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/depdev/teamalpha-selenium-test']]]
		        }
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
                dir("/var/jenkins_home/jobs/TeamAlpha_Job/workspace/"){
                    //sh 'mvn -f pom.xml clean verify install -Dbrowser=firefox'
                    sh 'ls -l'
                }
            }
            post {
                success {
                    archiveArtifacts artifacts: "/var/jenkins_home/jobs/TeamAlpha_Job/workspace/*.*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                }
            }
        }
		
    }
}
