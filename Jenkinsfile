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
                sshPublisher(publishers: [sshPublisherDesc(configName: 'Team Alpha ECS', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'cd teamalpha-webapp && git checkout && docker-compose up --build -d', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
            }
        }

		stage('Automated Test') {
            parallel {
                stage('Firefox') {
                    steps {
                        sh 'mvn -f pom.xml clean verify -Dremote=true -Dbrowser=firefox -DbrowserVersion=66.0.3 -Dplatform=LINUX'
                        //sh 'ls -l'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: "**/target/screenshots/*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                        }
                    }
                }
                stage('Chrome') {
                    steps {
                        sh 'mvn -f pom.xml clean verify -Dremote=true -Dbrowser=chrome -DbrowserVersion=74.0.3729.108 -Dplatform=LINUX'
                        //sh 'ls -l'
                    }
                    post {
                        success {
                            archiveArtifacts artifacts: "**/target/screenshots/*", caseSensitive: true, defaultExcludes: false, fingerprint: true
                        }
                    }
                }
            }
        }
		
    }
}
